package com.CSMIS.CSMISKhaingFamily;

import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class RestaurantServiceTest {

    @Mock
    private RestaurantInfoRepository resRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateUserId_shouldReturnCorrectUserId() {
        // Arrange
        when(resRepository.countRes()).thenReturn(99);

        // Act
        String userId = restaurantService.generateUserId();

        // Assert
//        assertEquals("R-099", userId);
    }
}
