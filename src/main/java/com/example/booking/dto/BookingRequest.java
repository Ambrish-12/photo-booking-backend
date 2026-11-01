package com.example.booking.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequest {

    @NotBlank(message = "Name is required")
    private String customerName;

    @Email(message = "Valid email is required")
    @NotBlank(message = "Email is required")
    private String customerEmail;

    @NotBlank(message = "Phone number is required")
    private String customerPhone;

    @NotNull(message = "Service ID is required")
    private Long serviceId;

    @NotNull(message = "Date is required")
    private LocalDate shootDate;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotBlank(message = "Shoot location is required")
    private String shootLocation;

    private String notes;

    // Getters and Setters
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    public Long getServiceId() { return serviceId; }
    public void setServiceId(Long serviceId) { this.serviceId = serviceId; }
    public LocalDate getShootDate() { return shootDate; }
    public void setShootDate(LocalDate shootDate) { this.shootDate = shootDate; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public String getShootLocation() { return shootLocation; }
    public void setShootLocation(String shootLocation) { this.shootLocation = shootLocation; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}