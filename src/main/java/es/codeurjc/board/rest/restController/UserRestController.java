package es.codeurjc.board.rest.restController;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.ArrayList;

import es.codeurjc.board.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
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

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.codeurjc.board.rest.dto.UserValidationDTO;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    @Autowired
    private ButtonsHeader btnsHeader;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("")
    public ResponseEntity<UserBasicDTO> newUser(@RequestBody UserValidationDTO newUser) throws Exception {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{8,}$";
        if (newUser.description() == null || newUser.description().isBlank() || newUser.password() == null || newUser.password().isBlank()
                || newUser.password().length() < 6 || newUser.password().matches(regex)
                || newUser.username() == null || newUser.email() == null || newUser.email() == null || newUser.email().isBlank()) {
            throw new IllegalArgumentException("Some fields are empty");
        } else {
            User user = userMapper.extendedToDomain(newUser);;
            user.setPassword(passwordEncoder.encode(newUser.password()));
            List<String> roles = new ArrayList<>();
            roles.add("USER");
            user.setRoles(roles);
            userService.saveUser(user);
            Image image;
            try {
                image = imageService.createImage(newImage);
                userService.addImageToUser(user, image);
            } catch (IOException e) {
                e.printStackTrace();
            }
            userService.saveUser(user);
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(location).body(userMapper.basicToDTO(user));
        }

    }

    @GetMapping("")
    public ResponseEntity<?> getUsers(HttpServletRequest request) {

        if (!userService.isUserAdmin(request)) {
            return ResponseEntity.status(403).body("Forbidden");
        }

        List<User> users = userService.findAll();
        List<UserBasicDTO> dtoList = users.stream()
                .map(userMapper::basicToDTO)
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id, HttpServletRequest request) {

        User user = userService.findById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // OWNER OR ADMIN
        if (userService.getUserID(request) != id && !userService.isUserAdmin(request)) {
            return ResponseEntity.status(403).body("Forbidden");
        }

        return ResponseEntity.ok(userMapper.basicToDTO(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(
            @PathVariable long id,
            @RequestBody UserExtendedDTO userDTO,
            HttpServletRequest request) {

        User user = userService.findById(id);

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{8,}$";

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (userService.getUserID(request) != id && !userService.isUserAdmin(request)) {
            return ResponseEntity.status(403).body("Forbidden");
        }

        if (userDTO.email() != null && !userDTO.email().isBlank()) {
            user.setEmail(userDTO.email());
        }

        if (userDTO.username() != null && !userDTO.username().isBlank()) {
            user.setUsername(userDTO.username());
        }

        if (userDTO.description() != null) {
            user.setDescription(userDTO.description());
        }

        if (userDTO.password() != null && !userDTO.password().isBlank()) {
            if (!userDTO.password().matches(regex)) {
                return ResponseEntity.badRequest().body("Invalid password format");
            }
            user.setPassword(passwordEncoder.encode(userDTO.password()));
        }

        userService.saveUser(user);

        return ResponseEntity.ok(userMapper.extendedToDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id, HttpServletRequest request) {

        User user = userService.findById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // OWNER OR ADMIN
        if (userService.getUserID(request) != id && !userService.isUserAdmin(request)) {
            return ResponseEntity.status(403).body("Forbidden");
        }

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
