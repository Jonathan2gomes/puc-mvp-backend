package com.sistemas.senior.stockcontrol.controller;

import com.sistemas.senior.stockcontrol.service.ProductService;
import com.sistemas.senior.stockcontrol.service.dto.ProductDtoIncoming;
import com.sistemas.senior.stockcontrol.service.dto.ProductDtoOutgoing;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/")
    @Operation(summary = "Get all products",description = "Get all products")
    public ResponseEntity getProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by id",description = "Get a product by id")
    public ResponseEntity getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PostMapping(value = "/")
    @Operation(summary = "Register a product",description = "Register a product")
    public ResponseEntity<ProductDtoIncoming> saveProduct(@Valid @RequestBody ProductDtoIncoming productDtoIncoming) {
        service.validateMeasureUnit(productDtoIncoming);
//        service.validateMeasureUnit(productDtoIncoming)
        service.saveProduct(productDtoIncoming);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDtoIncoming);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update a product",description = "Update a product")
    public ResponseEntity<ProductDtoIncoming> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDtoIncoming productDtoIncoming) {
        service.validateMeasureUnit(productDtoIncoming);
        service.updateProduct(productDtoIncoming, id);
        return ResponseEntity.status( HttpStatus.CREATED).body(productDtoIncoming);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product by id",description = "Delete a product by id")
    public ResponseEntity deleteProductById(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
