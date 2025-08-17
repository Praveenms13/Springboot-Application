package com.praveen.ecommerce.util;

import com.praveen.ecommerce.constants.ApplicationConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final Environment env;

    public String generateJwtToken(Authentication authentication, String ipAddress) {
        String secret = env.getProperty(
                ApplicationConstants.JWT_SECRET_KEY,
                ApplicationConstants.JWT_SECRET_DEFAULT_VALUE
        );
        System.out.println("Secret:  " + env.getProperty(ApplicationConstants.JWT_SECRET_KEY));
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        User fetchedUser = (User) authentication.getPrincipal();

        return Jwts.builder()
                .issuer("Praveen's Sticker Shop")
                .subject(fetchedUser.getUsername())
                .claim("ip", ipAddress)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 15))
                .signWith(secretKey)
                .compact();
    }

}
