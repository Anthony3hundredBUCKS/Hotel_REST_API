package com.example.demo.repository;

import com.example.demo.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT h FROM Hotel h WHERE " +
            "(:name IS NULL OR LOWER(h.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:brand IS NULL OR LOWER(h.brand) LIKE LOWER(CONCAT('%', :brand, '%'))) AND " +
            "(:city IS NULL OR LOWER(h.address.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
            "(:country IS NULL OR LOWER(h.address.country) LIKE LOWER(CONCAT('%', :country, '%')))")
    List<Hotel> searchBase(@Param("name") String name,
                           @Param("brand") String brand,
                           @Param("city") String city,
                           @Param("country") String country);
}