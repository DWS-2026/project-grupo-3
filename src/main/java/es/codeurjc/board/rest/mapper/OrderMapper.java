package es.codeurjc.board.rest.mapper;

import es.codeurjc.board.model.Order;
import es.codeurjc.board.rest.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "status", target = "status")
    OrderDTO toDTO(Order order);

    List<OrderDTO> toDTOs(List<Order> orders);
}