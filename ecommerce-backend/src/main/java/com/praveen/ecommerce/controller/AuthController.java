package com.praveen.ecommerce.controller;

import com.praveen.ecommerce.dto.LoginRequestDto;
import com.praveen.ecommerce.dto.LoginResponseDto;
import com.praveen.ecommerce.dto.RegisterRequestDto;
import com.praveen.ecommerce.dto.UserDto;
import com.praveen.ecommerce.entity.Customer;
import com.praveen.ecommerce.repository.CustomerRepository;
import com.praveen.ecommerce.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> apiLogin(
            @RequestBody LoginRequestDto loginRequestDto,
            HttpServletRequest request
    ) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.username(),
                            loginRequestDto.password()
                    )
            );
            String jwtToken = jwtUtil.generateJwtToken(authentication, request.getRemoteAddr());
            UserDto userDto = new UserDto();
            var loggedInUser = (User) authentication.getPrincipal();
            userDto.setName(loggedInUser.getUsername());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(), userDto, jwtToken));
        } catch (BadCredentialsException e) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        } catch (AuthenticationException e) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Authentication Failed");
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(registerRequestDto, customer);
        customer.setPasswordHash(passwordEncoder.encode(registerRequestDto.getPassword()));
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Registration Successful");
    }

    private ResponseEntity<LoginResponseDto> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new LoginResponseDto(message, null, null));
    }
}
