package com.CSMIS.CSMISKhaingFamily;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TestEmailController{

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSetCronExpression() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/remindSchedule")
                .param("datetime", "2023-06-01T10:00:00")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());    }

    @Test
    public void testSendEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/sendEmail")
                .param("username", "test@gmail.com")
                .param("password", "password")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    // Add more test cases as needed

}
