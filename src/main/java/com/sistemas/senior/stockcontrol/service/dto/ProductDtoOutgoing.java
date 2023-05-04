package com.sistemas.senior.stockcontrol.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductDtoOutgoing {

    private Long id;
    private String name;
    private String quantity;
    private BigDecimal price;
    private boolean isPerishable;
    private LocalDate expirationDate;
    private LocalDate manufacturingDate;

    public ProductDtoOutgoing() {
    }

    public ProductDtoOutgoing(Long id, String name, String quantity, BigDecimal price, boolean isPerishable, LocalDate expirationDate, LocalDate manufacturingDate) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.isPerishable = isPerishable;
        this.expirationDate = expirationDate;
        this.manufacturingDate = manufacturingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isPerishable() {
        return isPerishable;
    }

    public void setPerishable(boolean perishable) {
        isPerishable = perishable;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }
}
