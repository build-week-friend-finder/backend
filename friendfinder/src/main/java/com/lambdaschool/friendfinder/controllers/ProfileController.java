package com.lambdaschool.friendfinder.controllers;

import com.lambdaschool.friendfinder.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @GetMapping(value = "/profiles", produces = {"application/json"})
    public ResponseEntity<?> getListOfProfiles() {
        return new ResponseEntity<>(HttpStatus.OK);
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
