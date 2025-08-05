package com.praveen.ecommerce.controller;

import com.praveen.ecommerce.dto.ContactRequestDto;
import com.praveen.ecommerce.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final IContactService iContactService;

    @PostMapping
    public String saveContacts(@RequestBody ContactRequestDto contactRequestDto) {
        boolean isSaved = iContactService.saveContact(contactRequestDto);
        if (isSaved) {
            return "Request Processed Successfully";
        } else {
            return "An error occurred. Please try again later.";
        }
    }
}
