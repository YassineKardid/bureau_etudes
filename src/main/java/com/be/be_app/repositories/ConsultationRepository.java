package com.be.be_app.repositories;

import com.be.be_app.Embeddables.ConsultationId;
import com.be.be_app.models.Consultation;
import org.springframework.data.repository.CrudRepository;

public interface ConsultationRepository extends CrudRepository<Consultation, ConsultationId> {

}
