package com.lambdaschool.friendfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Interests", description = "The Interests Entity")
@Entity
@Table(name = "interests")
public class Interests extends Auditable{
    @ApiModelProperty(name = "interestsid", value = "Primary Key for Interests", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long interestsid;

    @ApiModelProperty(name = "interestname", value = "Name for Interest", required = true, example = "Basketball")
    @Column(nullable = false)
    private String interestname;

    @ApiModelProperty(name = "selected", value = "Did User select interest?", required = true, example = "true")
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
