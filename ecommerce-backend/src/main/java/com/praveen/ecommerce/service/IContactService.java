package com.praveen.ecommerce.service;

import com.praveen.ecommerce.dto.ContactRequestDto;
import com.praveen.ecommerce.dto.ProductDto;
import com.praveen.ecommerce.entity.Contact;

import java.util.List;

public interface IContactService {
    boolean saveContact(ContactRequestDto contactRequestDto);
}
