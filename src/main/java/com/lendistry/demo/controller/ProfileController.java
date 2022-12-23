package com.lendistry.demo.controller;

import com.lendistry.demo.models.Profile;
import com.lendistry.demo.service.ProfileService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ObservationRegistry observationRegistry;

    @GetMapping()
    public Collection<Profile> getAllProfiles(){
        return Observation.createNotStarted("getAllProfiles", observationRegistry).observe(() -> profileService.fetchAllProfiles());
    }

    @GetMapping("/{profileId}")
    public Profile getProfile(@PathVariable("profileId")Integer profileId){
        Optional<Profile> profile = this.profileService.fetchProfile(profileId);
        return profile.orElseThrow(() -> new RuntimeException("Profile Not Found with id "+ profileId));
       /* if(profile.isPresent()){
            return profile.get();
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile Not Found");
        } */
    }

    @PostMapping()
    public String addNewProfile(@RequestBody() Profile profile){
        var profileAdded =  profileService.addNewProfile(profile);
        if(profileAdded){
            return "Profile added successfully";
        }else{
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please try again");
        }
    }

    @DeleteMapping("/{profileId}")
    public String deleteProfile(@PathVariable("profileId")Integer profileId){
         Optional<Profile> deletedProfile = profileService.deleteProfile(profileId);
        if(deletedProfile.isPresent()){
            return "Profile successfully deleted";
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile Not Found");
        }
    }

    @PutMapping()
    public String updateProfile(@RequestBody() Profile profile){
        var profileUpdated =  profileService.updateProfile(profile);
        return profileUpdated ? "Profile successfully updated" : "Profile not updated, Please validate profileId";
    }
}
