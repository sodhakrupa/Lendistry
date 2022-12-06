package com.lendistry.demo.service;

import com.lendistry.demo.models.Profile;
import com.lendistry.demo.repository.ProfileRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepositroy profileRepositroy;

    public Optional<Profile> fetchProfile(Integer profileId){
        return Optional.ofNullable(this.profileRepositroy.fetchProfileById(profileId));
    }

    public Collection<Profile> fetchAllProfiles(){
        return this.profileRepositroy.fetchAllProfile();
    }

    public boolean addNewProfile(Profile profile){
        return this.profileRepositroy.addProfile(profile);
    }

    public boolean updateProfile(Profile profile){
        return this.profileRepositroy.updateProfile(profile);
    }

    public Optional<Profile> deleteProfile(Integer profileId){
        return Optional.ofNullable(this.profileRepositroy.deleteProfile(profileId));
    }

}
