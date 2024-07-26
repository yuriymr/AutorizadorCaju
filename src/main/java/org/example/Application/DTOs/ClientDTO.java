package org.example.Application.DTOs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Client")

public class ClientDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalAmount;
    private BigDecimal foodAmount;
    private BigDecimal mealAmount;
    private BigDecimal cashAmount;

    // Getters e Setters

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(BigDecimal foodAmount) {
        this.foodAmount = foodAmount;
    }

    public BigDecimal getMealAmount() {
        return mealAmount;
    }

    public void setMealAmount(BigDecimal mealAmount) {
        this.mealAmount = mealAmount;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }
}
