package com.CSMIS.CSMISKhaingFamily;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.CSMIS.CSMISKhaingFamily.DAO.OrderDetailRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OrderDetailService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class OrderDetailServiceTest {
    
    @Mock
    private OrderDetailRepository orderRepository;
    
    @InjectMocks
    private OrderDetailService orderDetailService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void deleteOrder_shouldDeleteOrderAndReturnResult() {
        // Arrange
        Long orderId = 1L;
        
        // Act
        int result = orderDetailService.deleteOrder(orderId);
        
        // Assert
        verify(orderRepository, times(1)).deleteByWeeklyOrderId(orderId);
        assertEquals(1, result);
    }
    
    @Test
    void deleteAllOrderDetail_shouldDeleteAllOrderDetailsAndReturnResult() {
        // Act
        int result = orderDetailService.deleteAllOrderDetail();
        
        // Assert
        verify(orderRepository, times(1)).deleteAllOrderDetails();
        assertEquals(1, result);
    }
}
