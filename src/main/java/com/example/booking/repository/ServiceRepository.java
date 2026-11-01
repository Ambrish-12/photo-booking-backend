package com.example.booking.repository;

import com.example.booking.model.PhotographyService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceRepository extends JpaRepository<PhotographyService, Long> {
    List<PhotographyService> findByIsActiveTrue();
}
