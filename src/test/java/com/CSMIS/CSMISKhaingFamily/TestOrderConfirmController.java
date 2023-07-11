package com.CSMIS.CSMISKhaingFamily;



//import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;

import com.CSMIS.CSMISKhaingFamily.Controller.OrderConfirmController;
import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OrderDetailViewRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyOrderRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.AvoidMeat;
import com.CSMIS.CSMISKhaingFamily.Entity.OrderDetail;
import com.CSMIS.CSMISKhaingFamily.Entity.OrderDetailView;
import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyOrder;
import com.CSMIS.CSMISKhaingFamily.Model.CalculateInvoice;
import com.CSMIS.CSMISKhaingFamily.Model.OrderFormRequest;

public class TestOrderConfirmController {
    
    @Mock
    private AvoidMeatRepository avoidMeatRepository;
    
    @Mock
    private OrderDetailViewRepository orderDetailViewRepository;
    
    @Mock
    private WeeklyOrderRepository weeklyOrderRepository;
    
    @Mock
    private HttpSession httpSession;
    
    @InjectMocks
    private OrderConfirmController orderConfirmController;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testOrderConfirm() {
        // Mock data
        AvoidMeat avoidMeat1 = new AvoidMeat();
        avoidMeat1.setMeatName("Chicken");
        
        AvoidMeat avoidMeat2 = new AvoidMeat();
        avoidMeat2.setMeatName("Beef");
        
        List<AvoidMeat> avoidMeatList = new ArrayList<>();
        avoidMeatList.add(avoidMeat1);
        avoidMeatList.add(avoidMeat2);
        
        OrderDetailView orderDetailView = new OrderDetailView();
        orderDetailView.setWeekly_order_id(1L);
        orderDetailView.setEmployeecost(10.0);
        orderDetailView.setDatcost(5.0);
        orderDetailView.setTotalpax(5);
        orderDetailView.setDates("2023-05-24");
        orderDetailView.setQuantity(2);
        orderDetailView.setExtrapax(1);
        
        List<OrderDetailView> orderDetailViewList = new ArrayList<>();
        orderDetailViewList.add(orderDetailView);
        
        when(avoidMeatRepository.findAll()).thenReturn(avoidMeatList);
        when(orderDetailViewRepository.findLastByDates(anyString())).thenReturn(orderDetailView);
        when(weeklyOrderRepository.save(any(WeeklyOrder.class))).thenReturn(new WeeklyOrder());
        when(httpSession.getAttribute(eq("olist"))).thenReturn(new ArrayList<CalculateInvoice>());
        when(httpSession.getAttribute(eq("totalCount"))).thenReturn(0);}}
        
//        ModelMap model = new ModelMap();
//        String viewName = orderConfirmController.orderConfirm(model, httpSession);
        
        // Assert statements
//        assertEquals("orderform", viewName);
//        assertEquals(1, model.get("olist"));
//        assertEquals(5, model.get("totalCount"));
        
//        verify(avoidMeatRepository, times(1)).findAll();
//        verify(orderDetailViewRepository, times(5)).findLastByDates(anyString());
//        verify(weeklyOrderRepository, times(1
//        		)).save(any(WeeklyOrder.class));
//        verify(httpSession, times(1)).getAttribute(eq("olist"));
//        verify(httpSession, times(1)).getAttribute(eq("totalCount"));
//        }
//        }