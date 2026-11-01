package com.example.booking.controller;

import com.example.booking.dto.BookingRequest;
import com.example.booking.model.Booking;
import com.example.booking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking bookSlot(@Valid @RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }

    @GetMapping
    public List<Booking> getBookings(@RequestParam(required = false) String email){
        if (email != null && !email.isBlank()) {
            return bookingService.getBookingsByEmail(email);
        }

        return bookingService.getAllBookings();
    }
}