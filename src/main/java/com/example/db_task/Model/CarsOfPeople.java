package com.example.db_task.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cars_of_people")
@IdClass(CarsOfPeoplePK.class)
public class CarsOfPeople implements Serializable {

  @Id
  @Column(name = "person_id", insertable = false, updatable = false)
  private int person_id;

  @Id
  @Column(name = "car_id", insertable = false, updatable = false)
  private int car_id;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private PersonData personData;

  @ManyToOne
  @JoinColumn(name = "car_id")
  private Cars cars;
}
