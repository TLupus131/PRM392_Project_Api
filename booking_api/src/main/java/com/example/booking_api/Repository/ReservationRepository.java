package com.example.booking_api.Repository;

import com.example.booking_api.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT * FROM reservation \n" +
            "WHERE user_id = :userId \n" +
            "AND status = true \n" +
            "AND check_in_date > current_date", nativeQuery = true)
    List<Reservation> getReservationsByUser(@Param("userId") int userId);

    @Query(value = "SELECT * FROM reservation \n" +
            "WHERE user_id = :userId \n" +
            "AND status = false", nativeQuery = true)
    List<Reservation> getCanceledReservationsByUser(@Param("userId") int userId);

    @Query(value = "UPDATE reservation \n" +
            "SET check_in_date = :checkInDate, \n" +
            "check_out_date = :checkOutDate \n" +
            "WHERE id = :id", nativeQuery = true)
    void updateReservationDates(@Param("id") int id,
                                @Param("checkInDate") Date checkInDate,
                                @Param("checkOutDate") Date checkOutDate);
}
