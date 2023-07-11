package com.CSMIS.CSMISKhaingFamily;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.CSMIS.CSMISKhaingFamily.Controller.AboutUsController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AboutUsController.class)
public class TestAboutUsController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AboutUsController aboutUsController;

    @Test
    public void testAboutUsPage() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("about"));
    }
}
