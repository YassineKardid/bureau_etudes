package com.be.be_app.controllers;

import com.be.be_app.exceptions.DefaultException;
import com.be.be_app.exceptions.FileStorageException;
import com.be.be_app.models.Profile;
import com.be.be_app.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/users")
public class ProfileController {
    @Autowired
    ProfileRepository profileRepository;
    @PostMapping(path="/profiles")
    public @ResponseBody Profile createProfile(@RequestBody Profile profile){
        profileRepository.save(profile);
        return profile;
    }
    @PutMapping(path = "/profiles/{profileId}")
    public @ResponseBody Profile updateProfile(@PathVariable Long profileId, @RequestBody Profile profileRequest){
        return profileRepository.findById(profileId).map(profile ->
        {
            profile.setName(profileRequest.getName());
            return profileRepository.save(profile);
        }).orElseThrow(()-> new DefaultException("Sorry! Error "));
    }

    @GetMapping(path="/profiles")
    public @ResponseBody Iterable<Profile> getAllProfiles() {

        return  profileRepository.findAll();
    }
    @GetMapping(path="/profiles/{profileId}")
    public @ResponseBody Optional<Profile> getProfile(@PathVariable Long profileId){
        return  profileRepository.findById(profileId);
    }
    @DeleteMapping(path = "profiles/{profileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long profileId){
        return profileRepository.findById(profileId).map(profile -> {
            profileRepository.delete(profile);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new DefaultException("Error while deleting"));
    }

}
