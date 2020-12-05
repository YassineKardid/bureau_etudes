package com.be.be_app.repositories;

import com.be.be_app.models.SousEquipement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SousEquipementRepository extends CrudRepository<SousEquipement, Long> {
    List<SousEquipement> findByEquipementEquipementId(Long equipementId);
}
