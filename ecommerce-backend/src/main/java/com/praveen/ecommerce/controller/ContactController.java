package com.praveen.ecommerce.controller;

import com.praveen.ecommerce.dto.ContactInfoDto;
import com.praveen.ecommerce.dto.ContactRequestDto;
import com.praveen.ecommerce.dto.ContactResponseDto;
import com.praveen.ecommerce.service.IContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final IContactService iContactService;
    private final ContactInfoDto contactInfoDto;

    @PostMapping
    public ResponseEntity<String> saveContacts(@Valid @RequestBody ContactRequestDto contactRequestDto) {
        iContactService.saveContact(contactRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).
                body("Request Processed Successfully");
    }

    @GetMapping
    public ResponseEntity<ContactInfoDto> getAllContactInfo() {
        return ResponseEntity.ok(contactInfoDto);
    }
}
