package com.be.be_app.controllers;

import com.be.be_app.exceptions.DefaultException;
import com.be.be_app.models.Plan;
import com.be.be_app.repositories.PlanRepository;
import com.be.be_app.repositories.SousEquipementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/sites/{siteId}/secteurs/{secteurId}/equipements/{equipementId}/sousEquipements/{sousEquipementId}")
public class PlanController {
    @Autowired
    PlanRepository planRepository;
    @Autowired
    SousEquipementRepository sousEquipementRepository;
    @PostMapping(path = "/plans")
    public @ResponseBody Plan createPlan(@RequestBody Plan plan, @PathVariable Long siteId, @PathVariable Long secteurId,
                                         @PathVariable Long equipementId, @PathVariable Long sousEquipementId){
        return sousEquipementRepository.findById(sousEquipementId).map(sousEquipement -> {
            plan.setSousEquipement(sousEquipement);
            return planRepository.save(plan);
        }).orElseThrow(()->new DefaultException("Error while creating a plan"));
    }
    @PutMapping(path = "/plans/{planId}")
    public @ResponseBody Plan updatePlan(@RequestBody Plan planRequest, @PathVariable Long siteId, @PathVariable Long secteurId,
                                         @PathVariable Long equipementId, @PathVariable Long sousEquipementId, @PathVariable String planId){
        if(!sousEquipementRepository.existsById(sousEquipementId)){
            throw new DefaultException("Error while updating plan");
        }
        return planRepository.findById(planId).map(plan -> {
            plan.setDescription(planRequest.getDescription());
            plan.setNumber(planRequest.getNumber());
            plan.setTitle(planRequest.getTitle());
            return planRepository.save(plan);
        }).orElseThrow( ()-> new DefaultException("Error while updating plan"));
    }
    @GetMapping(path = "/plans")
    public @ResponseBody Iterable<Plan> getAllPlansBySousEquipementId(@PathVariable Long siteId, @PathVariable Long secteurId,
                                                                      @PathVariable Long equipementId, @PathVariable Long sousEquipementId){
        return planRepository.findBySousEquipementSousEquipementId(sousEquipementId);
    }
    @GetMapping(path = "/plans/{planId}")
    public @ResponseBody Optional<Plan> getPlanById(@PathVariable String planId){
        return planRepository.findById(planId);
    }
    @DeleteMapping(path = "/plan/{pandId}")
    public ResponseEntity<?> deletePlanById(@PathVariable String planId){
        return  planRepository.findById(planId).map(plan -> {
            planRepository.delete(plan);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new DefaultException("Error while deleting a plan"));
    }
}
