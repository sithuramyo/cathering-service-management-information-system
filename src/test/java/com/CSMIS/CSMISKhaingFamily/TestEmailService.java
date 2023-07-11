package com.CSMIS.CSMISKhaingFamily;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.CSMIS.CSMISKhaingFamily.DAO.EmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class TestEmailService {

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private Configuration configuration;

    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emailService = new EmailService(javaMailSender, configuration);
    }

    @Test
    public void testSendOtp() throws TemplateNotFoundException, IOException, TemplateException, MessagingException {
        String email = "test@example.com";
        String otpCode = "123456";

        MimeMessage mockMimeMessage = mock(MimeMessage.class);
        MimeMessageHelper mockMessageHelper = mock(MimeMessageHelper.class);

        when(javaMailSender.createMimeMessage()).thenReturn(mockMimeMessage);
        Template template = configuration.getTemplate("email-otpcode.ftl");

        StringWriter stringWriter = new StringWriter();


        
    }

    private Map<String, Object> getTemplateData(String otpCode) {
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("otpcode", otpCode);
        return templateData;
    }
}
