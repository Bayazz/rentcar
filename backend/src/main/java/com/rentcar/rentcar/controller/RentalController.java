package com.rentcar.rentcar.controller;

import com.rentcar.rentcar.dto.RentalCreateDTO;
import com.rentcar.rentcar.dto.RentalDTO;
import com.rentcar.rentcar.dto.RentalUpdateDTO;
import com.rentcar.rentcar.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
@Tag(name = "Rental", description = "API for managing car rentals")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all rentals")
    public List<RentalDTO> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get rental by ID")
    public RentalDTO getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new rental")
    public RentalDTO createRental(@Valid @RequestBody RentalCreateDTO rental) {
        return rentalService.createRental(rental);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update an existing rental")
    public RentalDTO updateRental(@PathVariable Long id, @Valid @RequestBody RentalUpdateDTO rental) {
        return rentalService.updateRental(id, rental);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a rental")
    public void deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
    }

    @GetMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get current rentals")
    public List<RentalDTO> getCurrentRentals() {
        return rentalService.getCurrentRentals();
    }
}
