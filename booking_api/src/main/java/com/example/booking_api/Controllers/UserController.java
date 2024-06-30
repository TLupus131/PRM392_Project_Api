package com.example.booking_api.Controllers;

import com.example.booking_api.Models.Region;
import com.example.booking_api.Models.User;
import com.example.booking_api.Request.UserRequest;
import com.example.booking_api.Response.UserResponse;
import com.example.booking_api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest request) {
        try {
            userService.registerUser(request.getEmail(), request.getPassword(), request.isGender());
            UserResponse response = new UserResponse(request.getId(), request.getEmail(), request.getPassword(), request.isGender());
            response.setMessage("User registed successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            UserResponse response = new UserResponse(request.getId(), request.getEmail(), request.getPassword(), request.isGender());
            response.setMessage("Error registed user: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/authentication")
    public ResponseEntity<User> getAuthenticationUser(
            @RequestParam String email,
            @RequestParam String password){
        User user = userService.getAuthenticatedUser(email, password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
