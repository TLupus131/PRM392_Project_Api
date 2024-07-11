package com.example.booking_api.Service;

import com.example.booking_api.Models.Reservation;
import com.example.booking_api.Models.ReservationDetail;
import com.example.booking_api.Repository.ReservationDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationDetailService {

    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    public void add(double price, Reservation reservation, int adults, int children, int days) {
        ReservationDetail reservationDetail = new ReservationDetail(price, reservation, adults, children, days);
        reservationDetailRepository.save(reservationDetail);
    }
}
