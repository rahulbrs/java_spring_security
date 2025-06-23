package com.example.spring_security_jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_security_jwt.config.JwtUtil;
import com.example.spring_security_jwt.dto.AuthRequest;
import com.example.spring_security_jwt.dto.AuthResponse;
import com.example.spring_security_jwt.dto.RegisterRequest;
import com.example.spring_security_jwt.entity.User;
import com.example.spring_security_jwt.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {
	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;
	private UserService userService;
	
	public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userService = userService;
	}
	
	@PostMapping("/auth/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request){
		User user = userService.registerUser(request);
		return ResponseEntity.ok("User Registered Successfull");
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtil.generateToken(userService.loadUserByUsername(request.getUsername()));
		return ResponseEntity.ok(new AuthResponse(token));
	}
	
	@GetMapping("/user/hello")
	public ResponseEntity<String> userHello(){
		return ResponseEntity.ok("Hello, User");
	}
	
	@GetMapping("/admin/hello")
	public ResponseEntity<String> adminHello(){
		return ResponseEntity.ok("Hello, Admin");
	}
}
