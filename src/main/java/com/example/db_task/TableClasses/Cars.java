package com.example.db_task.TableClasses;

import javax.persistence.*;

@Entity
@Table(name = "Cars")
public class Cars {

  private int carId;

  private String brand;

  private String type;

  private String plateNumber;

  private int yearOfManufacture;

  private int calculatedValue;

  private int drivenDistance;

  private boolean isSent;

  @Id
  @Column(name = "car_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getCarId() {
    return carId;
  }

  public void setCarId(int carId) {
    this.carId = carId;
  }

  @Basic
  @Column(name = "brand")
  public String getBrand() {
    return brand;
  }


  public void setBrand(String brand) {
    this.brand = brand;
  }


  @Basic
  @Column(name = "type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Basic
  @Column(name = "plate_number")
  public String getPlateNumber() {
    return plateNumber;
  }

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }

  @Basic
  @Column(name = "year_of_manufacture")
  public int getYearOfManufacture() {
    return yearOfManufacture;
  }

  public void setYearOfManufacture(int yearOfManufacture) {
    this.yearOfManufacture = yearOfManufacture;
  }

  @Basic
  @Column(name = "calculated_value")
  public int getCalculatedValue() {
    return calculatedValue;
  }

  public void setCalculatedValue(int calculatedValue) {
    this.calculatedValue = calculatedValue;
  }

  @Basic
  @Column(name = "driven_distance")
  public int getDrivenDistance() {
    return drivenDistance;
  }

  public void setDrivenDistance(int drivenDistance) {
    this.drivenDistance = drivenDistance;
  }

  @Basic
  @Column(name = "is_sent")
  public boolean isSent() {
    return isSent;
  }

  public void setSent(boolean sent) {
    isSent = sent;
  }
}
