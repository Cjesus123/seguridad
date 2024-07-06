package com.tutorial.authservice.emailpassword.service;

import com.tutorial.authservice.emailpassword.dto.EmailValuesDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;

@Service
public class EmailService {

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value(value = "${mail.urlFront}")
    private String emailFront;

    public void sendEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("huancocristian@gmail.com");
        message.setTo("huancocristian@gmail.com");
        message.setSubject("Prueba envio email simple");
        message.setText("Contenido");
        mailSender.send(message);
    }

    public void sendEmailTemplate(EmailValuesDTO emailValuesDTO){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try{
            HashMap<String,Object> model = new HashMap<>();
            model.put("username",emailValuesDTO.getUsername());
            model.put("url",emailFront + emailValuesDTO.getTokenPassword());
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            Context context = new Context();
            context.setVariables(model);
            String htmlText = templateEngine.process("mailTemplate",context);
            helper.setFrom(emailValuesDTO.getMailFrom());
            helper.setTo(emailValuesDTO.getMailTo());
            helper.setSubject(emailValuesDTO.getSubject());
            helper.setText(htmlText, true);
            mailSender.send(mimeMessage);

        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
