package com.lambdaschool.friendfinder.controllers;

import com.lambdaschool.friendfinder.models.Profile;
import com.lambdaschool.friendfinder.models.User;
import com.lambdaschool.friendfinder.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

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

    @GetMapping(value = "/{userid}/matches", produces = {"application/json"})
    public ResponseEntity<?> getListOfMatchesForUser(@PathVariable long userid) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/profile", produces = {"application/json"})
    public ResponseEntity<?> createProfile(@Valid @RequestBody User user) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/profile/{userid}", produces = {"application/json"})
    public ResponseEntity<?> updateProfile(@Valid @RequestBody User user, @PathVariable long userid) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/profile/{userid}", produces = {"application/json"})
    public ResponseEntity<?> getListOfProfiles(@PathVariable long userid) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
