package com.example.db_feladat.TableClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cars")
public class Cars {

  @Id
  @Column(name = "car_id", nullable = false)
  private long carId;

  @Column(name = "brand", length = 32)
  private String brand;

  @Column(name = "type", length = 32)
  private String type;

  @Column(name = "plate_number", length = 16)
  private String plateNumber;

  @Column(name = "year_of_manufacture")
  private int yearOfManufacture;

  @Column(name = "calculated_value")
  private int calculatedValue;

  @Column(name = "driven_distance")
  private int drivenDistance;

  @Column(name = "is_sent")
  private int isSent;


}
