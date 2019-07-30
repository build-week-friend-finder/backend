package com.lambdaschool.friendfinder.controllers;

import com.lambdaschool.friendfinder.models.Profile;
import com.lambdaschool.friendfinder.models.User;
import com.lambdaschool.friendfinder.services.ProfileService;
import com.lambdaschool.friendfinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/allprofiles", produces = {"application/json"})
    public ResponseEntity<?> getListOfAllProfiles() {
        ArrayList<Profile> myProfile = profileService.findAll(Pageable.unpaged());
        return new ResponseEntity<>(myProfile, HttpStatus.OK);
    }

    @GetMapping(value = "/profiles", produces = {"application/json"})
    public ResponseEntity<?> getListOfProfiles(@PageableDefault(page = 0, size = 5)Pageable pageable) {
        ArrayList<Profile> myProfiles = profileService.findAll(pageable);
        return new ResponseEntity<>(myProfiles, HttpStatus.OK);
    }

    @GetMapping(value = "/profile/{profileid}", produces = {"application/json"})
    public ResponseEntity<?> getProfileByUserId(@PathVariable long userid) {
        Profile p = profileService.findProfileById(userid);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    // TODO
    @GetMapping(value = "/{userid}/matches", produces = {"application/json"})
    public ResponseEntity<?> getListOfMatchesForUserByUserId(@PathVariable long userid) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/profile", produces = {"application/json"})
    public ResponseEntity<?> createProfile(@Valid Authentication authentication, @RequestBody Profile profile)  throws URISyntaxException {
        User u = userService.findUserByName(authentication.getName());
        long userid = u.getUserid();

        profile = profileService.save(profile, userid);
        u.setProfile(profile);
        userService.save(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/profile/{userid}", produces = {"application/json"})
    public ResponseEntity<?> updateProfileByUserId(@Valid @RequestBody Profile profile, @PathVariable long userid) {
        profileService.update(profile, userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
