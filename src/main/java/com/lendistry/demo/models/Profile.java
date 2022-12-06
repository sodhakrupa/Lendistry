package com.lendistry.demo.models;

public class Profile {

    private Integer profileId;
    private String name;
    private String emailId;

    public Profile(){}

    public Profile(Integer profileId, String name, String emailId){
        this.profileId = profileId;
        this.name = name;
        this.emailId = emailId;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
