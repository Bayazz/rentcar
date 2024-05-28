package com.rentcar.rentcar.service;

import com.rentcar.rentcar.dto.CarDTO;
import com.rentcar.rentcar.model.CarEntity;
import com.rentcar.rentcar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDTO> getAllCarsWithRentalStatus() {
        return carRepository.findAllCarsWithRentalStatus(System.currentTimeMillis());
    }

    public CarDTO getCarByIdWithRentalStatus(Long id) {
        return carRepository.findCarByIdWithRentalStatus(id, System.currentTimeMillis());
    }

    public CarDTO createCar(CarDTO carDTO) {
        CarEntity carEntity = convertCarDTOtoEntity(carDTO);
        CarEntity carEntitySaved = carRepository.save(carEntity);

        return convertCarEntityToDTO(carEntitySaved);
    }

    public CarDTO updateCar(Long id, CarDTO carDTO) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        CarEntity updatedCar = updateCarNonNullProperty(carDTO, carEntity);

        return convertCarEntityToDTO(carRepository.save(updatedCar));
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    private CarDTO convertCarEntityToDTO(CarEntity car) {
        return CarDTO.builder()
                .id(car.getId())
                .manufactureYear(car.getManufactureYear())
                .make(car.getMake())
                .bodyType(car.getBodyType())
                .fuelType(car.getFuelType())
                .model(car.getModel())
                .build();
    }

    private CarEntity convertCarDTOtoEntity(CarDTO carDTO) {
        CarEntity carEntity = new CarEntity();
        carEntity.setBodyType(carDTO.getBodyType());
        carEntity.setManufactureYear(carDTO.getManufactureYear());
        carEntity.setMake(carDTO.getMake());
        carEntity.setModel(carDTO.getModel());
        carEntity.setFuelType(carDTO.getFuelType());

        return carEntity;
    }

    private CarEntity updateCarNonNullProperty(CarDTO carSource, CarEntity carTarget) {
        if (carSource.getMake() != null) {
            carTarget.setMake(carSource.getMake());
        }
        if (carSource.getModel() != null) {
            carTarget.setModel(carSource.getModel());
        }
        if (carSource.getManufactureYear() != null) {
            carTarget.setManufactureYear(carSource.getManufactureYear());
        }
        if (carSource.getFuelType() != null) {
            carTarget.setFuelType(carSource.getFuelType());
        }
        if (carSource.getBodyType() != null) {
            carTarget.setBodyType(carSource.getBodyType());
        }

        return carTarget;
    }
}
