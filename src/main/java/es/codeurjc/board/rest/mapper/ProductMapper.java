package es.codeurjc.board.rest.mapper;

import es.codeurjc.board.rest.dto.ProductDTO;
import es.codeurjc.board.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    List<ProductDTO> toDTOs(Collection<Product> products);
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "reviews", ignore  = true)
    Product toDomain(ProductDTO productDTO);
}
