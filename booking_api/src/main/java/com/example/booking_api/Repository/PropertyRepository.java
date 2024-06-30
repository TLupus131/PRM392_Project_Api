package com.example.booking_api.Repository;

import com.example.booking_api.Models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    @Query(value = "SELECT p.* \n" +
            "FROM property p \n" +
            "JOIN region r ON r.id = p.region_id \n" +
            "WHERE r.name = :location \n" +
            "  AND p.adult_capacity >= :adult \n" +
            "  AND p.children_capacity >= :children \n" +
            "  AND (:petAllow = false OR p.pets_allowed = true) \n" +
            "  AND :room <= (\n" +
            "    SELECT p.quantity - COALESCE(SUM(res.quantity), 0) \n" +
            "    FROM reservation res \n" +
            "    WHERE res.property_id = p.id \n" +
            "    AND (:checkInDate BETWEEN res.check_in_date AND res.check_out_date " +
            "         OR :checkOutDate BETWEEN res.check_in_date AND res.check_out_date " +
            "         OR res.check_in_date BETWEEN :checkInDate AND :checkOutDate " +
            "         OR res.check_out_date BETWEEN :checkInDate AND :checkOutDate) " +
            "  );", nativeQuery = true)
    List<Property> getPropertiesByRequest(@Param("location") String location,
                                          @Param("checkInDate") Date checkInDate,
                                          @Param("checkOutDate") Date checkOutDate,
                                          @Param("room") int room,
                                          @Param("adult") int adult,
                                          @Param("children") int children,
                                          @Param("petAllow") boolean petAllow);

    @Query(value = "SELECT p.* \n" +
            "FROM property p \n" +
            "JOIN region r ON r.id = p.region_id \n" +
            "WHERE p.id = :propertyId \n" +
            "  AND p.adult_capacity >= :adult \n" +
            "  AND p.children_capacity >= :children \n" +
            "  AND (:petAllow = false OR p.pets_allowed = true) \n" +
            "  AND :room <= (\n" +
            "    SELECT p.quantity - COALESCE(SUM(res.quantity), 0) \n" +
            "    FROM reservation res \n" +
            "    WHERE res.property_id = p.id \n" +
            "    AND (:checkInDate BETWEEN res.check_in_date AND res.check_out_date " +
            "         OR :checkOutDate BETWEEN res.check_in_date AND res.check_out_date " +
            "         OR res.check_in_date BETWEEN :checkInDate AND :checkOutDate " +
            "         OR res.check_out_date BETWEEN :checkInDate AND :checkOutDate) " +
            "  );", nativeQuery = true)
    Property verifyProperty(@Param("propertyId") int propertyId,
                                          @Param("checkInDate") Date checkInDate,
                                          @Param("checkOutDate") Date checkOutDate,
                                          @Param("room") int room,
                                          @Param("adult") int adult,
                                          @Param("children") int children,
                                          @Param("petAllow") boolean petAllow);
}
