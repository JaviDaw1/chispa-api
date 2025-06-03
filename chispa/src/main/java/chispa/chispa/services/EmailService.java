package chispa.chispa.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Service for sending emails.
 * Currently handles password reset emails.
 */
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    /**
     * Sends a password reset email with a token.
     *
     * @param to    the recipient email address
     * @param token the reset token to include in the email
     */
    public void sendResetPasswordEmail(String to, String token) {
        // Email content configuration
        String subject = "Password Recovery";
        String resetUrl = "http://localhost:5173/reset-password?token=" + token;
        String text = "Click the following link to reset your password:\n" + resetUrl;

        // Create and send email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}