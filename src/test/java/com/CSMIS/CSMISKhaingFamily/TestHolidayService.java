package com.CSMIS.CSMISKhaingFamily;

import com.CSMIS.CSMISKhaingFamily.DAO.HolidayRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.HolidayService;
import com.CSMIS.CSMISKhaingFamily.Entity.HolidayData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TestHolidayService {

    @Autowired
    private HolidayService holidayService;

    @MockBean
    private HolidayRepository holidayRepository;

    @BeforeEach
    public void setup() {
        // Define mock behavior for holidayRepository methods
        List<HolidayData> mockHolidayList = new ArrayList<>();
        mockHolidayList.add(new HolidayData(/* set properties */));
        mockHolidayList.add(new HolidayData(/* set properties */));

        when(holidayRepository.findAll()).thenReturn(mockHolidayList);
        when(holidayRepository.save(any(HolidayData.class))).thenReturn(new HolidayData(/* set properties */));
    }

    @Test
    public void testSaveData() {
        HolidayData data = new HolidayData(/* set properties */);
        holidayService.saveData(data);

        // Verify that holidayRepository.save() was called with the correct data
        verify(holidayRepository, times(1)).save(data);
    }

    @Test
    public void testGetAllHoliday() {
        List<HolidayData> holidayList = holidayService.getAllHoliday();

        // Verify that holidayRepository.findAll() was called
        verify(holidayRepository, times(1)).findAll();

        // Perform assertions on the returned holidayList
        Assertions.assertEquals(2, holidayList.size());
        // Additional assertions as needed
    }
}
