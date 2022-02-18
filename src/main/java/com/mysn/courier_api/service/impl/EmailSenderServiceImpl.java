package com.mysn.courier_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl{

    private final JavaMailSender javaMailSender;

    private final static String EMAIL_BODY =
            "<h5>Hey %s,</h5><br>"+
            "</b><br><br><h2>You Have to deliver</h2>" +
            "<h3>Senders Name:- %s</h3>" +
            "<h3>Senders Address:- %s</h3>" +
            "<h3>Receivers Name:- %s</h3>" +
            "<h3>Receivers Address:- %s</h3>";

    public void sendSimpleEmail(String toEmail,String Hey,String senderName,String senderAddress,String receiverName,String receiverAddress)throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String from ="ameshmalindu006@gmail.com";

        boolean html = true;
        helper.setText(String.format(EMAIL_BODY,Hey,senderName, senderAddress, receiverName, receiverAddress), html);

        helper.setFrom(from);
        helper.setTo(toEmail);
        helper.setSubject(senderName+" place an order");

        javaMailSender.send(message);
        System.out.println("Mail Sent...");
    }

}
