package com.example.booking_api.Service;

import com.example.booking_api.Models.User;
import com.example.booking_api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

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

    public void updateUser(int id, String name, String email, String password, String nationality, String address, String gender, String dob, String phone) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (!name.equals("null")) {
                user.setName(name);
            }
            if (!email.equals("null")) {
                user.setEmail(email);
            }
            if (!password.equals("null")) {
                user.setPassword(password);
            }
            if (!nationality.equals("null")) {
                user.setNationality(nationality);
            }
            if (!address.equals("null")) {
                user.setAddress(address);
            }
            if (!gender.equals("null")) {
                if (gender.equals("male")) {
                    user.setGender(true);
                } else if (gender.equals("female")) {
                    user.setGender(false);
                }
            }
            if (!dob.equals("null")) {
                user.setDob(Date.valueOf(dob));
            }
            if (!phone.equals("null")) {
                user.setPhone(phone);
            }
            userRepository.save(user);
        }
    }
}
