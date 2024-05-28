package com.rentcar.rentcar.repository;

import com.rentcar.rentcar.model.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalRepository extends JpaRepository<RentalEntity, Long> {
    @Query("SELECT r FROM RentalEntity r WHERE r.endDate IS NULL OR r.endDate > :currentTimestamp")
    List<RentalEntity> findCurrentRentals(long currentTimestamp);
}
