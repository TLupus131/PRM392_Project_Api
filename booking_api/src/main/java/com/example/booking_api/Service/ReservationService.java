package com.example.booking_api.Service;

import com.example.booking_api.Models.Property;
import com.example.booking_api.Models.Reservation;
import com.example.booking_api.Models.User;
import com.example.booking_api.Repository.PropertyRepository;
import com.example.booking_api.Repository.ReservationDetailRepository;
import com.example.booking_api.Repository.ReservationRepository;
import com.example.booking_api.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    @Autowired
    private ReservationDetailService reservationDetailService;

    public Reservation makeReservation(int userId, int propertyId, String firstname, String lastname, String email, String nationality, String phone, int quantity, Date checkInDate, Date checkOutDate, Double price, int adults, int children, int days) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);

        if (!userOptional.isPresent()) {
            throw new Exception("User not found");
        }

        if (!propertyOptional.isPresent()) {
            throw new Exception("Property not found");
        }

        User user = userOptional.get();
        Property property = propertyOptional.get();

        Reservation reservation = new Reservation(
                user, property, firstname, lastname, email,
                nationality, phone, quantity, true, checkInDate, checkOutDate
        );
        reservationRepository.save(reservation);
        reservationDetailService.add(price, reservation, adults, children, days);
        return reservation;
    }

    public List<Reservation> allReservation() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationByUser(int userId) {
        return reservationRepository.getReservationsByUser(userId);
    }

    public List<Reservation> getCanceledReservationByUser(int userId) {
        return reservationRepository.getCanceledReservationsByUser(userId);
    }

    public Reservation getReservationById(int reservationId){
        return reservationRepository.findById(reservationId).orElse(null);
    }

    public void updateReservationDates(int reservationId, Date newCheckInDate, Date newCheckOutDate) {
        reservationRepository.updateReservationDates(reservationId, newCheckInDate, newCheckOutDate);
    }

    public void delete(int reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation != null) {
            if (reservation.getReservationDetail() != null) {
                reservationDetailRepository.delete(reservation.getReservationDetail());
            }
            reservationRepository.deleteById(reservationId);
        } else {
            throw new RuntimeException("Cannot find Reservation with ID: " + reservationId);
        }
    }
}
