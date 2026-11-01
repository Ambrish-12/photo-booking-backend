package com.example.booking.service;

import com.example.booking.dto.BookingRequest;
import com.example.booking.model.Booking;
import com.example.booking.model.Customer;
import com.example.booking.model.PhotographyService;
import com.example.booking.repository.BookingRepository;
import com.example.booking.repository.CustomerRepository;
import com.example.booking.repository.ServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final ServiceRepository serviceRepository;

    public BookingService(BookingRepository bookingRepository,
                          CustomerRepository customerRepository,
                          ServiceRepository serviceRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.serviceRepository = serviceRepository;
    }

    @Transactional
    public Booking createBooking(BookingRequest req) {
        // 1. Fetch Service and compute end_time
        PhotographyService service = serviceRepository.findById(req.getServiceId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Service package not found"));

        LocalTime endTime = req.getStartTime().plusMinutes(service.getDurationMinutes());

        // 2. Guard against time overlap conflicts
        boolean conflict = bookingRepository.hasScheduleConflict(req.getShootDate(), req.getStartTime(), endTime);
        if (conflict) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This time slot is already booked. Please choose another time.");
        }

        // 3. Find or Create Customer by email
        Customer customer = customerRepository.findByEmail(req.getCustomerEmail())
                .orElseGet(() -> customerRepository.save(new Customer(req.getCustomerName(), req.getCustomerEmail(), req.getCustomerPhone())));

        // 4. Save Booking
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setService(service);
        booking.setShootDate(req.getShootDate());
        booking.setStartTime(req.getStartTime());
        booking.setEndTime(endTime);
        booking.setShootLocation(req.getShootLocation());
        booking.setNotes(req.getNotes());
        booking.setStatus("confirmed");

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByEmail(String email) {
        return bookingRepository.findByCustomerEmailOrderByShootDateDesc(email);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAllByOrderByShootDateDescStartTimeAsc();
    }
}