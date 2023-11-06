package com.aydin.bookstore.auth.service;


import com.aydin.bookstore.auth.config.JwtService;
import com.aydin.bookstore.auth.dto.AuthenticationRequest;
import com.aydin.bookstore.auth.dto.AuthenticationResponse;
import com.aydin.bookstore.auth.dto.RegisterRequest;
import com.aydin.bookstore.model.Author;
import com.aydin.bookstore.model.Role;
import com.aydin.bookstore.model.Student;
import com.aydin.bookstore.model.User;
import com.aydin.bookstore.repository.AuthorRepository;
import com.aydin.bookstore.repository.StudentRepository;
import com.aydin.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final AuthorRepository authorRepository;
    private final StudentRepository studentRepository;

    public String register(RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("This account already exists");
        }
        var user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);

        if (user.getRole() == Role.AUTHOR) {
            Author author = Author.builder()
                    .name(request.getName())
                    .age(request.getAge())
                    .build();
            authorRepository.save(author);
        } else if (user.getRole().toString() == Role.STUDENT.toString()) {
            Student student = Student.builder()
                    .name(request.getName())
                    .age(request.getAge())
                    .build();
                studentRepository.save(student);
        } else {
            throw new IllegalArgumentException("Invalid role");
        }

        return "Successfully registered";
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }


}
