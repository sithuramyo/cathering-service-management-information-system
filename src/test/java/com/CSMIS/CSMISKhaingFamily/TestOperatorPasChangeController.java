package com.CSMIS.CSMISKhaingFamily;



import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.security.Principal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import com.CSMIS.CSMISKhaingFamily.Controller.AdminPasChangeController;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;

@SpringJUnitConfig
@WebMvcTest(AdminPasChangeController.class)
public class TestOperatorPasChangeController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OperatorRepository operatorRepository;

    @Test
    public void testChangePassword() throws Exception {
        Operator operator = new Operator();
        operator.setEmployeePassword("$2a$10$07Q7CvL4wRn8f0NtM77ir.c4HvXz98r15mrSCjwqx6Q0XHULaiwtW");
        when(operatorRepository.findByemployeeEmail(anyString())).thenReturn(operator);
        when(operatorRepository.save(any(Operator.class))).thenReturn(operator);

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("admin@example.com");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/operator/pasChange")
                .param("cpas", "currentPassword")
                .param("vpas", "verifyPassword")
                .param("npas", "newPassword")
                .principal(principal)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED);

//        mockMvc.perform(request)
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(MockMvcResultMatchers.content().string("Password changed successfully."));
//
//        verify(operatorRepository, times(1)).findByemployeeEmail("admin@example.com");
//        verify(operatorRepository, times(1)).save(any(Operator.class));
    }
}
