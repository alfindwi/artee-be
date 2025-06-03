package backend.artee_group.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.artee_group.dto.LoginDTO.LoginRequest;
import backend.artee_group.dto.LoginDTO.LoginResponse;
import backend.artee_group.dto.RegisterDTO.RegisterRequest;
import backend.artee_group.dto.RegisterDTO.RegisterResponse;
import backend.artee_group.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}
