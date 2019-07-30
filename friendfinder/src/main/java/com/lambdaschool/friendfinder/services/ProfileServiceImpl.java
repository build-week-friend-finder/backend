package com.lambdaschool.friendfinder.services;

import com.lambdaschool.friendfinder.exceptions.ResourceNotFoundException;
import com.lambdaschool.friendfinder.models.Interests;
import com.lambdaschool.friendfinder.models.Profile;
import com.lambdaschool.friendfinder.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "profileService")
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profilerepos;

    @Override
    public ArrayList<Profile> findAll(Pageable pageable) {
        ArrayList<Profile> list = new ArrayList<>();
        profilerepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Profile findProfileById(long id) throws ResourceNotFoundException {
        return profilerepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Transactional
    @Override
    public Profile save(Profile profile) {
        Profile newProfile = new Profile();
        newProfile.setName(profile.getName());
        newProfile.setDescription(profile.getDescription());
        newProfile.setGender(profile.getGender());

        ArrayList<Interests> newInterests = new ArrayList<>();
        for (Interests i : profile.getInterests()) {
            newInterests.add(new Interests(i.getInterestname()));
        }
        newProfile.setInterests(newInterests);

        return profilerepos.save(newProfile);
    }

    @Override
    public Profile update(Profile profile, long id) {
        Profile currentProfile = profilerepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (profile.getName() != null) {
            currentProfile.setName(profile.getName());
        }
        if (profile.getDescription()!= null) {
            currentProfile.setDescription(profile.getDescription());
        }
        if (profile.getGender() != null) {
            currentProfile.setGender(profile.getGender());
        }
        if (profile.getInterests() != null) {
            ArrayList<Interests> newInterests = new ArrayList<>();
            for (Interests i : profile.getInterests()) {
                newInterests.add(new Interests(i.getInterestname()));
            }
            currentProfile.setInterests(newInterests);
        }

        return profilerepos.save(currentProfile);
    }
}
