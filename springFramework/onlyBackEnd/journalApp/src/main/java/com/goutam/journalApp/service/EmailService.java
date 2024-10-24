package com.goutam.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body)
    {
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            javaMailSender.send(mail);
        }catch(Exception e)
        {
            System.out.println("Mail sending exception");
        }
    }

    public String getEmailBody()
    {
        return "Your registration with JournalApp has been successfully completed!\n" +
                "\n" +
                "Please note, this is a system-generated email—no replies will be monitored.\n" +
                "\n" +
                "Thank you for joining us!\n" +
                "\n" +
                "Best regards,\n" +
                "Team JournalApp";
    }

    public String getEmailSubject()
    {
        return "Registration Successful - JournalApp";
    }
}