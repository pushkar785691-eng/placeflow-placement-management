package dev.placeflow.api;

import static dev.placeflow.api.ApiDtos.*;
import dev.placeflow.model.*;
import dev.placeflow.repository.UserRepository;
import dev.placeflow.security.JwtService;
import jakarta.validation.Valid;
import java.util.Locale;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final UserRepository users;private final PasswordEncoder encoder;private final JwtService jwt;
 public AuthController(UserRepository u,PasswordEncoder e,JwtService j){users=u;encoder=e;jwt=j;}
 @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED) AuthResponse register(@Valid @RequestBody RegisterRequest r){String email=r.email().trim().toLowerCase(Locale.ROOT);if(users.existsByEmailIgnoreCase(email))throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already registered");var u=new User();u.setName(r.name().trim());u.setEmail(email);u.setPassword(encoder.encode(r.password()));u.setRole(Role.STUDENT);u.setCourse(r.course());u.setGraduationYear(r.graduationYear());u.setResumeUrl(r.resumeUrl());users.save(u);return new AuthResponse(jwt.issue(email),UserView.of(u));}
 @PostMapping("/login") AuthResponse login(@Valid @RequestBody LoginRequest r){var u=users.findByEmailIgnoreCase(r.email()).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password"));if(!encoder.matches(r.password(),u.getPassword()))throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password");return new AuthResponse(jwt.issue(u.getEmail()),UserView.of(u));}
}
