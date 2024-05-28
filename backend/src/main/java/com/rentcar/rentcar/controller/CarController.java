package com.rentcar.rentcar.controller;


import com.rentcar.rentcar.dto.CarDTO;
import com.rentcar.rentcar.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;


    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarDTO> getAllCarsWithRentalStatus() {
        return carService.getAllCarsWithRentalStatus();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDTO getCarByIdWithRentalStatus(@PathVariable Long id) {
        return carService.getCarByIdWithRentalStatus(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@RequestBody @Valid CarDTO car) {
        return carService.createCar(car);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDTO updateCar(@PathVariable Long id, @RequestBody CarDTO car) {
        return carService.updateCar(id, car);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
