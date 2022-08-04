package br.com.mrms.mytasks.controller;

import br.com.mrms.mytasks.controller.request.LoginRequest;
import br.com.mrms.mytasks.controller.request.SignupRequest;
import br.com.mrms.mytasks.controller.response.JwtResponse;
import br.com.mrms.mytasks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
        authService.registerUser(signupRequest.getUsername(), signupRequest.getPassword(), signupRequest.getConfirmPassword(), signupRequest.getRoles());
        return ResponseEntity.ok("Usuário registrado com sucesso");
    }
}