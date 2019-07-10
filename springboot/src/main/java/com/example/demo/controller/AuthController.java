package com.example.demo.controller;

import com.example.demo.domain.Manager;
import com.example.demo.domain.Spectator;
import com.example.demo.service.AuthService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@CrossOrigin
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/loginSpectator")
    public ResponseEntity<String> loginSpectator(@RequestBody ObjectNode objectNode)  {
        try {
            String username = objectNode.get("username").asText();
            String password = objectNode.get("password").asText();

            authService.loginSpectator(Spectator.builder()
                    .nume(username)
                    .password(password)
                    .build());

            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/loginManager")
    public ResponseEntity<String> loginManager(@RequestBody ObjectNode objectNode)  {
        try {
            String username = objectNode.get("username").asText();
            String password = objectNode.get("password").asText();

            authService.loginManager(Manager.builder()
                    .nume(username)
                    .password(password)
                    .build());

            return new ResponseEntity<>(username, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<String> signUp(@RequestBody ObjectNode objectNode) {
        String username = objectNode.get("username").asText();
        String password = objectNode.get("password").asText();

        authService.signUpSpectator(Spectator.builder()
                .nume(username)
                .password(password)
                .build());

        return new ResponseEntity<>( "User intregistrat.", HttpStatus.OK);
    }

    @PostMapping(value = "/logoutSpectator")
    public ResponseEntity<String> logoutSpectator(@RequestBody ObjectNode objectNode) {

        String username = objectNode.get("username").asText();
        authService.logoutSpectator(Spectator.builder().nume(username).build());

        return new ResponseEntity<>( "Logout spectator", HttpStatus.OK);
    }

    @PostMapping(value = "/logoutManager")
    public ResponseEntity<String> logoutManager(@RequestBody ObjectNode objectNode) {

        String username = objectNode.get("username").asText();

        authService.logoutManager(Manager.builder().nume(username).build());

        return new ResponseEntity<>( "Logout manager", HttpStatus.OK);
    }

}
