package com.praveen.ecommerce.service.impl;

import com.praveen.ecommerce.dto.ContactRequestDto;
import com.praveen.ecommerce.entity.Contact;
import com.praveen.ecommerce.repository.ContactRepository;
import com.praveen.ecommerce.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {
    private final ContactRepository contactRepository;

    @Override
    public boolean saveContact(ContactRequestDto contactRequestDto) {
        Contact contact = transformToEntity(contactRequestDto);
        contactRepository.save(contact);
        return true;
    }

    private Contact transformToEntity(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto, contact);
        return contact;
    }
}
