package com.aydin.bookstore.auth.service;


import com.aydin.bookstore.auth.config.JwtService;
import com.aydin.bookstore.auth.dto.AuthenticationRequest;
import com.aydin.bookstore.auth.dto.AuthenticationResponse;
import com.aydin.bookstore.auth.dto.RegisterRequest;
import com.aydin.bookstore.model.Role;
import com.aydin.bookstore.model.User;
import com.aydin.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .age(request.getAge())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        repository.save(user);

        return "Successfully registered";
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }


}
