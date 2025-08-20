package com.praveen.ecommerce.controller;

import com.praveen.ecommerce.dto.ProfileResponseDto;
import com.praveen.ecommerce.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final IProfileService iProfileService;
    @GetMapping
    public ResponseEntity<ProfileResponseDto> getProfile(Authentication authentication) {
        ProfileResponseDto profileResponseDto = iProfileService.getProfile();
        return ResponseEntity.ok(profileResponseDto);
    }
}
