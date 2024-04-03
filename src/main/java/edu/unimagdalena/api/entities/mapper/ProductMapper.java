package edu.unimagdalena.api.entities.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.unimagdalena.api.entities.Product;
import edu.unimagdalena.api.entities.dto.ProductDTO;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDto(Product product);

    @Mapping(target = "orderItems", ignore = true)
    Product productDtoToProduct(ProductDTO productDTO);
}
