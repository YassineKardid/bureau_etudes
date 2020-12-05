package com.be.be_app.repositories;

import com.be.be_app.models.Equipement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EquipementRepository extends CrudRepository<Equipement, Long> {
    List<Equipement> findBySecteurSecteurId(Long secteurId);
    Optional<Equipement> findByEquipementIdAndSecteurSecteurId(Long equipementId, Long secteurId);
    Optional<Equipement> findByEquipementIdAndSecteurSecteurIdAndSecteurSiteSiteId(Long equipementId, Long secteurId, Long siteId);
}
