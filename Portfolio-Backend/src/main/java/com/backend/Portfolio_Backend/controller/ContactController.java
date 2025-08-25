package com.backend.Portfolio_Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.Portfolio_Backend.entity.ContactForm;
import com.backend.Portfolio_Backend.repository.ContactRepository;
import com.backend.Portfolio_Backend.service.EmailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<?> submitContactForm (@Valid @RequestBody ContactForm contactForm){
        try{
            ContactForm savedForm = contactRepository.save(contactForm);

            emailService.sendContactNotification(savedForm);

            emailService.sendAutoResponse(savedForm);

            return ResponseEntity.ok().body("Message sent successfully.");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error submitting the form: " + e.getMessage());
        }
    }
}
