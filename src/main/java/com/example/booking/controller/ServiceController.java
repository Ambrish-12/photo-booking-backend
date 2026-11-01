package com.example.booking.controller;

import com.example.booking.model.PhotographyService;
import com.example.booking.repository.ServiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping
    public List<PhotographyService> getActiveServices() {
        return serviceRepository.findByIsActiveTrue();
    }
}