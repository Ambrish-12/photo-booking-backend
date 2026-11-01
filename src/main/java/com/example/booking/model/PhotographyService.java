package com.example.booking.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity //This informs hibernate that this class represent row in table
@Table(name = "services") //This java file maps to the services table
public class PhotographyService {

    @Id //Designates id as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Informs hibernate this column's auto increment is handled by postgre sql
    private Long id;

    @Column(nullable = false, length = 150)//Enforces database column constraints
    private String title;

    @Column(columnDefinition = "TEXT")//This maps description to unbounded data type TEXT else it will map as varchar2
    private String description;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price; //float or double causes round off error so use bigdecimal

    @Column(name = "is_active")
    private Boolean isActive = true;

    public PhotographyService() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}