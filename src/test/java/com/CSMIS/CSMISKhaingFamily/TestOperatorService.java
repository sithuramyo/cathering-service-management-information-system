package com.CSMIS.CSMISKhaingFamily;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorService;

import java.time.Instant;

import static org.mockito.Mockito.*;

public class TestOperatorService {

    @Mock
    private OperatorRepository opRepository;

    @InjectMocks
    private OperatorService operatorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertUserWithPas() {
        String otpCode = "123456";
        Instant otpCreationTime = Instant.now();
        String email = "test@example.com";

        // Call the insertUserWithPas method
        int result = operatorService.insertUserWithPas(otpCode, otpCreationTime, email);

        // Verify that opRepository.updateWithEmail() was called with the correct arguments
        verify(opRepository, times(1)).updateWithEmail(otpCode, otpCreationTime, email);

        // Perform assertions on the result
        Assertions.assertEquals(1, result);
    }
}

