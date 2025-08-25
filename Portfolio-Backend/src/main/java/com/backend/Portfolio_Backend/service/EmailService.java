package com.backend.Portfolio_Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
import org.springframework.stereotype.Service;

import com.backend.Portfolio_Backend.entity.ContactForm;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactNotification(ContactForm contactForm){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("katlegomakoti@gmail.com");
        message.setSubject("New Contact Form Submission from " + contactForm.getName());

        String text = String.format(
            "You have received a new message from your portfolio contact form.%n%n" +
            "Name: %s%n" +
            "Email: %s%n%n" +
            "Message:%n%s",
            contactForm.getName(),
            contactForm.getEmail(),
            contactForm.getMessage()  
        );

        message.setText(text);

        mailSender.send(message);
    }

    public void sendAutoResponse(ContactForm contactForm){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(contactForm.getEmail());
        message.setSubject("Thank you for contacting me!");

        String text = String.format(
            "Dear %s,\n\nThank you for reaching out! " +
            "I have received your message and will get back to you as soon as possible.\n\n" +
            "Best regards,\nKatlego Makoti\nFull-Stack Developer",
            contactForm.getName()
        );
        
        message.setText(text);
        
        mailSender.send(message);
    }
}
