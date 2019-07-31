package com.lambdaschool.friendfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "interests")
public class Interests extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long interestsid;

    private String interestname;
    private Boolean selected;

    @ManyToMany(mappedBy = "interests", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"interests", "profiles"})
    private List<Profile> profiles = new ArrayList<>();

    public Interests() {
    }

    public Interests(String interestname) {
        this.interestname = interestname;
        this.selected = false;
    }

    public long getInterestsid() {
        return interestsid;
    }

    public void setInterestsid(long interestsid) {
        this.interestsid = interestsid;
    }

    public String getInterestname() {
        return interestname;
    }

    public void setInterestname(String interestname) {
        this.interestname = interestname;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
}
