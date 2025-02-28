package com.robot.user.security.controller;

import com.robot.user.dto.JWTClient;
import com.robot.user.dto.UserDTO;
import com.robot.user.entitites.User;
import com.robot.user.jwt.JWTGenerator;
import com.robot.user.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/security")
@Slf4j
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    private  final UserServiceImpl userService;

    public LoginController(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, UserServiceImpl userService) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTClient>  login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new  UsernamePasswordAuthenticationToken( userDTO.email(), userDTO.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Details: {}", SecurityContextHolder.getContext().getAuthentication().getName());

        log.info("Rol de user: {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().toString());

        User user = userService.findByEmail(userDTO.email());

        String token = jwtGenerator.getToken(userDTO.email());
        JWTClient jwtClient = new JWTClient(user.getId(), token, user.getRoles().toString());


        return  new ResponseEntity<>(jwtClient, HttpStatus.OK);
    }
}