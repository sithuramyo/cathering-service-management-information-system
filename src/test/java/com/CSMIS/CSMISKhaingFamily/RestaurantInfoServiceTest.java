package com.CSMIS.CSMISKhaingFamily;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RestaurantInfoServiceTest {
    
    @Mock
    private RestaurantInfoRepository repo;
    
    @InjectMocks
    private RestaurantInfoService restaurantInfoService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void deleteOrder_shouldDeleteOrderAndReturnResult() {
        // Arrange
        String orderId = "123";
        
        // Act
        int result = restaurantInfoService.deleteOrder(orderId);
        
        // Assert
        verify(repo, times(1)).deleteById(orderId);
        assertEquals(1, result);
    }
}
