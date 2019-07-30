package com.lambdaschool.friendfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profiles")
public class Profile extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long profileid;

    private String name;
    private String gender;
    private String description;

    @OneToMany(mappedBy = "profile")
    @JsonIgnoreProperties("profile")
    private List<Interests> interests = new ArrayList<>();

    @OneToOne(mappedBy = "profile")
    private User user;

    public Profile() {
    }

    public Profile(String name, String gender, String description, List<Interests> interests) {
        this.name = name;
        this.gender = gender;
        this.description = description;
        this.interests = interests;
    }

    public long getProfileid() {
        return profileid;
    }

    public void setProfileid(long profileid) {
        this.profileid = profileid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Interests> getInterests() {
        return interests;
    }

    public void setInterests(List<Interests> interests) {
        this.interests = interests;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
