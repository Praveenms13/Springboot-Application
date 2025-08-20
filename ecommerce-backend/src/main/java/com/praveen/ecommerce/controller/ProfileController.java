package com.praveen.ecommerce.controller;

import com.praveen.ecommerce.dto.ProfileRequestDto;
import com.praveen.ecommerce.dto.ProfileResponseDto;
import com.praveen.ecommerce.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<ProfileResponseDto> updateProfile(@Validated @RequestBody ProfileRequestDto profileDto) {
        ProfileResponseDto profileResponseDto = iProfileService.updateProfile(profileDto);
        return ResponseEntity.ok(profileResponseDto);
    }
}
