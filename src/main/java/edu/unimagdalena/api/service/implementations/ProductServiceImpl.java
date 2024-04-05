package edu.unimagdalena.api.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.api.entities.Product;
import edu.unimagdalena.api.entities.dto.ProductDTO;
import edu.unimagdalena.api.entities.exceptions.NotAbleToDeleteException;
import edu.unimagdalena.api.entities.exceptions.ObjectNotFoundException;
import edu.unimagdalena.api.entities.mapper.ProductMapper;
import edu.unimagdalena.api.repository.ProductRepository;
import edu.unimagdalena.api.service.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product productSaved = productRepository.save(ProductMapper.INSTANCE.productDtoToProduct(productDTO));
        return ProductMapper.INSTANCE.productToProductDto(productSaved);
    }

    @Override
    public void delete(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotAbleToDeleteException("Product not found"));
        productRepository.delete(product);

    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .toList();
    }

    @Override
    public List<ProductDTO> getByMaxPriceAndStock(Float price, Integer stock) {
        List<Product> products = productRepository.findByMaxPriceAndStock(price, stock);
        return products.stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .toList();
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found"));
        return ProductMapper.INSTANCE.productToProductDto(product);
    }

    @Override
    public List<ProductDTO> getProductsInStock() {
        List<Product> products = productRepository.findProductsInStock();
        return products.stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .toList();
    }

    @Override
    public ProductDTO update(ProductDTO productDTO, Long productId) {
        Product productInDb = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found"));
        productInDb.setName(productDTO.name());
        productInDb.setPrice(productDTO.price());
        productInDb.setStock(productDTO.stock());
        return ProductMapper.INSTANCE.productToProductDto(productRepository.save(productInDb));
    }

}
