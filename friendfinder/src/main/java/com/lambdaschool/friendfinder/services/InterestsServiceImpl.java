package com.lambdaschool.friendfinder.services;

import com.lambdaschool.friendfinder.exceptions.ResourceNotFoundException;
import com.lambdaschool.friendfinder.models.Interests;
import com.lambdaschool.friendfinder.repository.InterestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "interestsService")
public class InterestsServiceImpl implements InterestsService {
    @Autowired
    private InterestsRepository interestsrepos;

    @Override
    public List<Interests> findAll() {
        ArrayList<Interests> list = new ArrayList<>();
        interestsrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Interests findInterestsById(long id) throws ResourceNotFoundException {
        return interestsrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        if (interestsrepos.findById(id).isPresent())
        {
            interestsrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Interests save(Interests interests) {
        Interests newInterest = new Interests();

        newInterest.setInterestname(interests.getInterestname());
        newInterest.setSelected(false);
        return interestsrepos.save(newInterest);
    }

    @Override
    public Interests update(Interests interests, long id) {
        Interests currentInterest = interestsrepos.findById(id).orElseThrow(() -> new EntityNotFoundException());

        if (interests.getInterestname() != null) {
            currentInterest.setInterestname(interests.getInterestname());
        }

        return interestsrepos.save(currentInterest);
    }
}
