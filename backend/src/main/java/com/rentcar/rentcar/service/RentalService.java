package com.rentcar.rentcar.service;

import com.rentcar.rentcar.dto.RentalCreateDTO;
import com.rentcar.rentcar.dto.RentalDTO;
import com.rentcar.rentcar.dto.RentalUpdateDTO;
import com.rentcar.rentcar.model.CarEntity;
import com.rentcar.rentcar.model.CustomerEntity;
import com.rentcar.rentcar.model.RentalEntity;
import com.rentcar.rentcar.repository.CarRepository;
import com.rentcar.rentcar.repository.CustomerRepository;
import com.rentcar.rentcar.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    private final CarRepository carRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public RentalService(CustomerRepository customerRepository,
                         CarRepository carRepository,
                         RentalRepository rentalRepository) {
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.rentalRepository = rentalRepository;
    }

    public List<RentalDTO> getAllRentals() {
        return rentalRepository.findAll().stream().map(this::convertRentalEntityToDTO)
                .collect(Collectors.toList());
    }

    public RentalDTO getRentalById(Long id) {
        return rentalRepository.findById(id).map(this::convertRentalEntityToDTO)
                .orElseThrow(() -> new RuntimeException("Rental not found"));
    }

    public List<RentalDTO> getCurrentRentals() {
        List<RentalEntity> rentals = rentalRepository.findCurrentRentals(System.currentTimeMillis());
        return rentals.stream()
                .map(rental -> RentalDTO.builder()
                        .id(rental.getId())
                        .endDate(rental.getEndDate())
                        .kilometersDriven(rental.getKilometersDriven())
                        .startDate(rental.getStartDate())
                        .customerName(rental.getCustomer().getName())
                        .carModel(rental.getCar().getModel())
                        .build())
                .collect(Collectors.toList());
    }

    public RentalDTO createRental(RentalCreateDTO rentalDTO) {
        RentalEntity rentalEntity = convertRentalDTOtoEntity(rentalDTO);
        RentalEntity savedRental = rentalRepository.save(rentalEntity);

        return RentalDTO.builder()
                .id(savedRental.getId())
                .kilometersDriven(savedRental.getKilometersDriven())
                .startDate(savedRental.getStartDate())
                .endDate(savedRental.getEndDate())
                .customerName(savedRental.getCustomer().getName())
                .carModel(savedRental.getCar().getModel())
                .build();
    }

    public RentalDTO updateRental(Long id, RentalUpdateDTO rentalUpdateDTO) {
        RentalEntity rentalEntity = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        RentalEntity updatedRental = updateRentalNonNullProperty(rentalUpdateDTO, rentalEntity);
        return convertRentalEntityToDTO(rentalRepository.save(updatedRental));
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

    private RentalDTO convertRentalEntityToDTO(RentalEntity rental) {
        return RentalDTO.builder()
                .id(rental.getId())
                .carModel(rental.getCar().getModel())
                .customerName(rental.getCustomer().getName())
                .endDate(rental.getEndDate())
                .startDate(rental.getStartDate())
                .kilometersDriven(rental.getKilometersDriven())
                .build();
    }

    private RentalEntity convertRentalDTOtoEntity(RentalCreateDTO rentalCreateDTO) {
        CarEntity carEntity = carRepository.findById(rentalCreateDTO.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        CustomerEntity customerEntity = customerRepository.findById(rentalCreateDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setStartDate(rentalCreateDTO.getStartDate());
        rentalEntity.setEndDate(rentalCreateDTO.getEndDate());
        rentalEntity.setKilometersDriven(rentalCreateDTO.getKilometersDriven());
        rentalEntity.setCar(carEntity);
        rentalEntity.setCustomer(customerEntity);

        return rentalEntity;
    }

    private RentalEntity updateRentalNonNullProperty(RentalUpdateDTO rentalSource, RentalEntity rentalTarget) {
        if (rentalSource.getStartDate() != null) {
            rentalTarget.setStartDate(rentalSource.getStartDate());
        }
        if (rentalSource.getEndDate() != null) {
            rentalTarget.setEndDate(rentalSource.getEndDate());
        }
        if (rentalSource.getKilometersDriven() != null) {
            rentalTarget.setKilometersDriven(rentalSource.getKilometersDriven());
        }

        return rentalTarget;
    }
}
