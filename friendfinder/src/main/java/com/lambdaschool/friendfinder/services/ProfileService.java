package com.lambdaschool.friendfinder.services;

import com.lambdaschool.friendfinder.models.Profile;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface ProfileService {
    ArrayList<Profile> findAll(Pageable pageable);

    Profile findProfileById(long id);

    Profile save(Profile profile);

    Profile update(Profile user, long id);
}
