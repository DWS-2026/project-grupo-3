package es.codeurjc.board.rest.mapper;

import es.codeurjc.board.model.OrderItems;
import es.codeurjc.board.rest.dto.OrderItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.price", target = "price")
    @Mapping(expression = "java(orderItem.getSubtotal())", target = "subtotal")
    OrderItemDTO toDTO(OrderItems orderItem);
}