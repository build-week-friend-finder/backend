package com.lambdaschool.friendfinder.services;

import com.lambdaschool.friendfinder.exceptions.ResourceNotFoundException;
import com.lambdaschool.friendfinder.models.Interests;
import com.lambdaschool.friendfinder.models.Profile;
import com.lambdaschool.friendfinder.models.User;
import com.lambdaschool.friendfinder.repository.ProfileRepository;
import com.lambdaschool.friendfinder.repository.UserRepository;
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

    @Autowired
    private UserRepository userrepos;

    @Override
    public ArrayList<Profile> findAll(Pageable pageable) {
        ArrayList<Profile> list = new ArrayList<>();
        profilerepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Profile findProfileById(long userid) throws ResourceNotFoundException {
        User u = userrepos.findById(userid).orElseThrow(() -> new ResourceNotFoundException(Long.toString(userid)));
        long profileid = u.getProfile().getProfileid();

        return profilerepos.findById(profileid).orElseThrow(() -> new ResourceNotFoundException(Long.toString(profileid)));
    }

    @Override
    public Profile findProfileByProfileId(long profileid) throws ResourceNotFoundException {
        return profilerepos.findById(profileid).orElseThrow(() -> new ResourceNotFoundException(Long.toString(profileid)));
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
    public Profile assign(long profileid, long userid) {
        User u = userrepos.findById(userid).orElseThrow(() -> new EntityNotFoundException(Long.toString(userid)));
        Profile p = profilerepos.findById(profileid).orElseThrow(() -> new EntityNotFoundException(Long.toString(profileid)));

        u.setProfile(p);
        p.setUser(u);

        userrepos.save(u);
        return profilerepos.save(p);
    }

    @Override
    public Profile update(Profile profile) {
        long profileid = profile.getProfileid();
        Profile currentProfile = profilerepos.findById(profileid).orElseThrow(() -> new EntityNotFoundException(Long.toString(profileid)));

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
