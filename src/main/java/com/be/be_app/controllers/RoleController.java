package com.be.be_app.controllers;

import com.be.be_app.exceptions.DefaultException;
import com.be.be_app.models.Role;
import com.be.be_app.repositories.ProfileRepository;
import com.be.be_app.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/users/profiles")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ProfileRepository profileRepository;

    @PostMapping(path = "/{profileId}/roles")
    public @ResponseBody Role createRole(@RequestBody Role role, @PathVariable Long profileId){
        return profileRepository.findById(profileId).map(profile -> {
            role.setProfile(profile);
            return roleRepository.save(role);
        }).orElseThrow(()-> new DefaultException("Error while posting a role"));
    }

    @PutMapping(path = "/{profileId}/roles/{roleId}")
    public @ResponseBody Role updateRole(@RequestBody Role roleRequest,@PathVariable Long profileId, @PathVariable Long roleId){

        if(!profileRepository.existsById(roleId)){
            throw new DefaultException("profile Not Found");
        }
        return roleRepository.findById(roleId).map(role -> {
            role.setName(roleRequest.getName());
            //role.setProfile(roleRequest.getProfile());
            return roleRepository.save(role);
        }).orElseThrow( ()-> new DefaultException("Error while posting a role") );
    }
    @DeleteMapping("/{profileId}/roles/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable Long profileId, @PathVariable Long roleId){
        return roleRepository.findByRoleIdAndProfileProfileId(roleId, profileId).map(role -> {
            roleRepository.delete(role);
            return ResponseEntity.ok().build();
        }).orElseThrow( ()-> new DefaultException("Error while deleting a role") );
    }
    @GetMapping("/{profileId}/roles")
    public  @ResponseBody Iterable<Role> getAllProfiles(@PathVariable Long profileId){
        return roleRepository.findByProfileProfileId(profileId);
    }

}
