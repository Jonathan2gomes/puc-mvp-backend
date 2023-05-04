package com.sistemas.senior.stockcontrol.service;

import com.sistemas.senior.stockcontrol.exception.ConflictException;
import com.sistemas.senior.stockcontrol.exception.NotFoundException;
import com.sistemas.senior.stockcontrol.model.Product;
import com.sistemas.senior.stockcontrol.model.enums.MeasureUnit;
import com.sistemas.senior.stockcontrol.repository.ProductRepository;
import com.sistemas.senior.stockcontrol.service.dto.ProductDtoIncoming;
import com.sistemas.senior.stockcontrol.service.dto.ProductDtoOutgoing;
import com.sistemas.senior.stockcontrol.service.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<ProductDtoOutgoing> getAllProducts() {
        return productRepository.findAll().stream().map(new ProductMapper()::toDto).collect(Collectors.toList());
    }


    public ProductDtoOutgoing getProductById(Long id) {
        return new ProductMapper().toDto(productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found")));
    }

    public void saveProduct(ProductDtoIncoming productDtoIncoming) {
        if (validateProduct(productDtoIncoming.getName())) {
            throw new ConflictException("Product already exists");
        } else {
            Product product = ProductMapper.toEntity(productDtoIncoming);
            productRepository.save(product);
        }
    }

    public boolean validateProduct(String name) {
        return productRepository.existsByName(name);
    }

    public void updateProduct(ProductDtoIncoming dto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product doesn't exist"));
        ProductMapper.convert(dto, product);
        productRepository.save(product);
    }

    public void validateMeasureUnit(ProductDtoIncoming productDtoIncoming) {
        if ((productDtoIncoming.getQuantity().length() > 3) && (productDtoIncoming.getMeasureUnit().equals(MeasureUnit.LITRO) || productDtoIncoming.getMeasureUnit().equals(MeasureUnit.QUILOGRAMA))) {
            throw new ConflictException("Product quantity cannot have more than 3 digits when LITRO or QUILOGRAMA.");
        } else if (productDtoIncoming.getMeasureUnit().equals(MeasureUnit.UNIDADE) && productDtoIncoming.getQuantity().contains(".")) {
            throw new ConflictException("Product quantity has to be an integer number");
        }
    }

    public void deleteProduct(Long id) {
        getProductById(id);
        productRepository.deleteById(id);
    }
}
