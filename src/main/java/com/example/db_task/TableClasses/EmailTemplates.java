package com.example.db_task.TableClasses;

import javax.persistence.*;

@Entity
@Table(name = "Email_templates")
public class EmailTemplates {

  @Id
  @Column(name = "language_id")
  private int language_id;

  @Column(name = "text", length = 4000)
  private String text;

}
