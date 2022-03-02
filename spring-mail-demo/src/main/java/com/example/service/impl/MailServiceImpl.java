package com.example.service.impl;

import com.example.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author Javen
 * @date 2022/2/14
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimple() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("1183014671@qq.com");
        simpleMailMessage.setTo("zjw940810@sina.com");
        simpleMailMessage.setSubject("BugBugBug");
        simpleMailMessage.setText("一杯茶，一根烟，一个Bug改一天");
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendComplicated() {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("1183014671@qq.com");
            messageHelper.setTo("zjw940810@sina.com");
            messageHelper.setSubject("Bug123456");
            messageHelper.setText("一杯茶，一根烟，一个Bug改一天！");
            messageHelper.addInline("bug.gif", new File("C:\\Users\\Jinwei Zhang\\Desktop\\ID Card (front).jpg"));
            messageHelper.addAttachment("bug.docx", new File("C:\\Users\\Jinwei Zhang\\Desktop\\123.docx"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
