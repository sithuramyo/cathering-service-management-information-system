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
public class TestCreativeInvoiceController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testStudentRegister() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/setupInvoice"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    // Add more test cases as needed

}
