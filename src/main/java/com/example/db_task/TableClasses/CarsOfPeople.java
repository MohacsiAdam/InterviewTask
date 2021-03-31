package com.example.db_task.TableClasses;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cars_of_people")
public class CarsOfPeople implements Serializable {

  @Id
  @Column(name = "person_id")
  private int person_id;

  @Id
  @Column(name = "car_id")
  private int car_id;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private PersonData personData;

  @ManyToOne
  @JoinColumn(name = "car_id")
  private Cars cars;
}
