package com.example.db_task.Model;

import javax.persistence.*;

@Entity
@Table(name = "Email_templates")
public class EmailTemplates {

  @Id
  @Column(name = "language_id")
  private int language_id;

  @Column(name = "text", length = 4000)
  private String text;

  public EmailTemplates(int language_id, String text) {
    this.language_id = language_id;
    this.text = text;
  }

  public EmailTemplates() {
  }

  public int getLanguage_id() {
    return language_id;
  }

  public void setLanguage_id(int language_id) {
    this.language_id = language_id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
