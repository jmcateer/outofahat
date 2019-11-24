package com.jkg.www.outofahat.service.impl;

import com.jkg.www.outofahat.service.valueobject.model.ContactInfo;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    private Logger logger = LoggerFactory.getLogger(EmailService.class);

   private final String fromEmail;

    public JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender,
            @Value("${spring.mail.username") String fromEmail) {
        this.javaMailSender = javaMailSender;
        this.fromEmail = fromEmail;
    }

    public String sendInviteEmail(
            ContactInfo contactInfo,
            String assignedTo) {
        String body = generateBody(contactInfo.getFirst(), assignedTo);
        sendMail(contactInfo.getEmail(), "Ornament Exchange 2019", body);
        return "Sent";
    }

    private void sendMail(
            String to,
            String subject,
            String body) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);

            helper.setFrom(fromEmail);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(body, false);//true indicates body is html
            javaMailSender.send(message);
        } catch (Exception ex) {
            logger.error("email failed for " + to, ex);
        }
    }

    private String generateBody(String first, String assignedTo) {
        String template = "Hiya, <person>.\n"
                + "This is an automated message sent from \"Out of a Hat\" to let you know to whom you will present this"
                + "year's ornament to. \n"
                + "That lucky person is: <to>.\n\n"
                + "Have a wonderful Christmas!\n\n"
                + "Yours truly, \n"
                + "\"Out of a Hat\"";

        return template.replace("<person>", first).replace("<to>", assignedTo.replace("_", ""));
    }
}
