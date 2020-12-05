package com.be.be_app.controllers;

import com.be.be_app.exceptions.DefaultException;
import com.be.be_app.models.Site;
import com.be.be_app.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class SiteController {

    @Autowired
    SiteRepository siteRepository;
    @PostMapping(path = "/sites")
    public @ResponseBody Site createSite(@RequestBody Site site){
        return  siteRepository.save(site);
    }
    @PutMapping(path = "/sites/{siteId}")
    public @ResponseBody Site updateSite(@RequestBody Site siteRequest, @PathVariable Long siteId){
        return siteRepository.findById(siteId).map(site ->{
            site.setName(siteRequest.getName());
            return siteRepository.save(site);
        }).orElseThrow(()-> new DefaultException("Error updating sites "));
    }
    @GetMapping(path = "/sites/{siteId}")
    public @ResponseBody Optional<Site> getSite(@PathVariable Long siteId){
        return siteRepository.findById(siteId);
    }
    @GetMapping(path = "/sites")
    public @ResponseBody Iterable<Site> getAllsites(){
        return siteRepository.findAll();
    }

}
