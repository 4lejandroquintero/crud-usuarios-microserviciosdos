package com.robot.user.security.service;

import com.robot.user.entitites.RolUser;
import com.robot.user.entitites.User;
import com.robot.user.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    private UserServiceImpl userService;

    public CustomUserDetailService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: {}", username);

        // Buscar el usuario por su email (username)
        User user = userService.findByEmail(username);

        // Si el usuario no existe, lanzar una excepción
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        // Convertir los roles del usuario a un arreglo de cadenas
        String[] roles = user.getRoles().stream()
                .map(RolUser::name) // Convertir cada RolUser a String
                .toArray(String[]::new); // Convertir el Stream a un arreglo de String

        // Construir el UserDetails con los datos del usuario
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail()) // Usar el email como nombre de usuario
                .password(user.getPassword()) // Usar la contraseña del usuario
                .roles(roles) // Asignar los roles del usuario
                .build();
    }
}
