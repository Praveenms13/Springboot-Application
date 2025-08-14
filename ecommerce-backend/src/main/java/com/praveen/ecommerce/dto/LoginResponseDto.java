package com.praveen.ecommerce.dto;

public record LoginResponseDto(String message, UserDto user, String JwtToken) {
}
