package com.CSMIS.CSMISKhaingFamily;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.CSMIS.CSMISKhaingFamily.Controller.AdminContactController;
import com.CSMIS.CSMISKhaingFamily.DAO.ContactInfoRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.ContactInfo;

class TestAdminContactController {

    private MockMvc mockMvc;

    @Mock
    private ContactInfoRepository contactInfoRepository;

    @InjectMocks
    private AdminContactController adminContactController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminContactController).build();
    }

    @Test
    void testSaveContactInfo_ValidInput() throws Exception {
        ContactInfo savedContact = new ContactInfo();
        savedContact.setId((int) 1L);
        savedContact.setEmail("test@example.com");
        savedContact.setPhone("1234567890");
        savedContact.setAddress("Sample Address");

        when(contactInfoRepository.save(any())).thenReturn(savedContact);

        mockMvc.perform(post("/admin/contactInfo")
                .param("email", "test@example.com")
                .param("phone", "1234567890")
                .param("address", "Sample Address")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Contact information saved successfully."));

        verify(contactInfoRepository).save(any());
    }

    @Test
    void testSaveContactInfo_InvalidInput() throws Exception {
        mockMvc.perform(post("/admin/contactInfo")
                .param("email", "")
                .param("phone", "1234567890")
                .param("address", "Sample Address")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Please enter valid contact information."));

        mockMvc.perform(post("/admin/contactInfo")
                .param("email", "test@example.com")
                .param("phone", "")
                .param("address", "Sample Address")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Please enter valid contact information."));

        mockMvc.perform(post("/admin/contactInfo")
                .param("email", "test@example.com")
                .param("phone", "1234567890")
                .param("address", "")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Please enter valid contact information."));

//        verify(contactInfoRepository).save(any());
    }
}
