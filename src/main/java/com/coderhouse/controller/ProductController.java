package com.coderhouse.controller;

import com.coderhouse.model.request.ProductRequest;
import com.coderhouse.model.response.ProductResponse;
import com.coderhouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService service;

    @PostMapping("")
    public ProductResponse createProduct(
            @Validated @RequestBody ProductRequest product) {
        return service.create(product);
    }

    @GetMapping("/all")
    public List<ProductResponse> searchProduct() {
        return service.searchAll();
    }
}
