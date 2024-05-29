package com.rentcar.rentcar.controller;


import com.rentcar.rentcar.dto.CarDTO;
import com.rentcar.rentcar.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@Tag(name = "Car", description = "API for managing cars")
public class CarController {

    private final CarService carService;


    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all cars")
    public List<CarDTO> getAllCarsWithRentalStatus() {
        return carService.getAllCarsWithRentalStatus();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a car by ID")
    public CarDTO getCarByIdWithRentalStatus(@PathVariable Long id) {
        return carService.getCarByIdWithRentalStatus(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new car")
    public CarDTO createCar(@RequestBody @Valid CarDTO car) {
        return carService.createCar(car);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update an existing car")
    public CarDTO updateCar(@PathVariable Long id, @RequestBody CarDTO car) {
        return carService.updateCar(id, car);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a car")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
