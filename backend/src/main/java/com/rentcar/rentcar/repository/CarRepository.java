package com.rentcar.rentcar.repository;


import com.rentcar.rentcar.dto.CarDTO;
import com.rentcar.rentcar.model.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

    @Query("SELECT new com.rentcar.rentcar.dto.CarDTO(c.id, c.make, c.model, c.manufactureYear, c.fuelType, c.bodyType, CASE WHEN r.id IS NULL THEN FALSE ELSE TRUE END) " +
            "FROM CarEntity c LEFT JOIN RentalEntity r ON c.id = r.car.id AND (r.endDate IS NULL OR r.endDate > :currentTimestamp) WHERE c.id = :carId")
    CarDTO findCarByIdWithRentalStatus(@Param("carId") Long carId, Long currentTimestamp);

    @Query("SELECT new com.rentcar.rentcar.dto.CarDTO(c.id, c.make, c.model, c.manufactureYear, c.fuelType, c.bodyType, CASE WHEN r.id IS NULL THEN FALSE ELSE TRUE END) " +
            "FROM CarEntity c LEFT JOIN RentalEntity r ON c.id = r.car.id AND (r.endDate IS NULL OR r.endDate > :currentTimestamp)")
    List<CarDTO> findAllCarsWithRentalStatus(Long currentTimestamp);
}
