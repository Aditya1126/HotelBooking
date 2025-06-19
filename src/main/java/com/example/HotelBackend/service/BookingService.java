package com.example.HotelBackend.service;


import com.example.HotelBackend.dto.BookingDTO;
import com.example.HotelBackend.dto.Response;

public interface BookingService {

    Response getAllBookings();
    Response createBooking(BookingDTO bookingDTO);
    Response findBookingByReferenceNo(String  bookingReference);
    Response updateBooking(BookingDTO bookingDTO);
}
