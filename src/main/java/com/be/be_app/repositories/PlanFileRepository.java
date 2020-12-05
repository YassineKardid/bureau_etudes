package com.be.be_app.repositories;

import com.be.be_app.models.PlanFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PlanFileRepository extends CrudRepository<PlanFile, String> {
    List<PlanFile> findByPlanPlanId(String planId);
}
