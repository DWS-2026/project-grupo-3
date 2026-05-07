package es.codeurjc.board.rest.mapper;



import org.mapstruct.Mapper;

import es.codeurjc.board.rest.dto.UserBasicDTO;
import es.codeurjc.board.rest.dto.UserEditDTO;
import es.codeurjc.board.rest.dto.UserValidationDTO;
import es.codeurjc.board.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserBasicDTO basicToDTO(User user);
    User basicToDomain(UserBasicDTO user);
    User validationToDomain(UserValidationDTO user);
    User editToDomain(UserEditDTO user);
    //User extendedToDomain(UserValidationDTO user);
}
