package com.be.be_app.controllers;

import com.be.be_app.exceptions.DefaultException;
import com.be.be_app.exceptions.FileStorageException;
import com.be.be_app.models.PlanFile;
import com.be.be_app.playload.UploadPlanFileResponse;
import com.be.be_app.repositories.PlanFileRepository;
import com.be.be_app.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;

@Controller
@RequestMapping(path = "/sites/{siteId}/secteurs/{secteurId}/equipements/{equipementId}/sousEquipements/{sousEquipementId}/plans/{planId}")
public class PlanFileController {
    @Autowired
    PlanFileRepository planFileRepository;
    @Autowired
    PlanRepository planRepository;
    @PostMapping(path = "/test")
    public @ResponseBody String test(Model model){
        return "Hello again";
    }
    @PostMapping(path = "/planFiles")
    public @ResponseBody UploadPlanFileResponse uploadPlanFile(@RequestParam("planFileRequest") MultipartFile planFileRequest, @PathVariable Long siteId, @PathVariable Long secteurId, @PathVariable Long equipementId,
                                                 @PathVariable Long sousEquipementId, @PathVariable String planId, HttpServletRequest request){
        String planName = StringUtils.cleanPath((planFileRequest.getOriginalFilename()));
        PlanFile planFile = new PlanFile();
        try{
            if(planName.contains("..")){
                throw new DefaultException("Sorry! Filename contains invalid path sequence " + planName);
            }
            planRepository.findById(planId).map(plan -> {
                planFile.setPlan(plan);
                planFile.setName(planName);
                planFile.setType(planFileRequest.getContentType());

                return  planFile;
            }).orElseThrow(()-> new DefaultException("Error while adding a plan file"));
            planFile.setData(planFileRequest.getBytes());
        } catch (IOException ex){
            throw  new FileStorageException("Could not store file " + planName + ". Please try again!", ex);
        }
        planFileRepository.save(planFile);
        String link = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(request.getRequestURI() +"/DownloadPlanFile/")
                .path(planFile.getPlanFileId())
                .toUriString();
        return new UploadPlanFileResponse(planFile.getName(),link, planFile.getType(), planFileRequest.getSize(),planFile.getPlan());
    }
    @GetMapping(path = "/planFiles")
    public @ResponseBody Iterable<UploadPlanFileResponse> getAllPlanFilesByPlanId(@PathVariable String planId, HttpServletRequest request){
        System.out.println(request.getAttributeNames());
        List<UploadPlanFileResponse> uploadPlanFileResponses = new ArrayList<UploadPlanFileResponse>();
        List<PlanFile> planFiles = new ArrayList<PlanFile>();
        planFiles = planFileRepository.findByPlanPlanId(planId);
        planFiles.forEach(planFile -> {
            String link = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(request.getRequestURI() +"/DownloadPlanFile/")
                    .path(planFile.getPlanFileId())
                    .toUriString();
            uploadPlanFileResponses.add(new UploadPlanFileResponse(planFile.getName(),link, planFile.getType(), planFile.getData().length,planFile.getPlan()));
        });
        return uploadPlanFileResponses;
    }
    @GetMapping(path = "/planFiles/DownloadPlanFile/{planFileId}")
    public ResponseEntity<Resource> downloadPlanFileById(@PathVariable String planFileId){
        PlanFile planFile = planFileRepository.findById(planFileId).orElseThrow( ()->new DefaultException("Plan file not found"));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(planFile.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+planFile.getName()+"\"")
                .body(new ByteArrayResource(planFile.getData()));
    }
}
