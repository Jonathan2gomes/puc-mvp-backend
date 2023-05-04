package com.sistemas.senior.stockcontrol.model;

import com.sistemas.senior.stockcontrol.model.enums.MeasureUnit;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "measure_unit", nullable = false)
    private MeasureUnit measureUnit;

    @Column(name = "quantity", nullable = true)
    private String quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "is_perishable", nullable = false)
    private Boolean isPerishable;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "manufacturing_date", nullable = false)
    private LocalDate ManufacturingrDate;

    public Product(Long id, String name, MeasureUnit measureUnit, String quantity, BigDecimal price, Boolean isPerishable, LocalDate expirationDate, LocalDate manufacturingrDate) {
        this.id = id;
        this.name = name;
        this.measureUnit = measureUnit;
        this.quantity = quantity;
        this.price = price;
        this.isPerishable = isPerishable;
        this.expirationDate = expirationDate;
        ManufacturingrDate = manufacturingrDate;
    }

    public Product() {
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

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
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

    public Boolean getPerishable() {
        return isPerishable;
    }

    public void setPerishable(Boolean perishable) {
        isPerishable = perishable;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getManufacturingrDate() {
        return ManufacturingrDate;
    }

    public void setManufacturingrDate(LocalDate manufacturingrDate) {
        ManufacturingrDate = manufacturingrDate;
    }


}
