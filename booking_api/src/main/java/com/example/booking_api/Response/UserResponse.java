package com.example.booking_api.Response;

import com.example.booking_api.Models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private int id;
    private String name;
    private String avatar;
    private String email;
    private String address;
    private String nationality;
    private String phone;
    private String password;
    private boolean gender;
    private Date dob;
    private User.ERole role;
    private String message;

    public UserResponse(int id, String email, String password, boolean gender) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }
}
