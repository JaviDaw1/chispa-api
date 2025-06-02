package chispa.chispa.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendResetPasswordEmail(String to, String token) {
        String subject = "Recuperación de contraseña";
        String resetUrl = "http://localhost:5173/reset-password?token=" + token;
        String text = "Haz clic en el siguiente enlace para restablecer tu contraseña:\n" + resetUrl;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}