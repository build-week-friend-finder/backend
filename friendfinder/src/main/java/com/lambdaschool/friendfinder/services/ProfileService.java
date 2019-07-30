package com.lambdaschool.friendfinder.services;

import com.lambdaschool.friendfinder.models.Profile;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface ProfileService {
    ArrayList<Profile> findAll(Pageable pageable);

    Profile findProfileById(long userid);

    Profile save(Profile profile, long userid);

    Profile update(Profile profile, long userid);
}
