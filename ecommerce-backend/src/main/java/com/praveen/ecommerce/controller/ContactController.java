package com.praveen.ecommerce.controller;

import com.praveen.ecommerce.dto.ContactRequestDto;
import com.praveen.ecommerce.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final IContactService iContactService;

    @PostMapping
    public ResponseEntity<String> saveContacts(@RequestBody ContactRequestDto contactRequestDto) {
        iContactService.saveContact(contactRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).
                body("Request Processed Successfully");
    }
}
