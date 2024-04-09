package edu.unimagdalena.api.service.services;

import java.util.List;

import edu.unimagdalena.api.entities.dto.ProductDTO;

public interface ProductService {

    // CRUD
    ProductDTO create(ProductDTO productDTO);

    ProductDTO getProductById(Long productId);

    ProductDTO update(ProductDTO productDTO, Long productId);

    void delete(Long productId);

    // Others methods

    List<ProductDTO> getAllProducts();

    ProductDTO getByName(String name);

    List<ProductDTO> getByPrice(Float price);

    List<ProductDTO> getProductsInStock();

    List<ProductDTO> getByMaxPriceAndStock(Float price, Integer stock);
}
