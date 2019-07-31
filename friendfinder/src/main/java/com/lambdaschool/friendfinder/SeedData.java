package com.lambdaschool.friendfinder;

import com.lambdaschool.friendfinder.models.*;
import com.lambdaschool.friendfinder.services.InterestsService;
import com.lambdaschool.friendfinder.services.ProfileService;
import com.lambdaschool.friendfinder.services.RoleService;
import com.lambdaschool.friendfinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;

    @Autowired
    InterestsService interestsService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // prepopulating interests, can be changed
        Interests i1 = new Interests("Basketball");
        Interests i2 = new Interests("Hiking");
        Interests i3 = new Interests("Video Games");
        Interests i4 = new Interests("Drinking");
        Interests i5 = new Interests("KPOP");
        Interests i6 = new Interests("Travelling");
        interestsService.save(i1);
        interestsService.save(i2);
        interestsService.save(i3);
        interestsService.save(i4);
        interestsService.save(i5);
        interestsService.save(i6);

        // prepopulating interests for profiles
        List<Interests> u1Interests = new ArrayList<>();
        u1Interests.add(i1);
        u1Interests.add(i4);

        // prepopulating profiles
        Profile p1 = new Profile("Jack", "M", "Ever take the chance to think 'Why am I here?'", u1Interests);


        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", admins);
        u1.setProfile(p1);
        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", datas);
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", users);
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);

        profileService.save(p1, 10);
    }
}