package com.be.be_app.repositories;

import com.be.be_app.models.Secteur;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SecteurRepository extends CrudRepository<Secteur, Long> {
    List<Secteur> findBySiteSiteId(Long siteId);
    Optional<Secteur> findBySecteurIdAndSiteSiteId(Long secteurId, Long siteId);
}
