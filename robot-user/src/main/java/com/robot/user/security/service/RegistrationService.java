package com.robot.user.security.service;

import com.robot.user.entitites.User;
import com.robot.user.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserRepository iUserRepository;

    public RegistrationService(UserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    public User register (User user){
        return iUserRepository.save(user);
    }
}
