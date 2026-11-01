package com.example.booking.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)//Performance optimization it will not join customers table unless we explicitly asks
    @JoinColumn(name = "customer_id", nullable = false)//Specifies exact foreign key column name
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private PhotographyService service;

    @Column(name = "shoot_date", nullable = false)
    private LocalDate shootDate;//LocalDate stores date that matches sql date format

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;//LocalTime Stores only time that supported by sql time format

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "shoot_location", nullable = false, columnDefinition = "TEXT")
    private String shootLocation;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(length = 20)
    private String status = "confirmed";

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    public Booking() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public PhotographyService getService() { return service; }
    public void setService(PhotographyService service) { this.service = service; }
    public LocalDate getShootDate() { return shootDate; }
    public void setShootDate(LocalDate shootDate) { this.shootDate = shootDate; }
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    public String getShootLocation() { return shootLocation; }
    public void setShootLocation(String shootLocation) { this.shootLocation = shootLocation; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
}