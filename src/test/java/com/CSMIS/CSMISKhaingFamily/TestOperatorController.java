package com.CSMIS.CSMISKhaingFamily;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestOperatorController {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDisplayShowForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/operator/cateringStart"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testDisplayCateringForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/operator/cateringRegistration"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
               
    }

    @Test
    public void testDisplayUpdateCateringForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/operator/cateringUpdate"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
              
    }
}
