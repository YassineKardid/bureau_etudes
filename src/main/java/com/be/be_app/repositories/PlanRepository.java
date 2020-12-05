package com.be.be_app.repositories;

import com.be.be_app.models.Plan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlanRepository extends CrudRepository<Plan, String> {
    List<Plan> findBySousEquipementSousEquipementId(Long sousEquipementId);
}
