package backend.artee_group.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import backend.artee_group.dto.LoginDTO.LoginRequest;
import backend.artee_group.dto.LoginDTO.LoginResponse;
import backend.artee_group.dto.RegisterDTO.RegisterRequest;
import backend.artee_group.dto.RegisterDTO.RegisterResponse;
import backend.artee_group.dto.UserDTO.UserResponse;
import backend.artee_group.entity.User;
import backend.artee_group.exception.FuncErrorException;
import backend.artee_group.repository.UserRepository;
import backend.artee_group.util.JwtUtil;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {

        if (request.getEmail() == null || request.getEmail().isBlank() || request.getPassword() == null
                || request.getPassword().isBlank()) {
            throw new FuncErrorException("Email and password are required");
        }

        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isEmpty()) {
            throw new FuncErrorException("User not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
            throw new FuncErrorException("Email or password is incorrect");
        }

        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User users = user.get();

        String token = jwtUtil.generateToken(
                users.getId(), users.getEmail());

        UserResponse userResponse = new UserResponse(users.getId(), users.getEmail());
        return new LoginResponse(token, userResponse);
    }

    public RegisterResponse register(RegisterRequest request) {

        if (request.getEmail() == null || request.getEmail().isBlank() || request.getPassword() == null
                || request.getPassword().isBlank()) {
            throw new FuncErrorException("Email and password are required");
        }

        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent()) {
            throw new FuncErrorException("User already exists");
        }

        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(newUser);

        return new RegisterResponse(new UserResponse(savedUser.getId(), savedUser.getEmail()));
    }
}
