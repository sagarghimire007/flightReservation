package com.udemy.flightReservation.utill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender sender;

    public void sendItinerary(String toAddress, String filePath){
        MimeMessage message = sender.createMimeMessage();

        try {
           MimeMessageHelper messageHelper = new MimeMessageHelper(message, true); // place true if there are attachments to pass
            messageHelper.setTo(toAddress);
            messageHelper.setSubject("Itinerary for your flights.");
            messageHelper.setText("Please find your Itinerary attached.");
            messageHelper.addAttachment("Itinerary", new File(filePath));
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
