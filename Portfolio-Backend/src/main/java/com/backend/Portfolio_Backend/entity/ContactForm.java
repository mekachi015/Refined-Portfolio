package com.backend.Portfolio_Backend.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Document(collection = "contact_submissions")
public class ContactForm {

    @Id
    private String id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Message content is required")
    private String message;

    private LocalDateTime createdAt;

    public ContactForm() {
        this.createdAt = LocalDateTime.now();
    }

    public ContactForm(String name, String email, String message) {
        this();
        this.name = name;
        this.email = email;
        this.message = message;
    }
}
