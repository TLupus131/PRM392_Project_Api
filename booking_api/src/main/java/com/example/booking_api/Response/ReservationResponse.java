package com.example.booking_api.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {
    private Integer id;
    private int userId;
    private int propertyId;
    private String firstName;
    private String lastName;
    private String email;
    private String nationality;
    private String phone;
    private int quantity;
    private Date checkInDate;
    private Date checkOutDate;
    private Double finalPrice;
    private String message;

    public ReservationResponse(int userId, int propertyId, String firstName, String lastName, String email, String nationality, String phone, int quantity, Date checkInDate, Date checkOutDate, Double finalPrice) {
        this.userId = userId;
        this.propertyId = propertyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationality = nationality;
        this.phone = phone;
        this.quantity = quantity;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.finalPrice = finalPrice;
    }

    public ReservationResponse(Integer id, int userId, int propertyId, String firstName, String lastName, String email, String nationality, String phone, int quantity, Date checkInDate, Date checkOutDate, Double finalPrice) {
        this.id = id;
        this.userId = userId;
        this.propertyId = propertyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationality = nationality;
        this.phone = phone;
        this.quantity = quantity;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.finalPrice = finalPrice;
    }
}
