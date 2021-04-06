package com.example.db_task.Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Person_Data")
public class PersonData {

  @Id
  @Column(name = "Person_id")
  private int personId;

  @Column(name = "name", length = 128)
  private String name;

  @Column(name = "data_of_birth")
  private java.sql.Date dataOfBirth;

  @Column(name = "country", length = 128)
  private String country;

  @Column(name = "language_id", insertable = false, updatable = false)
  private int languageId;

  @ManyToOne
  @JoinColumn(name = "language_id")
  private EmailTemplates emailTemplates;

  @Override
  public String toString(){

    return "person id: "+this.personId
            +"\nname: "+this.name
            +"\ndate of birth: "+this.dataOfBirth
            +"\ncountry: "+this.country
            +"\nlanguage_id: "+this.languageId;
  }

  public int getPersonId() {
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

  public Date getDataOfBirth() {
    return dataOfBirth;
  }

  public void setDataOfBirth(Date dataOfBirth) {
    this.dataOfBirth = dataOfBirth;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getLanguageId() {
    return languageId;
  }

  public void setLanguageId(int languageId) {
    this.languageId = languageId;
  }
}
