package com.lambdaschool.friendfinder.repository;

import com.lambdaschool.friendfinder.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
