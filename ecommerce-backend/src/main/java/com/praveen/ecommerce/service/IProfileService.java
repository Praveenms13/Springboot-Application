package com.praveen.ecommerce.service;

import com.praveen.ecommerce.dto.ProfileRequestDto;
import com.praveen.ecommerce.dto.ProfileResponseDto;

public interface IProfileService {
    ProfileResponseDto getProfile();
    ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto);
}
