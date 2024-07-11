package com.example.booking_api.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "reservationDetail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "adults", nullable = false)
    private Integer adults;

    @Column(name = "children", nullable = false)
    private Integer children;

    @Column(name = "days", nullable = false)
    private Integer days;

    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    @JsonIgnoreProperties("reservationDetail")
    private Reservation reservation;

    public ReservationDetail(Double totalPrice, Reservation reservation, int adults, int children, int days) {
        this.totalPrice = totalPrice;
        this.reservation = reservation;
        this.adults = adults;
        this.children = children;
        this.days = days;
    }
}
