package com.example.db_task.TableClasses;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cars_of_people")
@Embeddable
public class CarsOfPeople implements Serializable {

  @EmbeddedId
  private CarsOfPeople id;

  @ManyToOne
  @JoinColumn(name = "person_id")
  public PersonData personData;

  @ManyToOne
  @JoinColumn(name = "car_id")
  public  Cars carData;

  public CarsOfPeople(PersonData personData, Cars carData) {
    this.personData = personData;
    this.carData = carData;
  }

  public CarsOfPeople() {

  }

  public CarsOfPeople getId() {
    return id;
  }

  public void setId(CarsOfPeople id) {
    this.id = id;
  }

  public PersonData getPersonData() {
    return personData;
  }

  public void setPersonData(PersonData personData) {
    this.personData = personData;
  }

  public Cars getCarData() {
    return carData;
  }

  public void setCarData(Cars carData) {
    this.carData = carData;
  }
}
