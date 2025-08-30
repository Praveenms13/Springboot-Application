package com.praveen.ecommerce.service;

import com.praveen.ecommerce.dto.ContactRequestDto;
import com.praveen.ecommerce.dto.ContactResponseDto;

import java.util.List;

public interface IContactService {
    void saveContact(ContactRequestDto contactRequestDto);
    List<ContactResponseDto> getAllOpenMessages();
    void updateMessageStatus(Long contactId, String status);
}
