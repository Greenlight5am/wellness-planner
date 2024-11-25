package com.prova.wellnessplanner.security.auth;

import com.prova.wellnessplanner.entity.User;
import com.prova.wellnessplanner.enums.Role;
import com.prova.wellnessplanner.repository.UserRepository;
import com.prova.wellnessplanner.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager manager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager manager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.manager = manager;
    }

    public AuthenticationResponse register(RegisterRequest request){
        User user = new User(request.getFirstname(), request.getLastname(), request.getEmail(),
                            passwordEncoder.encode(request.getPassword()), Role.USER);
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
