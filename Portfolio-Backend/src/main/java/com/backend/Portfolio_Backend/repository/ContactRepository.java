package com.backend.Portfolio_Backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.Portfolio_Backend.entity.ContactForm;

public interface ContactRepository extends MongoRepository<ContactForm, String>{

}
