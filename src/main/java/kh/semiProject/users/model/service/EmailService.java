package kh.semiProject.users.model.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendVerificationEmail(String toEmail) {
        // 6자리 랜덤 인증번호 생성
        String code = String.valueOf((int)((Math.random() * 900000) + 100000));

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("[코딩 출발] 이메일 인증번호입니다.");
            helper.setText("인증번호: <b>" + code + "</b>", true);
            helper.setFrom("semiproject2025@gmail.com");

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return code;
    }
}