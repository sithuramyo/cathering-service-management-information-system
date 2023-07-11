package com.CSMIS.CSMISKhaingFamily;

import com.CSMIS.CSMISKhaingFamily.Controller.ApiAdminController;
import com.CSMIS.CSMISKhaingFamily.DAO.*;
import com.CSMIS.CSMISKhaingFamily.Entity.*;
import com.CSMIS.CSMISKhaingFamily.Function.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestApiAdminController {
	@BeforeEach
	public void setup() {
	    MockitoAnnotations.openMocks(this);
	}
	
	


    @Mock
    private HolidayRepository holidayRepository;

    @Mock
    private ExcelReader excelReader;

    @Mock
    private DoorLogExcelReader doorLogExcelReader;

    @Mock
    private HolidayExcelReader holidayExcelReader;

    @Mock
    private AvoidMeatRepository avoidmeatRepository;

    @Mock
    private AdminMenuImportService service;

    @Mock
    private RegisterCateringRepository registerCateringRepository;

    @Mock
    private RegisterDateRepository registerDateRepository;

    @Mock
    private AvoidMeatListRepository avoidMeatListRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private RegisteredeatRepository registeredeatRepository;

    @Mock
    private RegistereduneatRepository registereduneatRepository;

    @Mock
    private UnregisteredeatRepository unregisteredeatRepository;

    @Mock
    private Registerfinal1Repository registerfianlRepository;

    @InjectMocks
    private ApiAdminController apiAdminController;

    @Test
    public void testCateringRegister() {
        // Create a sample RegisterCatering object
        RegisterCatering selectedDates = new RegisterCatering();
        
        // Mock the behavior of the registerCateringRepository
        Mockito.when(registerCateringRepository.save(selectedDates)).thenReturn(selectedDates);

        // Call the method
        apiAdminController.cateringRegister(selectedDates);

        // Verify the method invocation
        verify(registerCateringRepository, times(1)).save(selectedDates);
    }



    @Test
    public void testGetHolidayDates() {
        // Create a list of HolidayData objects
        List<HolidayData> holidayDataList = Arrays.asList(new HolidayData(), new HolidayData());

        // Set up the necessary mock
        when(holidayRepository.findAll()).thenReturn(holidayDataList);

        // Call the method
        List<HolidayData> result = apiAdminController.getHolidayDates();

        // Verify the method invocation and the returned result
        verify(holidayRepository, times(1)).findAll();
        assertEquals(holidayDataList, result);
    }

    @Test
    public void testGetRegisteredDate() {
        // Create a sample Principal object
        Principal principal = Mockito.mock(Principal.class);
        when(principal.getName()).thenReturn("test@example.com");

        // Create a sample Operator object
        Operator operator = new Operator();
        operator.setEmployeeEmail("test@example.com");

        // Set up the necessary mock
        when(operatorRepository.findByemployeeEmail("test@example.com")).thenReturn(operator);
        when(registerCateringRepository.getDate(operator.getDoorLogNo())).thenReturn(Arrays.asList("2023-05-23", "2023-05-24"));

        // Call the method
        List<String> result = apiAdminController.getRegisteredDate(principal);

        // Verify the method invocation and the returned result
        verify(operatorRepository, times(1)).findByemployeeEmail("test@example.com");
        verify(registerCateringRepository, times(1)).getDate(operator.getDoorLogNo());
        assertEquals(Arrays.asList("2023-05-23", "2023-05-24"), result);
        }
    @Test
    public void testGetAvoidmeatlist() {
        // Create a sample Principal object
        Principal principal = Mockito.mock(Principal.class);
        when(principal.getName()).thenReturn("test@example.com");

        // Create a sample Operator object
        Operator operator = new Operator();
        operator.setEmployeeEmail("test@example.com");

        // Set up the necessary mock
        when(operatorRepository.findByemployeeEmail("test@example.com")).thenReturn(operator);
        when(registerCateringRepository.getMeat(operator.getDoorLogNo())).thenReturn(Arrays.asList("Chicken", "Beef"));

        // Call the method
        List<String> result = apiAdminController.getAvoidmeatlist(principal);

        // Verify the method invocation and the returned result
        verify(operatorRepository, times(1)).findByemployeeEmail("test@example.com");
        verify(registerCateringRepository, times(1)).getMeat(operator.getDoorLogNo());
        assertEquals(Arrays.asList("Chicken", "Beef"), result);
    }

    // Add more test methods for other API endpoints...

    @Test
    public void testImportExcel() throws IOException, MessagingException {
        // Create a sample MultipartFile object
        MockMultipartFile file = new MockMultipartFile("employeeFile", "employees.xlsx", "application/vnd.ms-excel", new byte[]{});

        // Set up the necessary mocks
        ValidationResult validationResult = new ValidationResult();
        validationResult.addError("Invalid file format");
        when(excelReader.validateExcel(file)).thenReturn(validationResult);

        // Call the method
        String result = apiAdminController.importExcel(file);

        // Verify the method invocation and the returned result
        verify(excelReader, times(1)).importOperator(file);
        verify(excelReader, times(1)).validateExcel(file);
        assertEquals("Invalid file format", result);
    }

    // Add more test methods for other file import endpoints...
}
