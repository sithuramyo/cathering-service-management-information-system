package com.CSMIS.CSMISKhaingFamily;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyInvoiceService;
import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyInvoice;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestWeeklyInvoiceService {

    @Mock
    private WeeklyInvoiceRepository weeklyInvoiceRepository;

    @InjectMocks
    private WeeklyInvoiceService weeklyInvoiceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetInvoiceById() {
        Long id = 1L;
        WeeklyInvoice expectedInvoice = new WeeklyInvoice();

        // Configure the mock repository to return the expected invoice
        when(weeklyInvoiceRepository.searchInvoice(id)).thenReturn(expectedInvoice);

        // Call the getInvoiceById method
        WeeklyInvoice result = weeklyInvoiceService.getInvoiceById(id);

        // Verify that weeklyInvoiceRepository.searchInvoice() was called with the correct argument
        verify(weeklyInvoiceRepository, times(1)).searchInvoice(id);

        // Perform assertions on the result
        Assertions.assertEquals(expectedInvoice, result);
    }

    @Test
    public void testGetPaidList() {
        List<WeeklyInvoice> expectedInvoiceList = new ArrayList<>();

        // Configure the mock repository to return the expected invoice list
        when(weeklyInvoiceRepository.paidList()).thenReturn(expectedInvoiceList);

        // Call the getPaidList method
        List<WeeklyInvoice> result = weeklyInvoiceService.getPaidList();

        // Verify that weeklyInvoiceRepository.paidList() was called
        verify(weeklyInvoiceRepository, times(1)).paidList();

        // Perform assertions on the result
        Assertions.assertEquals(expectedInvoiceList, result);
    }

    // Similarly, you can write tests for the remaining methods of WeeklyInvoiceService
}
