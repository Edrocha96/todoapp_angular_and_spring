package br.com.edrocha.users.controllers;

import br.com.edrocha.users.dtos.AuthenticationRequest;
import br.com.edrocha.users.dtos.AuthenticationResponse;
import br.com.edrocha.users.dtos.RegisterRequest;
import br.com.edrocha.users.services.AuthenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) throws JsonProcessingException {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<Void> validateToken(@RequestHeader Map<String, String> headers) {
        final String token = headers.getOrDefault("Authorization", "");
        final Boolean isValid = this.service.validateToken(token);
        if (isValid) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(401).build();
    }
}
