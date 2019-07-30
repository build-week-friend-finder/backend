package com.lambdaschool.friendfinder.repository;

import com.lambdaschool.friendfinder.models.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {
}
