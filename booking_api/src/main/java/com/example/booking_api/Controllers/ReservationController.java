package com.example.booking_api.Controllers;

import com.example.booking_api.Models.Reservation;
import com.example.booking_api.Request.ReservationRequest;
import com.example.booking_api.Response.ReservationResponse;
import com.example.booking_api.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllProperty() {
        return new ResponseEntity<>(reservationService.allReservation(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") int id) {
        try {
            Reservation reservation = reservationService.getReservationById(id);
            if (reservation != null) {
                return ResponseEntity.ok(reservation);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ReservationResponse> add(@RequestBody ReservationRequest request) {
        try {
            Reservation reservation = reservationService.makeReservation(request.getUserId(), request.getPropertyId(), request.getFirstName(), request.getLastName(), request.getEmail(), request.getNationality(), request.getPhone(), request.getQuantity(), request.getCheckInDate(), request.getCheckOutDate(), request.getFinalPrice(), request.getAdults(), request.getChildren(), request.getDays());
            ReservationResponse response = new ReservationResponse(reservation.getId(), request.getUserId(), request.getPropertyId(), request.getFirstName(), request.getLastName(), request.getEmail(), request.getNationality(), request.getPhone(), request.getQuantity(), request.getCheckInDate(), request.getCheckOutDate(), request.getFinalPrice(), request.getAdults(), request.getChildren(), request.getDays());
            response.setMessage("Added reservation successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ReservationResponse response = new ReservationResponse(null, request.getUserId(), request.getPropertyId(), request.getFirstName(), request.getLastName(), request.getEmail(), request.getNationality(), request.getPhone(), request.getQuantity(), request.getCheckInDate(), request.getCheckOutDate(), request.getFinalPrice(), request.getAdults(), request.getChildren(), request.getDays());
            response.setMessage("Error adding reservation: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateReservation(
            @RequestParam("id") int reservationId,
            @RequestParam("checkInDate") Date checkInDate,
            @RequestParam("checkOutDate") Date checkOutDate) {

        try {
            reservationService.updateReservationDates(reservationId, checkInDate, checkOutDate);
            return ResponseEntity.ok("Reservation updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating reservation: " + e.getMessage());
        }
    }

    @PostMapping("/delete/{reservationId}")
    public ResponseEntity<String> delete(@PathVariable("reservationId") int reservationId) {
        try {
            reservationService.delete(reservationId);
            return ResponseEntity.ok("Reservation deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting reservation: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Reservation>> getReservationByUser(@RequestParam int userId) {
        return new ResponseEntity<>(reservationService.getReservationByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/canceled")
    public ResponseEntity<List<Reservation>> getCanceledReservationByUser(@RequestParam int userId) {
        return new ResponseEntity<>(reservationService.getCanceledReservationByUser(userId), HttpStatus.OK);
    }
}
