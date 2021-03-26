package com.example.db_task.TableClasses;

import javax.persistence.*;

@Entity
@Table(name = "Email_templates")
public class EmailTemplates {

  @Id
  @Column(name = "language_id")
  @ManyToOne
  @JoinColumn(name = "language_id")
  public PersonData personData;

  @Column(name = "text", length = 4000)
  private String text;

  public EmailTemplates(PersonData personData, String text) {
    this.personData = personData;
    this.text = text;
  }

  public EmailTemplates() {

  }

  public PersonData getPersonData() {
    return personData;
  }

  public void setPersonData(PersonData personData) {
    this.personData = personData;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
