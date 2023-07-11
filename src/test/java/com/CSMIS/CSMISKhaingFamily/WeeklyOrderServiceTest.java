package com.CSMIS.CSMISKhaingFamily;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyOrderRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyOrderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class WeeklyOrderServiceTest {
    
    @Mock
    private WeeklyOrderRepository wRepo;
    
    @InjectMocks
    private WeeklyOrderService weeklyOrderService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void deleteAllOrder_shouldDeleteAllOrdersAndReturnResult() {
        // Act
        int result = weeklyOrderService.deleteAllOrder();
        
        // Assert
        verify(wRepo, times(1)).deleteAllWeeklyOrders();
        assertEquals(1, result);
    }
    
    @Test
    void insertStatusWithId_shouldUpdateStatusAndReturnResult() {
        // Arrange
        String status = "active";
        Long id = 123L;
        
        // Act
        int result = weeklyOrderService.insertStatusWithId(status, id);
        
        // Assert
        verify(wRepo, times(1)).updateStatusWithId(status, id);
        assertEquals(1, result);
    }
    
    @Test
    void updateStatus_shouldUpdateStatusToInactiveAndReturnResult() {
        // Act
        int result = weeklyOrderService.updateStatus();
        
        // Assert
        verify(wRepo, times(1)).updateStatusToInactive();
        assertEquals(1, result);
    }
}
