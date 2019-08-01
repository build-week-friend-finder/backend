package com.lambdaschool.friendfinder.controllers;

import com.lambdaschool.friendfinder.models.ErrorDetail;
import com.lambdaschool.friendfinder.models.Profile;
import com.lambdaschool.friendfinder.models.User;
import com.lambdaschool.friendfinder.services.ProfileService;
import com.lambdaschool.friendfinder.services.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Returns all Profiles", response = Profile.class, responseContainer = "List")
    @GetMapping(value = "/allprofiles", produces = {"application/json"})
    public ResponseEntity<?> getListOfAllProfiles() {
        ArrayList<Profile> myProfile = profileService.findAll(Pageable.unpaged());
        return new ResponseEntity<>(myProfile, HttpStatus.OK);
    }

    @ApiOperation(value = "Returns all Profiles with Paging and Sorting", response = Profile.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping(value = "/profiles", produces = {"application/json"})
    public ResponseEntity<?> getListOfProfiles(@PageableDefault(page = 0, size = 5)Pageable pageable) {
        ArrayList<Profile> myProfiles = profileService.findAll(pageable);
        return new ResponseEntity<>(myProfiles, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieves a Profile associated with the userid.", response = Profile.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Profile Found", response = Profile.class),
            @ApiResponse(code = 404, message = "Profile Not Found", response = ErrorDetail.class)})
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

    @ApiOperation(value = "Creates a new Profile.", notes = "", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Profile Created Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error Creating Profile", response = ErrorDetail.class)
    } )
    @PostMapping(value = "/profile", produces = {"application/json"})
    public ResponseEntity<?> createProfile(@Valid Authentication authentication, @RequestBody Profile profile)  throws URISyntaxException {
        User u = userService.findUserByName(authentication.getName());
        long userid = u.getUserid();

        profile = profileService.save(profile);
        profileService.assign(profile.getProfileid(), userid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Updates a current Profile by userid", response = void.class)
    @ApiResponses(value =  {
            @ApiResponse(code = 400, message = "Need Valid Profile Object", response = ErrorDetail.class),
            @ApiResponse(code = 404, message = "Profile Not Found", response = ErrorDetail.class)
    })
    @PutMapping(value = "/profile/{userid}", produces = {"application/json"})
    public ResponseEntity<?> updateProfileByUserId(@Valid @RequestBody Profile profile, @PathVariable long userid) {
        profileService.update(profile, userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
