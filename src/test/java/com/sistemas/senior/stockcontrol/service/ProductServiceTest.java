package com.sistemas.senior.stockcontrol.service;


import com.sistemas.senior.stockcontrol.exception.ConflictException;
import com.sistemas.senior.stockcontrol.exception.NotFoundException;
import com.sistemas.senior.stockcontrol.model.Product;
import com.sistemas.senior.stockcontrol.model.enums.MeasureUnit;
import com.sistemas.senior.stockcontrol.repository.ProductRepository;
import com.sistemas.senior.stockcontrol.service.dto.ProductDtoIncoming;
import com.sistemas.senior.stockcontrol.service.dto.ProductDtoOutgoing;
import com.sistemas.senior.stockcontrol.service.mapper.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    private final String PRODUCT_NAME = "gasolina";

    @Test
    void shouldGetAllProductsWithSuccess() {
        Mockito.when(repository.findAll()).thenReturn(generateProductList());
        List<ProductDtoOutgoing> productsDto = service.getAllProducts();
        assertNotNull(productsDto);
        assertEquals(productsDto.get(0).getName(), generateProductList().get(0).getName());
        assertEquals(productsDto.get(0).getPrice(), generateProductList().get(0).getPrice());
    }

    @Test
    void shouldGetProductWithSuccess() {
        Mockito.when(repository.findById(123l)).thenReturn(Optional.of(generateProductList().get(0)));
        ProductDtoOutgoing product = service.getProductById(123l);
        assertNotNull(product);
        assertEquals(product.getName(), generateProductList().get(0).getName());
        assertEquals(product.getPrice(), generateProductList().get(0).getPrice());
    }

    @Test
    void shouldConvertProductToOutgoingDtoWithSuccess() {
        ProductDtoOutgoing dto = new ProductMapper().toDto(generateProductList().get(0));
        assertNotNull(dto);
        assertEquals(dto.getName(), generateProductList().get(0).getName());
        assertEquals(dto.getPrice(), generateProductList().get(0).getPrice());
        assertEquals("50lt", dto.getQuantity());
    }

    @Test
    void shouldConvertToEntityWithSuccess() {
        Product product = ProductMapper.toEntity(generateProductDtoIncomingList().get(0));
        assertNotNull(product);
        assertEquals(generateProductList().get(0).getName(), product.getName());
        assertEquals(generateProductList().get(0).getMeasureUnit(), product.getMeasureUnit());
        assertEquals(generateProductList().get(0).getManufacturingrDate(), product.getManufacturingrDate());
    }

    @Test
    void shouldValidateProductWithSuccess() {
        Mockito.when(repository.existsByName(PRODUCT_NAME)).thenReturn(true);
        boolean exists = service.validateProduct("gasolina");
        assertTrue(exists);
    }

    @Test
    void shouldSaveProductWithSuccess() {
        Assertions.assertDoesNotThrow(() ->
                service.saveProduct(generateProductDtoIncomingList().get(0)));
    }

    @Test
    void shouldNOTsaveProduct() {
        Mockito.when(repository.existsByName(PRODUCT_NAME)).thenReturn(true);
        Assertions.assertThrows(ConflictException.class, () ->
                service.saveProduct(generateProductDtoIncomingList().get(0))
        );
    }

    @Test
    void shoudUpdateProductWithSuccess() {
        Mockito.when(repository.findById(456l)).thenReturn(Optional.of(generateProductList().get(0)));
        service.updateProduct(generateProductDtoIncomingList().get(0), 456l);
        assertEquals(repository.findById(456l).get().getName(), generateProductList().get(0).getName());
        assertEquals(repository.findById(456l).get().getPrice(), generateProductList().get(0).getPrice());
    }

    @Test
    void shouldNotUpdateProduct() {
        Mockito.when(repository.findById(123l)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () ->
                service.updateProduct(generateProductDtoIncomingList().get(0), 123l));
    }

    @Test
    void shouldValidateMeasureUnit() {
        Assertions.assertDoesNotThrow(() ->
                service.validateMeasureUnit(generateProductDtoIncomingList().get(0)));
    }

    @Test
    void shoudFailValidateMeasureUnitWhenLitro() {
        ProductDtoIncoming product = generateProductDtoIncomingList().get(0);
        product.setQuantity("1234");
        Assertions.assertThrows(ConflictException.class, () ->
                service.validateMeasureUnit(product));
    }

    @Test
    void shoudFailValidateMeasureUnitWhenUnidadde() {
        ProductDtoIncoming product = generateProductDtoIncomingList().get(0);
        product.setMeasureUnit(MeasureUnit.UNIDADE);
        product.setQuantity("2.5");
        Assertions.assertThrows(ConflictException.class, () ->
                service.validateMeasureUnit(product));
    }

    @Test
    void shouldDeleteProductWithSuccess() {
        Mockito.when(repository.findById(123l)).thenReturn(Optional.of(generateProductList().get(0)));
        service.deleteProduct(generateProductList().get(0).getId());
        Mockito.verify(repository).deleteById(123l);

    }


    @Test
    void shouldNotDeleteUnexistingProduct() {
        Assertions.assertThrows(NotFoundException.class, () ->
                service.deleteProduct(123l));
    }


    List<Product> generateProductList() {
        return Arrays.asList(
                new Product(123l, "gasolina", MeasureUnit.LITRO, "50", new BigDecimal(3.45), false, null, LocalDate.of(2023, 01, 04)),
                new Product(456l, "cimento", MeasureUnit.QUILOGRAMA, "20", new BigDecimal(27.5), false, null, LocalDate.now()));
    }

    List<ProductDtoOutgoing> generateProductDtoOutgoingList() {
        return Arrays.asList(
                new ProductDtoOutgoing(123l, "gasolina", "50lt", new BigDecimal(3.45), false, null, LocalDate.of(2023, 01, 04)),
                new ProductDtoOutgoing(123l, "cimento", "50un", new BigDecimal(27.5), false, null, LocalDate.of(2022, 9, 20)));
    }

    List<ProductDtoIncoming> generateProductDtoIncomingList() {
        return Arrays.asList(
                new ProductDtoIncoming("gasolina", MeasureUnit.LITRO, "50", new BigDecimal(3.45), false, null, LocalDate.of(2023, 01, 04)),
                new ProductDtoIncoming("cimento", MeasureUnit.QUILOGRAMA, "50", new BigDecimal(27.5), false, null, LocalDate.of(2022, 9, 20)));
    }
}
