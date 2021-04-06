package com.example.db_task.Repositories;

import com.example.db_task.Model.EmailTemplates;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailCRUD extends CrudRepository<EmailTemplates, Long> {

    @Query("SELECT e FROM EmailTemplates e WHERE e.language_id = :lid")
    EmailTemplates getTextByLanguageId(@Param("lid") int language_id);
}
