package com.CSMIS.CSMISKhaingFamily;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TestHomePageController {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    
    @Test
    public void testDateRegister() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/lunchRegister"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    
    // Add more test cases as needed
    @Test
    public void testOtpError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/otpError"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("forgot_password_form"));
    }
    
    @Test
    public void testOtpSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/otpSuccess"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("otp_form"));
    }
    
    @Test
    public void testOtpAcceptError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/otpAcceptError"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("otp_form"));
    }
    
    @Test
    public void testOtpAcceptSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/otpAcceptOtpError"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("otp_form"));
    }
    
    @Test
    public void testOtpResetPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/otpResetPassword"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("changePw"));
    }
    
    @Test
    public void testAcceptOtpValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/otpAccept")
            .param("otp", "123456")
            .param("email", "example@example.com"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    
    @Test
    public void testAcceptOtpInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/otpAccept")
            .param("otp", "123456")
            .param("email", "invalid@example.com"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
           
    }
    
    @Test
    public void testAcceptOtpExpired() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/otpAccept")
            .param("otp", "123456")
            .param("email", "example@example.com"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
           
    }
   
    
    @Test
    public void testProcessResetPasswordInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/reset_password")
            .sessionAttr("email", "example@example.com")
            .param("password", "newPassword")
            .param("cpassword", "invalidPassword"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
            
    }
    
   
    
    @Test
    public void testShowForgotPasswordForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/forgot_password"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("forgot_password_form"));
    }
    
    @Test
    public void testSendOtp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/otpSend")
            .param("email", "example@example.com"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("otp_form"));
    }
}
