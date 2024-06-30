package com.example.booking_api.Repository;

import com.example.booking_api.Models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (email, password, role, gender) VALUES (:email, :password, :role, :gender)", nativeQuery = true)
    void insertUser(String email, String password, String role, boolean gender);

    User findByEmailAndPassword(String email, String password);
}
