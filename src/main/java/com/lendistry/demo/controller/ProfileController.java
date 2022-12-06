package com.lendistry.demo.controller;

import com.lendistry.demo.models.Profile;
import com.lendistry.demo.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RequestMapping("/api/profile/")
@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping()
    public Collection<Profile> getAllProfiles(){
        return profileService.fetchAllProfiles();
    }

    @GetMapping("/{profileId}")
    public Profile getProfile(@PathVariable("profileId")Integer profileId){
        Optional<Profile> profile = this.profileService.fetchProfile(profileId);
        if(profile.isPresent()){
            return profile.get();
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile Not Found");
        }
    }

    @PostMapping()
    public String addNewProfile(@RequestBody() Profile profile){
        boolean profileAdded =  profileService.addNewProfile(profile);
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
        boolean profileUpdated =  profileService.updateProfile(profile);
        return profileUpdated ? "Profile successfully updated" : "Profile not updated, Please validate profileId";
    }
}
