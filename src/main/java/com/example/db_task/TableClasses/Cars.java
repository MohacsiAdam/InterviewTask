package com.example.db_task.TableClasses;

import javax.persistence.*;

@Entity
@Table(name = "Cars")
public class Cars {

  public long getCarId() {
    return carId;
  }

  public void setCarId(long carId) {
    this.carId = carId;
  }

  @Id
  @Column(name = "car_id")
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
  private boolean isSent;


}
