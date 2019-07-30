package com.lambdaschool.friendfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "interests")
public class Interests extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long interestsid;

    private String interestname;
    private Boolean selected;

    @ManyToOne
    @JoinColumn(name = "profileid")
    @JsonIgnoreProperties("interests")
    private Profile profile;

    public Interests() {
    }

    public Interests(String interestname) {
        this.interestname = interestname;
        this.selected = false;
    }

    public long getId() {
        return interestsid;
    }

    public void setId(long interestsid) {
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
}
