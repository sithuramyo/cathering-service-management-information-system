package com.CSMIS.CSMISKhaingFamily;
import com.CSMIS.CSMISKhaingFamily.Controller.MonthlyInvoiceController;


import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.CreativeInvoice;
import com.CSMIS.CSMISKhaingFamily.Entity.RestaurantInfo;
import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyInvoice;
import com.CSMIS.CSMISKhaingFamily.Model.MonthlyInvoiceBean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



public class TestMonthlyInvoiceController {
	@Mock
    private HttpSession session;

    @Mock
    private WeeklyInvoiceRepository weeklyInvoiceRepository;

    @Mock
    private CreativeInvoiceRepository ciRepository;

    @Mock
    private RestaurantInfoRepository resRepo;
    
    @InjectMocks
    private MonthlyInvoiceController monthlyInvoiceController;

    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testPaidList1() {}

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    
    @Test
    public void testPaidList() {
        MockitoAnnotations.initMocks(this);
        
    	        
    	        MonthlyInvoiceController monthlyInvoiceController = new MonthlyInvoiceController();
        // Mock data
        List<WeeklyInvoice> mockWeeklyInvoices = new ArrayList<>();
        WeeklyInvoice weeklyInvoice = new WeeklyInvoice();
        weeklyInvoice.setCreateinvoiceid("1");
        weeklyInvoice.setResid("1");
        weeklyInvoice.setDescription("Test Description");
        weeklyInvoice.setPrice(100.0);
        weeklyInvoice.setTotalamount(200.0);
        mockWeeklyInvoices.add(weeklyInvoice);

        CreativeInvoice mockCreativeInvoice = new CreativeInvoice();
        mockCreativeInvoice.setApprovedby("Test Approver");
        mockCreativeInvoice.setCashier("Test Cashier");
        mockCreativeInvoice.setPaymethod("Test Payment Method");
        mockCreativeInvoice.setReceivedby("Test Receiver");

        RestaurantInfo mockRestaurantInfo = new RestaurantInfo();

        // Mock repositories
        when(weeklyInvoiceRepository.dateList()).thenReturn(mockWeeklyInvoices);
        when(ciRepository.getInvoiceInfo("1")).thenReturn(mockCreativeInvoice);
        when(resRepo.getResInfo("1")).thenReturn(mockRestaurantInfo);

        // Create the controller instance
        MonthlyInvoiceController monthlyInvoiceController1 = new MonthlyInvoiceController(
               );

        // Create the session
        MockHttpSession session = new MockHttpSession();

        // Call the method
    
//        String result = monthlyInvoiceController1.paidList(session);

        // Verify the result
//        assertEquals("monthlyinvoiceview", result);

        // Verify the session attributes
        List<MonthlyInvoiceBean> capturedList = (List<MonthlyInvoiceBean>) session.getAttribute("mlist");
//        double capturedTotalAmount = (double) session.getAttribute("totalAmount");

      
//        assertEquals("Test Description", capturedBean.getDescription());
//        // Verify other properties as needed
//
//        assertEquals(200.0, capturedTotalAmount, 0.0);
    }
}
