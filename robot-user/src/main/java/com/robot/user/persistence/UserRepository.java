package com.robot.user.persistence;

import com.robot.user.entitites.RolUser;
import com.robot.user.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByRoles(RolUser roles);
}

