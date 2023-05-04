package com.sistemas.senior.stockcontrol.service.mapper;

import com.sistemas.senior.stockcontrol.model.Product;
import com.sistemas.senior.stockcontrol.service.dto.ProductDtoIncoming;
import com.sistemas.senior.stockcontrol.service.dto.ProductDtoOutgoing;

public class ProductMapper {

    public ProductDtoOutgoing toDto(Product product) {
        ProductDtoOutgoing dto = new ProductDtoOutgoing();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setQuantity(product.getQuantity() + product.getMeasureUnit().getAbbreviation());
        dto.setPrice(product.getPrice());
        dto.setPerishable(product.getPerishable());
        dto.setExpirationDate(product.getExpirationDate());
        dto.setManufacturingDate(product.getManufacturingrDate());
        return dto;
    }

    public static Product toEntity(ProductDtoIncoming dto) {

        Product product = new Product();
        convert(dto, product);

        return product;
    }

    public static void convert(ProductDtoIncoming dto, Product product) {
        product.setName(dto.getName());
        product.setMeasureUnit(dto.getMeasureUnit());
        product.setQuantity(dto.getQuantity());
        product.setPrice(dto.getPrice());
        product.setPerishable(dto.getPerishable());
        product.setExpirationDate(dto.getExpirationDate());
        product.setManufacturingrDate(dto.getManufacturingDate());
    }

}
