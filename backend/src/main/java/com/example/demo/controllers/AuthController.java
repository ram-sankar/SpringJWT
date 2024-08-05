package com.example.demo.controllers;

import com.example.demo.dataTransferObject.ApiResponse;
import com.example.demo.dataTransferObject.AuthResponseDto;
import com.example.demo.dataTransferObject.LoginDto;
import com.example.demo.dataTransferObject.RegisterDto;
import com.example.demo.models.Roles;
import com.example.demo.models.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JWTGenerator;
import com.example.demo.util.LoggerUtil;
import com.example.demo.util.constants.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDto>> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                        loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generator(authentication);
        ApiResponse<AuthResponseDto> apiResponse = new ApiResponse<AuthResponseDto>(new AuthResponseDto(token), ResponseMessages.CREATED_SUCCESSFULLY);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterDto registerDTO) {
        if (userRepository.existsByName(registerDTO.getUsername())) {
            ApiResponse<String> apiResponse = new ApiResponse<String>("User name is taken", ResponseMessages.INVALID_REQUEST, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(registerDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Optional<Roles> optionalRoles = roleRepository.findByName("USER");
        List<Roles> optionalRoles2 = roleRepository.findAll();
        if (!optionalRoles.isPresent()) {
            ApiResponse<String> apiResponse = new ApiResponse<String>("Role not found", ResponseMessages.INVALID_REQUEST, HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Roles roles = optionalRoles.get();
        List<Roles> rolesList = Collections.singleton(roles).stream().collect(Collectors.toList());
        userEntity.setRoles(rolesList);

        userRepository.save(userEntity);
        ApiResponse<String> apiResponse = new ApiResponse<String>("User Registered Success", ResponseMessages.CREATED_SUCCESSFULLY, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
