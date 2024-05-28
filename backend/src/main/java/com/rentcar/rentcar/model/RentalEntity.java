package com.rentcar.rentcar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rental")
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    private long startDate;
    private long endDate;
    private int kilometersDriven;
}
