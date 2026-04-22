package es.codeurjc.board.rest.mapper;



import org.mapstruct.Mapper;

import es.codeurjc.board.rest.dto.UserBasicDTO;
import es.codeurjc.board.rest.dto.UserExtendedDTO;
import es.codeurjc.board.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserBasicDTO basicToDTO(User user);
}
