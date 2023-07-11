package com.CSMIS.CSMISKhaingFamily;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.CSMIS.CSMISKhaingFamily.Controller.AdminAccController;
import com.CSMIS.CSMISKhaingFamily.Controller.OperatorAccController;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;

class TestOperatorAccController {

    private MockMvc mockMvc;

    @Mock
    private OperatorRepository operatorRepository;

    @InjectMocks
    private OperatorAccController operatorAccController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(operatorAccController).build();
    }

    @Test
    void testSendEmail_ValidInput() throws Exception {
        HttpSession session = mock(HttpSession.class);
        Operator savedOperator = new Operator();
        savedOperator.setId(1L);
        savedOperator.setEmployeeName("John Doe");
        savedOperator.setEmployeeEmail("john@example.com");

//        when(operatorRepository.updateWithEmail1(any(), any(), any())).thenReturn(1);

        mockMvc.perform(post("/operator/account")
                .param("name", "John Doe")
                .param("email", "john@example.com")
                .sessionAttr("email", "test@example.com")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Account details saved successfully."));

    }

    @Test
    void testSendEmail_InvalidInput() throws Exception {
        HttpSession session = mock(HttpSession.class);

        mockMvc.perform(post("/operator/account")
                .param("name", "")
                .param("email", "john@example.com")
                .sessionAttr("email", "test@example.com")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Please enter valid name and email."));

        mockMvc.perform(post("/operator/account")
                .param("name", "John Doe")
                .param("email", "")
                .sessionAttr("email", "test@example.com")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Please enter valid name and email."));

        mockMvc.perform(post("/operator/account")
                .param("name", "")
                .param("email", "")
                .sessionAttr("email", "test@example.com")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Please enter valid name and email."));

//        verify(operatorRepository).updateWithEmail1(any(), any(), any());
    }
}
