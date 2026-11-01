package com.example.booking.repository;

import com.example.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("""
        SELECT COUNT(b) > 0 FROM Booking b
        WHERE b.shootDate = :shootDate
          AND b.status = 'confirmed'
          AND b.startTime < :newEndTime
          AND b.endTime > :newStartTime
    """)
    boolean hasScheduleConflict(
            @Param("shootDate") LocalDate shootDate, //Safely binds method parameters into the query to prevent SQL injection vulnerabilities.
            @Param("newStartTime") LocalTime newStartTime,
            @Param("newEndTime") LocalTime newEndTime
    );

    List<Booking> findByCustomerEmailOrderByShootDateDesc(String email);

    List<Booking> findAllByOrderByShootDateDescStartTimeAsc();
}