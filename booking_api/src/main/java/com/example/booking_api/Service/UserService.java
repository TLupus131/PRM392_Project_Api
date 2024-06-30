package com.example.booking_api.Service;

import com.example.booking_api.Models.User;
import com.example.booking_api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(String email, String password, boolean gender) {
        userRepository.insertUser(email, password, "User", gender);
    }

    public User getAuthenticatedUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
