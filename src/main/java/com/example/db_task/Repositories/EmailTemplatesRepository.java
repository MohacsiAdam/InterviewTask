package com.example.db_task.Repositories;

import com.example.db_task.TableClasses.EmailTemplates;

public interface EmailTemplatesRepository {

    void create(EmailTemplates emailTemplates);

    void update(EmailTemplates emailTemplates);

    void delete(EmailTemplates emailTemplates);

    String getTextById(long language_id);
}
