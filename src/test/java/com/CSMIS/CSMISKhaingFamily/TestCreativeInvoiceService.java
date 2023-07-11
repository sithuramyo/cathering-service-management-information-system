package com.CSMIS.CSMISKhaingFamily;
import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatListRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class TestCreativeInvoiceService {

    @Mock
    private CreativeInvoiceRepository invoiceRepository;

    @Mock
    private AvoidMeatListRepository avoidRepo;

    @InjectMocks
    private CreativeInvoiceService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateUserIdWithCountLessThan10() {
        // Mock the countInvoices method to return 5
        when(invoiceRepository.countInvoices()).thenReturn(5);

        // Call the generateUserId method
        String userId = service.generateUserId();

        // Verify that the countInvoices method was called once
        verify(invoiceRepository, times(1)).countInvoices();

        // Verify the generated userId
        Assertions.assertEquals("CS001-20230206", userId);
    }

    @Test
    public void testGenerateUserIdWithCountBetween10And100() {
        // Mock the countInvoices method to return 50
        when(invoiceRepository.countInvoices()).thenReturn(50);

        // Call the generateUserId method
        String userId = service.generateUserId();

        // Verify that the countInvoices method was called once
        verify(invoiceRepository, times(1)).countInvoices();

        // Verify the generated userId
        Assertions.assertEquals("CS001-20230251", userId);
    }

    @Test
    public void testGenerateUserIdWithCountEqualTo100() {
        // Mock the countInvoices method to return 100
        when(invoiceRepository.countInvoices()).thenReturn(99); // Change the count to 99 to simulate count == 100

        // Call the generateUserId method
        String userId = service.generateUserId();

        // Verify that the countInvoices method was called once
        verify(invoiceRepository, times(1)).countInvoices();

        // Verify the generated userId
        Assertions.assertEquals("CS001-20230100", userId);
    }
}
