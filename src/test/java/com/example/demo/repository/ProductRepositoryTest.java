package com.example.demo.repository;

import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    public static final String NAME = "Auto";
    public static final String DESCRIPTION = "Auto osobowe marki Opel";
    public static final double PRICE = 75_000.00;
    public static final int QUANTITY = 10;

//    @BeforeEach
//            void setUp() {
//        Product product = new Product();
//        product.setName(NAME);
//        product.setDescription(DESCRIPTION);
//        product.setPrice(PRICE);
//        product.setQuantity(QUANTITY);
//
//        assertNull(product.getId());
//
//        productRepository.save(product);
//
//        assertNotNull(product.getId());
//    }

    @Test
    void getProduct(){
        Product product = productRepository.getOne(1L);
        assertEquals("Maciek",product.getName(),"Znaleziono nieprawidłowe imie!");
    }
}
