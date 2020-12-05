package com.be.be_app.controllers;

import com.be.be_app.exceptions.DefaultException;
import com.be.be_app.models.Secteur;
import com.be.be_app.repositories.SecteurRepository;
import com.be.be_app.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/sites/{siteId}")
public class SecteurController {
    @Autowired
    SecteurRepository secteurRepository;
    @Autowired
    SiteRepository siteRepository;
    @PostMapping(path = "/secteurs")
    public @ResponseBody Secteur createSecteur(@RequestBody Secteur secteur, @PathVariable Long siteId){
        return siteRepository.findById(siteId).map(site -> {
            secteur.setSite(site);
            return secteurRepository.save(secteur);
        }).orElseThrow(()-> new DefaultException("Error while posting a secteur"));
    }
    @PutMapping(path = "/secteurs/{secteurId}")
    public  @ResponseBody Secteur updateSecteur(Secteur secteurRequest,@PathVariable Long siteId, @PathVariable Long secteurId){

        if (!siteRepository.existsById(siteId)){
            throw new DefaultException("Site Not Found");
        }
        return secteurRepository.findById(secteurId).map(secteur -> {
            //secteur.setSite(secteurRequest.getSite());
            secteur.setName(secteurRequest.getName());
            return secteurRepository.save(secteur);
        }).orElseThrow( ()-> new DefaultException("Error while updating a secteur") );
    }
    @GetMapping(path = "/secteurs")
    public @ResponseBody Iterable<Secteur> getAllSecteurs(@PathVariable Long siteId){
        return secteurRepository.findBySiteSiteId(siteId);
    }
    @DeleteMapping(path = "/secteurs/{secteurId}")
    public  ResponseEntity<?> deleteSecteur(@PathVariable Long siteId, @PathVariable Long secteurId){
        return secteurRepository.findById(secteurId).map(secteur -> { //findBySecteurIdAndSiteSiteId(secteurId, siteId)
            secteurRepository.delete(secteur);
            return ResponseEntity.ok().build();
        }).orElseThrow( ()-> new DefaultException("Error while deleting a secteur") );
    }
}
