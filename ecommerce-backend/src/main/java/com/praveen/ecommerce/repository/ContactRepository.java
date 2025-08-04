package com.praveen.ecommerce.repository;

import com.praveen.ecommerce.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}