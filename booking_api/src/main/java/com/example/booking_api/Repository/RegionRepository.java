package com.example.booking_api.Repository;

import com.example.booking_api.Models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

    @Query(value = "SELECT * FROM region WHERE name LIKE %:text%", nativeQuery = true)
    List<Region> getRegionsByRequest(@Param("text") String text);
}
