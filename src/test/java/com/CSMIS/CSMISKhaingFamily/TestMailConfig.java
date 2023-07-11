package com.CSMIS.CSMISKhaingFamily;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestMailConfig{

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    public void testJavaMailSenderNotNull() {
        assertNotNull(javaMailSender, "JavaMailSender must not be null");
    }

}
