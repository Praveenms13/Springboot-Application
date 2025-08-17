package com.praveen.ecommerce.service;

import com.praveen.ecommerce.dto.ContactRequestDto;

public interface IContactService {
    boolean saveContact(ContactRequestDto contactRequestDto);
}
