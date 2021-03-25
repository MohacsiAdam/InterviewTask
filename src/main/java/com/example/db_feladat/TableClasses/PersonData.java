package com.example.db_feladat.TableClasses;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Person_Data")
public class PersonData {

  @Id
  @Column(name = "Person_id", nullable = false)
  private int personId;

  @Column(name = "name", length = 128)
  private String name;

  @Column(name = "data_of_birth")
  private java.sql.Date dataOfBirht;

  @Column(name = "country", length = 128)
  private String country;

  @Column(name = "language_id")
  private int languageId;

  public long getPersonId() {
    return personId;
  }

  public void setPersonId(int personId) {
    this.personId = personId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDataOfBirht() {
    return dataOfBirht;
  }

  public void setDataOfBirht(Date dataOfBirht) {
    this.dataOfBirht = dataOfBirht;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public long getLanguageId() {
    return languageId;
  }

  public void setLanguageId(int languageId) {
    this.languageId = languageId;
  }
}
