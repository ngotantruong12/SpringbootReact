package ntt.com.example.demo_SpringBoot_Web.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ntt.com.example.demo_SpringBoot_Web.dto.request.SiginRequest;
import ntt.com.example.demo_SpringBoot_Web.dto.request.SignupRequest;
import ntt.com.example.demo_SpringBoot_Web.dto.respone.JwtResponse;
import ntt.com.example.demo_SpringBoot_Web.dto.respone.MessageResponse;
import ntt.com.example.demo_SpringBoot_Web.entity.ERoleEntity;
import ntt.com.example.demo_SpringBoot_Web.entity.RoleEntity;
import ntt.com.example.demo_SpringBoot_Web.entity.UserEntity;
import ntt.com.example.demo_SpringBoot_Web.repository.RoleRepository;
import ntt.com.example.demo_SpringBoot_Web.repository.UserRepository;
import ntt.com.example.demo_SpringBoot_Web.security.Jwt.JwtUtils;
import ntt.com.example.demo_SpringBoot_Web.service.impl.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
	public class AuthController {	@Autowired
	 AuthenticationManager authenticationManager;
	
	 @Autowired
	 UserRepository userRepository;
	
	 @Autowired
	 RoleRepository roleRepository;
	
	 @Autowired
	 PasswordEncoder encoder;
	
	 @Autowired
	 JwtUtils jwtUtils;

	 @PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody SiginRequest loginRequest) {
//		  try {
//			    Authentication authentication = authenticationManager.authenticate(
//			            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//			    // Authentication successful, do something with the authenticated user
//			} catch (AuthenticationException e) {
//			    // Authentication failed, handle the exception
//			    logger.error("Authentication error: " + e.getMessage());
//			    throw new BadCredentialsException("Invalid username or password");
//			}

	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

//	    System.out.println(authentication); 
	    SecurityContextHolder.getContext().setAuthentication(authentication);  
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();     // // pass, name, id, autho == generateJwtToken
	    List<String> roles = userDetails.getAuthorities().stream()
	        .map(item -> item.getAuthority())
	        .collect(Collectors.toList());

	    return ResponseEntity.ok(new JwtResponse(jwt, 
	                         userDetails.getId(), 
	                         userDetails.getUsername(), 
	                         userDetails.getEmail(), 
	                         roles));
	  }
	 
	 @PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Username is already taken!"));
	    }

	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Email is already in use!"));
	    }

	    // Create new user's account
	    UserEntity user = new UserEntity(signUpRequest.getUsername(), 
	               signUpRequest.getEmail(),
	               encoder.encode(signUpRequest.getPassword()));

	    Set<String> strRoles = signUpRequest.getRole();
	    Set<RoleEntity> roles = new HashSet<>();

	    if (strRoles == null) {
	    	RoleEntity userRole = roleRepository.findByName(ERoleEntity.ROLE_USER)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	      roles.add(userRole);
	    } else {
	      strRoles.forEach(role -> {
	        switch (role) {
	        case "admin":
	        	RoleEntity adminRole = roleRepository.findByName(ERoleEntity.ROLE_ADMIN)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(adminRole);

	          break;
	        case "mod":
	        	RoleEntity modRole = roleRepository.findByName(ERoleEntity.ROLE_MODERATOR)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(modRole);

	          break;
	        default:
	        	RoleEntity userRole = roleRepository.findByName(ERoleEntity.ROLE_USER)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(userRole);
	        }
	      });
	    }

	    user.setRoles(roles);
	    userRepository.save(user);

	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	  }
}
