package edu.unimagdalena.api.servicesTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import edu.unimagdalena.api.entities.Product;
import edu.unimagdalena.api.entities.dto.ProductDTO;
import edu.unimagdalena.api.entities.mapper.ProductMapper;
import edu.unimagdalena.api.repository.ProductRepository;
import edu.unimagdalena.api.service.implementations.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;
    
    @InjectMocks
    ProductServiceImpl productService;

    Product product1 = Product.builder()
            .id(1l)
            .name("pasta")
            .price((float) 6.0)
            .stock(260)
            .build();

    Product product2 = Product.builder()
            .id(2l)
            .name("sopa")
            .price((float) 5.0)
            .stock(200)
            .build();

    ProductDTO productDTO = ProductMapper.INSTANCE.productToProductDto(product1);
    ProductDTO productDTO1 = ProductMapper.INSTANCE.productToProductDto(product2);


    @BeforeEach
    void setUp(){
        when(productRepository.findAll()).thenReturn(List.of(product1, product2));
        when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product1));
        when(productRepository.save(any())).thenReturn(product1);
    }

    @Test
    void testCreate() {
        //given
        ProductDTO saved = productService.create(productDTO);
        //then
        assertThat(saved).isNotNull();
        assertEquals(1l, saved.id());
    }

    @Test
    void testDelete() {
        //when
        ProductDTO saved = productService.create(productDTO);
        when(productRepository.count()).thenReturn(1l);
        assertEquals(1l, productRepository.count());
        //then
        productService.delete(saved.id());
        when(productRepository.count()).thenReturn(0l);
        assertEquals(0l, productRepository.count());
    }

    @Test
    void testGetAllProducts() {
        //given
        productService.create(productDTO);
        //when
        List<ProductDTO> res = productService.getAllProducts();
        //then
        assertEquals(2, res.size());
    }

    @Test
    void testGetByMaxPriceAndStock() {
        //given
        productService.create(productDTO);
        //when
        when(productRepository.findByMaxPriceAndStock(anyFloat(), anyInt())).thenReturn(List.of(product1));
        List<ProductDTO> res = productService.getByMaxPriceAndStock(1f, 1);
        //then
        assertNotNull(res);
        assertEquals(1L, res.get(0).id());
    }

    @Test
    void testGetProductById() {
        //given
        ProductDTO saved = productService.create(productDTO);
        when(productRepository.findById(anyLong())).thenReturn(Optional.ofNullable(product1));
        //when
        ProductDTO res = productService.getProductById(saved.id());
        //then
        assertEquals(saved.id(), res.id());
    }

    @Test
    void testGetProductsInStock() {
        //given
        productService.create(productDTO);
        //when
        when(productRepository.findProductsInStock()).thenReturn(List.of(product1, product2));
        List<ProductDTO> res = productService.getProductsInStock();
        //then
        assertNotNull(res);
        assertEquals(2, res.size());
    }

    @Test
    void testUpdate() {
        //given
        productService.create(productDTO);
        //when
        ProductDTO productUpdate = productService.update(productDTO1, 1l);
        //then
        assertEquals("sopa", productUpdate.name());
    }
}
