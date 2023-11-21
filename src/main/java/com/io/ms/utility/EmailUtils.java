package com.io.ms.utility;


import com.io.ms.constant.AppConstants;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
    private static Logger logger = LoggerFactory.getLogger(EmailUtils.class);

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEmail(String to, String subject, String body)  {
        boolean isSent = false;
        try {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setTo(to);
        //mimeMessageHelper.setCc("");
        mimeMessageHelper.setText(body, true);
        mailSender.send(mimeMessageHelper.getMimeMessage());
        isSent = true;
        } catch (Exception e) {
            logger.error(AppConstants.EXCEPTION_OCCURRED  + e.getMessage(), e);
        }
        return isSent;
    }

    public boolean sendEmailWithCc(String to,String[] cc, String subject, String body)  {
        boolean isSent = false;
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setText(body, true);
            mailSender.send(mimeMessageHelper.getMimeMessage());
            isSent = true;
        } catch (Exception e) {
            logger.error(AppConstants.EXCEPTION_OCCURRED  + e.getMessage(), e);
        }
        return isSent;
    }
}
