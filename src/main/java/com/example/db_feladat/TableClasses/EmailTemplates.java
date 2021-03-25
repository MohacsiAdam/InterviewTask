package com.example.db_feladat.TableClasses;

import javax.persistence.*;

@Entity
@Table(name = "Email_templates")
public class EmailTemplates {

  @Id
  @Column(name = "language_id", nullable = false)
  private int languageId;

  @Column(name = "text", length = 4000)
  private String text;

  public long getLanguageId() {
    return languageId;
  }

  public void setLanguageId(int languageId) {
    this.languageId = languageId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
