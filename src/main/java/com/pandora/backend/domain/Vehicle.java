package com.pandora.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String licensePlate;

    @ManyToOne
    private Account responsibleUser;
    private String brand;
    private String carModel;
    private Integer year;
    private String color;
    private String chassis;
    private VehicleStatus vehicleStatus;

    @Transient
    private Double efficiency;
    @Transient
    private Double totalCost;
    private String observation;
    private boolean deleted;
}
