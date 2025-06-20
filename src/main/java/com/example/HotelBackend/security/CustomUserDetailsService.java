package com.example.HotelBackend.security;

import com.example.HotelBackend.entities.User;
import com.example.HotelBackend.exceptions.NotFoundException;
import com.example.HotelBackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(username).orElseThrow(()-> new NotFoundException("User Email not found."));
        return AuthUser.builder().user(user).build();
    }
}
