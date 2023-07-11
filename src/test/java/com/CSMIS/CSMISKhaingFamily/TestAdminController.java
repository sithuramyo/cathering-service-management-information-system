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
public class TestAdminController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/list"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testImportList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/importlist"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testUpdateHoliday() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/updateHoliday")
                .param("date", "2023-01-01")
                .param("name", "New Year")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testCheckAvoidMeatExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/checkAvoidMeatExist")
                .param("name", "Chicken")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testUpdateAvoidMeat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/updateAvoidMeat")
                .param("id", "1")
                .param("name", "Beef")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    
    @Test
    public void testDeleteAvoidMeat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/deleteAvoidMeat")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
               
    }

    @Test
    public void testDeleteFeedback() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/deleteFeedback")
                .param("rowId", "1")
                .param("activeTab", "feedback")
                .param("returnUrl", "/admin/list"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
                
    }

    @Test
    public void testDeleteAllFeedback() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/deleteAllFeedback"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testListByDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/list/{date}", "2023-01-01"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    
    @Test
    public void testDisplayshowForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/cateringStart"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testDisplaycateringForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/cateringRegistration"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testDisplayupdatecateringForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/cateringUpdate"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/updateEmployee")
                .param("staffid", "12345")
                .param("role", "Manager")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/deleteEmployee")
                .param("staffid", "12345")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }



    // Add more test cases as needed

}
