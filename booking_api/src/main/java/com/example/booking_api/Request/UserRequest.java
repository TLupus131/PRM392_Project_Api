package com.example.booking_api.Request;

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
public class UserRequest {
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

    public UserRequest(int id, String email, String password, boolean gender) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }
}
