package com.lambdaschool.friendfinder.services;

import com.lambdaschool.friendfinder.models.Interests;

import java.util.List;

public interface InterestsService {
    List<Interests> findAll();

    Interests findInterestsById(long id);

    void delete(long id);

    Interests save(Interests interests);

    Interests update(Interests interests, long id);
}
