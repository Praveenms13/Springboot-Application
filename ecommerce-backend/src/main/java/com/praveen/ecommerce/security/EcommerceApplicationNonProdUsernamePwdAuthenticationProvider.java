package com.praveen.ecommerce.security;

import com.praveen.ecommerce.entity.Customer;
import com.praveen.ecommerce.entity.Role;
import com.praveen.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Profile("!prod")
@Component
@RequiredArgsConstructor
public class EcommerceApplicationNonProdUsernamePwdAuthenticationProvider implements AuthenticationProvider {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Customer customer = customerRepository.findByEmailOrMobileNumber(username, username).orElseThrow(
                () -> new UsernameNotFoundException(
                        "User details not found with user: " + username
                )
        );
        Set<Role> roles = customer.getRoles();
        List<SimpleGrantedAuthority> authorities = roles.
                stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
        return new UsernamePasswordAuthenticationToken(customer, null, authorities);
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
