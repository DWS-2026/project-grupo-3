package es.codeurjc.board.rest.restController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.board.model.Image;
import es.codeurjc.board.modelAttributes.ButtonsHeader;
import es.codeurjc.board.rest.dto.UserBasicDTO;
import es.codeurjc.board.rest.mapper.UserMapper;
import es.codeurjc.board.service.ImageService;
import es.codeurjc.board.service.OrderService;
import es.codeurjc.board.service.UserService;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import es.codeurjc.board.model.User;
import es.codeurjc.board.rest.dto.UserEditDTO;
import es.codeurjc.board.rest.dto.UserValidationDTO;
import es.codeurjc.board.rest.mapper.UserMapper;
import es.codeurjc.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ImageService imageService;

    @PostMapping("/")
    public ResponseEntity<?> newUser(@Valid @RequestBody UserValidationDTO new_User) {

        User user = userMapper.validationToDomain(new_User);
        user.setPassword(passwordEncoder.encode(new_User.password()));

        List<String> roles = new ArrayList<>();
        roles.add("USER");
        user.setRoles(roles);

        userService.saveUser(user);

        URI location = fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(userMapper.basicToDTO(user));
    }

    @GetMapping("/")
    public ResponseEntity<?> getUsers(HttpServletRequest request) {

        if (!userService.isUserAdmin(request)) {
            return ResponseEntity.status(403).body("Forbidden");
        }

        return ResponseEntity.ok(
                userService.findAll().stream()
                        .map(userMapper::basicToDTO)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id, HttpServletRequest request) {

        User user = userService.findById(id);

        if (user == null) return ResponseEntity.notFound().build();

        if (userService.getUserID(request) != id && !userService.isUserAdmin(request)) {
            return ResponseEntity.status(403).body("Forbidden");
        }

        return ResponseEntity.ok(userMapper.basicToDTO(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(
            @PathVariable long id,
            @Valid @RequestBody  UserEditDTO userDTO,
            HttpServletRequest request) {

        User user = userService.findById(id);

        if (user == null) return ResponseEntity.notFound().build();

        if (userService.getUserID(request) != id && !userService.isUserAdmin(request)) {
            return ResponseEntity.status(403).body("Forbidden");
        }

        try {
            userService.editUser(userDTO.email(), userDTO.username(), userDTO.description(), passwordEncoder.encode(userDTO.password()), null, id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating user: " + e.getMessage());
        }

        if(userService.requiresReLogin(userDTO.username(), userDTO.email(), userDTO.password())){
                request.getSession().invalidate();
        }
        return ResponseEntity.ok(userMapper.basicToDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id, HttpServletRequest request) {

        User user = userService.findById(id);

        if (user == null) return ResponseEntity.notFound().build();

        if (userService.getUserID(request) != id && !userService.isUserAdmin(request)) {
            return ResponseEntity.status(403).body("Forbidden");
        }

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}/profile-photo")
    public ResponseEntity<?> uploadProfilePhoto(
            @PathVariable long id,
            @RequestPart("imageFile") MultipartFile imageFile,
            HttpServletRequest request) {

        User user = userService.findById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Solo el propio usuario o admin
        if (userService.getUserID(request) != id &&
                !userService.isUserAdmin(request)) {

            return ResponseEntity.status(403).body("Forbidden");
        }

        try {

            if (imageFile == null || imageFile.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Image file is empty");
            }

            Image image = imageService.createImage(imageFile);

            userService.addImageToUser(user, image);

            return ResponseEntity.ok(
                    userMapper.basicToDTO(user)
            );

        } catch (Exception e) {

            return ResponseEntity.internalServerError()
                    .body("Error uploading image");

        }
    }
}
