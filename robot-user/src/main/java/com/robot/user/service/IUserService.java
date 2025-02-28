package com.robot.user.service;

import com.robot.user.entitites.RolUser;
import com.robot.user.entitites.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    List<User> getUsersByRoles(RolUser roles);

    User findByEmail(String email);
}
