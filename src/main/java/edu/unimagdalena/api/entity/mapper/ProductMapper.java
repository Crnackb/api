package edu.unimagdalena.api.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.unimagdalena.api.entity.Product;
import edu.unimagdalena.api.entity.dto.ProductDTO;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDto(Product product);

    @Mapping(target = "orderItems", ignore = true)
    Product productDtoToProduct(ProductDTO productDTO);
}
