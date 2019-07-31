package com.lambdaschool.friendfinder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Profile", description = "The Profile Entity")
@Entity
@Table(name = "profiles")
public class Profile extends Auditable {
    @ApiModelProperty(name = "profileid", value = "Primary Key for Profile", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long profileid;

    @ApiModelProperty(name = "name", value = "Name of Profile", required = true, example = "John")
    @Column(nullable = false)
    private String name;

    @ApiModelProperty(name = "gender", value = "Gender of Profile", required = true, example = "M")
    @Column(nullable = false)
    private String gender;

    @ApiModelProperty(name = "description", value = "Description of Profile", required = true, example = "This is my new profile!")
    @Column(nullable = false)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "profileinterests",
            joinColumns = {@JoinColumn(name = "profileid")},
            inverseJoinColumns = {@JoinColumn(name = "interestsid")})
    @JsonIgnoreProperties({"profile", "profiles"})
    private List<Interests> interests = new ArrayList<>();

    @OneToOne(mappedBy = "profile")
    @JsonIgnoreProperties("profile")
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
