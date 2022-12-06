package com.lendistry.demo.repository;

import com.lendistry.demo.models.Profile;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProfileRepositroy {

    private Random random = new Random();
    private final Map<Integer, Profile> profileMap = new HashMap<>();

    @PostConstruct
    public void initDummyProfileData(){
        profileMap.put(1, new Profile(1, "Alex", "alex@gmail.com"));
        profileMap.put(2, new Profile(2, "John", "john@gmail.com"));
        profileMap.put(3, new Profile(3, "Maria", "maria@gmail.com"));
    }

    public Profile fetchProfileById(Integer profileId){
        return this.profileMap.get(profileId);
    }

    public Collection<Profile> fetchAllProfile(){
        return this.profileMap.values();
    }

    public boolean addProfile(Profile profile){
        try{
            Integer randomProfileId = this.generateProfileId();
            profile.setProfileId(randomProfileId);
            this.profileMap.put(randomProfileId, profile);
            return true;
        }catch(Exception ex){
            return false;
        }
    }

    public boolean updateProfile(Profile profile){
        if(profile.getProfileId() != null && this.profileMap.containsKey(profile.getProfileId())) {
            this.profileMap.put(profile.getProfileId(), profile);
            return true;
        }
        return false;
    }

    public Profile deleteProfile(Integer profileId){
        return this.profileMap.remove(profileId);
    }

    private Integer generateProfileId(){
        return Math.abs(random.nextInt());
    }
}
