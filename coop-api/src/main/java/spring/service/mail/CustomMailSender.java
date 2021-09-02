package spring.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

import static spring.util.Constants.RESET_PASSWORD_URL;
import static spring.util.Constants.VERIFY_URL;


public class CustomMailSender {


    private final JavaMailSender mailSender;

    @Autowired
    public CustomMailSender(JavaMailSender mailSender) {
        this.mailSender=mailSender;
    }

    public void sendVerificationEmail(String fullName, String email, String token){

        String subject = "Please verify your registration";
        String senderName = "TripWithMe team";
        String mailContent = "<p>Dear " + fullName + ",<p>";
        mailContent+="<p>You have requested to reset your password<p>";
        mailContent+="<p>Click the link below to change your password<p>";

        String verifyUrl = generateEmailVerificationTokenUrl(token);

        mailContent+="<h3><a href=\"" + verifyUrl+ "\">Change my password</a></h3>";

        mailContent+="<p>Ignore this email if you do remember your password, or you have not made the request";

        mailContent+="<p>Thank you<br>The TripWithMe Team</p>";


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("trip.with.me.bg@gmail.com",senderName);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(mailContent,true);

            mailSender.send(message);


        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }



    private String generateEmailVerificationTokenUrl(String token){
        return VERIFY_URL+token;
    }

    private String generateResetPasswordVerificationTokenUrl(String token){
        return RESET_PASSWORD_URL+token;
    }

    public void sentResetPasswordEmail(String fullName, String email, String token) {
        String subject = "Please verify your password reset";
        String senderName = "TripWithMe team";
        String mailContent = "<p>Dear " + fullName + ",<p>";
        mailContent+="<p>Please click the link below to reset your password:<p>";

        String verifyUrl = generateResetPasswordVerificationTokenUrl(token);

        mailContent+="<h3><a href=\"" + verifyUrl+ "\">VERIFY</a></h3>";

        mailContent+="<p>Thank you<br>The TripWithMe Team</p>";


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("trip.with.me.bg@gmail.com",senderName);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(mailContent,true);

            mailSender.send(message);


        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
