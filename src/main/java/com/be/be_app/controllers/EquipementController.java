package com.be.be_app.controllers;

import com.be.be_app.exceptions.DefaultException;
import com.be.be_app.models.Equipement;
import com.be.be_app.repositories.EquipementRepository;
import com.be.be_app.repositories.SecteurRepository;
import com.be.be_app.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/sites/{siteId}/secteurs/{secteurId}")
public class EquipementController {
    @Autowired
    EquipementRepository equipementRepository;
    @Autowired
    SecteurRepository secteurRepository;
    @Autowired
    SiteRepository siteRepository;

    @PostMapping(path = "/equipements")
    public @ResponseBody Equipement createEquipement(@RequestBody Equipement equipement, @PathVariable Long siteId, @PathVariable Long secteurId){
        return secteurRepository.findById(secteurId).map(secteur -> { //findBySecteurIdAndSiteSiteId(secteurId,siteId)
            equipement.setSecteur(secteur);
            return equipementRepository.save(equipement);
        }).orElseThrow(()-> new DefaultException("Error while posting a equipement"));
    }
    @PutMapping(path = "/equipements/{equipementId}")
    public @ResponseBody Equipement updateEquipement(@RequestBody Equipement equipementRequest, @PathVariable Long siteId, @PathVariable Long secteurId, @PathVariable Long equipementId){
        if(!secteurRepository.existsById(secteurId)){
            throw new DefaultException(" Secteur not found");
        }
        return equipementRepository.findById(equipementId).map(equipement->{
            secteurRepository.findById(secteurId).map(secteur -> {
                equipement.setSecteur(secteur);
                equipement.setName(equipementRequest.getName());
                return equipement;
            }).orElseThrow( ()-> new DefaultException("Error updating Equipement"));
            return equipementRepository.save(equipement);
        }).orElseThrow( ()-> new DefaultException("Error updating Equipement"));
    }
    @GetMapping(path = "/equipements")
    public @ResponseBody Iterable<Equipement> getEquipementsBySecteurId(@PathVariable Long siteId, @PathVariable Long secteurId){
        return equipementRepository.findBySecteurSecteurId(secteurId);
    }

    @DeleteMapping(path = "/equipements/{equipementId}")
    public ResponseEntity<?> deleteEquipement(@PathVariable Long siteId,@PathVariable Long secteurId, @PathVariable Long equipementId){
        return equipementRepository.findById(equipementId).map(equipement -> { //findByEquipementIdAndSecteurSecteurIdAndSecteurSiteSiteId(equipementId, secteurId, siteId)
            equipementRepository.delete(equipement);
            return ResponseEntity.ok().build();
        }).orElseThrow( ()-> new DefaultException("Error while deleting a secteur") );
    }

}
