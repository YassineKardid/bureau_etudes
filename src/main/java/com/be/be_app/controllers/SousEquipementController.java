package com.be.be_app.controllers;

import com.be.be_app.exceptions.DefaultException;
import com.be.be_app.models.SousEquipement;
import com.be.be_app.repositories.EquipementRepository;
import com.be.be_app.repositories.SousEquipementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/sites/{siteId}/secteurs/{secteurId}/equipements/{equipementId}")
public class SousEquipementController {
    @Autowired
    SousEquipementRepository sousEquipementRepository;
    @Autowired
    EquipementRepository equipementRepository;

    @PostMapping(path = "/sousEquipements")
    public @ResponseBody SousEquipement createSousEquipement(@RequestBody SousEquipement sousEquipement, @PathVariable Long siteId, @PathVariable Long secteurId, @PathVariable Long equipementId){
        return equipementRepository.findById(equipementId).map(equipement -> {   //findByEquipementIdAndSecteurSecteurIdAndSecteurSiteSiteId(siteId, secteurId,equipementId)
            sousEquipement.setEquipement(equipement);
            return  sousEquipementRepository.save(sousEquipement);
        }).orElseThrow(()-> new DefaultException("Error while posting a sous equipement"));
    }
    @GetMapping(path = "/sousEquipements")
    public @ResponseBody Iterable<SousEquipement> getSousEquipement( @PathVariable Long siteId, @PathVariable Long secteurId, @PathVariable Long equipementId){
        return sousEquipementRepository.findByEquipementEquipementId(equipementId);
    }
    @PutMapping(path = "/sousEquipements/{sousEquipementId}")
    public @ResponseBody SousEquipement updateSousEquipement( @RequestBody SousEquipement sousEquipementRequest, @PathVariable Long siteId, @PathVariable Long secteurId,
                                                              @PathVariable Long equipementId, @PathVariable Long sousEquipementId){
        if(!equipementRepository.existsById(equipementId)){
            throw new DefaultException("Equipement not found");
        }
        return sousEquipementRepository.findById(sousEquipementId).map(sousEquipement -> {
            sousEquipement.setName(sousEquipementRequest.getName());
            sousEquipement.setSerialNumber(sousEquipementRequest.getSerialNumber());
            return sousEquipementRepository.save(sousEquipement);
        }).orElseThrow( ()-> new DefaultException("Error while updating sous equipement"));
    }
    @DeleteMapping(path = "/sousEquipements/{sousEquipementId}")
    public ResponseEntity<?> deleteSousEquipement(@PathVariable Long siteId, @PathVariable Long secteurId,
                                                  @PathVariable Long equipementId, @PathVariable Long sousEquipementId){
        return sousEquipementRepository.findById(sousEquipementId).map(sousEquipement -> {
            sousEquipementRepository.delete(sousEquipement);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new DefaultException("Error while deleting a sous equipement"));
    }

}
