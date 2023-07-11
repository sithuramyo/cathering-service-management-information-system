package com.CSMIS.CSMISKhaingFamily;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.CSMIS.CSMISKhaingFamily.DAO.ToogleRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.ToogleService;

import static org.mockito.Mockito.*;

public class TestToogleService {

    @Mock
    private ToogleRepository toogleRepository;

    @InjectMocks
    private ToogleService toogleService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateToogleWithEmail() {
        String toogle = "enabled";
        String email = "test@example.com";
        int expectedResult = 1;

        // Configure the mock repository to return the expected result
        when(toogleRepository.updateWithEmail(toogle, email)).thenReturn(expectedResult);

        // Call the updateToogleWithEmail method
        int result = toogleService.updateToogleWithEmail(toogle, email);

        // Verify that toogleRepository.updateWithEmail() was called with the correct arguments
        verify(toogleRepository, times(1)).updateWithEmail(toogle, email);

        // Perform assertions on the result
        Assertions.assertEquals(expectedResult, result);
    }
}

