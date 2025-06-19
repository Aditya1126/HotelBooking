package com.example.HotelBackend.service;


import com.example.HotelBackend.dto.LoginRequest;
import com.example.HotelBackend.dto.RegistrationRequest;
import com.example.HotelBackend.dto.Response;
import com.example.HotelBackend.dto.UserDTO;
import com.example.HotelBackend.entities.User;

public interface UserService {

    Response registerUser(RegistrationRequest registrationRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    Response getOwnAccountDetails();
    User getCurrentLoggedInUser();
    Response updateOwnAccount(UserDTO userDTO);
    Response deleteOwnAccount();
    Response getMyBookingHistory();
}
