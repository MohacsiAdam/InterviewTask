package com.example.db_feladat.TableClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cars_of_people")
public class CarsOfPeople {

  @Id
  @Column(name = "person_id")
  private long personId;

  @Id
  @Column(name = "car_id")
  private long carId;


  public long getPersonId() {
    return personId;
  }

  public void setPersonId(long personId) {
    this.personId = personId;
  }


  public long getCarId() {
    return carId;
  }

  public void setCarId(long carId) {
    this.carId = carId;
  }

}
