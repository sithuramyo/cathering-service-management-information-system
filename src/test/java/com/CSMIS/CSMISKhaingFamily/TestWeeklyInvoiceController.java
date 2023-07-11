package com.CSMIS.CSMISKhaingFamily;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.CSMIS.CSMISKhaingFamily.Controller.WeeklyInvoiceController;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyInvoiceService;
import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyInvoice;
import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyOrder;
import com.CSMIS.CSMISKhaingFamily.Model.CalculateInvoice;
import com.CSMIS.CSMISKhaingFamily.Model.WeeklyInvoiceRequest;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class TestWeeklyInvoiceController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeeklyInvoiceService weeklyInvoiceService;

    @Autowired
    private WeeklyInvoiceController weeklyInvoiceController;

    @Test
    public void invoicePaidTest() throws Exception {
        WeeklyInvoiceRequest request = new WeeklyInvoiceRequest();
        request.setStatus("Paid");

        WeeklyInvoice invoice = new WeeklyInvoice();
        invoice.setId(1L);
        invoice.setStatus("Paid");

   
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/invoicePaid")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
                
    }

    @Test
    public void orderReportTest() throws Exception {
        List<WeeklyOrder> orderList = new ArrayList<>();
        WeeklyOrder order1 = new WeeklyOrder();
        order1.setId(1L);
        WeeklyOrder order2 = new WeeklyOrder();
        order2.setId(2L);
        orderList.add(order1);
        orderList.add(order2);

 

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/orderReport"))
        .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
              
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void testSearchOrderReport() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/orderReportList")
                .param("start_date", "2023-01-01")
                .param("end_date", "2023-01-31")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
              
        // Add assertions for expected behavior and verify the results
    }

    @Test
    public void testSearchOrderReportWithInvalidDates() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/orderReportList")
                .param("start_date", "")
                .param("end_date", "")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
                
        // Add assertions for expected behavior and verify the results
    }

    @Test
    public void testResetOrderReportReset() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/orderReportListReset"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        // Add assertions for expected behavior and verify the results
    }
}
