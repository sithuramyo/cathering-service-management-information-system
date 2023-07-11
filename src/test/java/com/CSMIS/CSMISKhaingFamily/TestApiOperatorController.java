
package com.CSMIS.CSMISKhaingFamily;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.CSMIS.CSMISKhaingFamily.Controller.ApiOperatorController;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisterCateringRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisteredeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegistereduneatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.UnregisteredeatRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Entity.RegisterCatering;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestApiOperatorController {

    @MockBean
    private RegisterCateringRepository registerCateringRepository;

    @MockBean
    private OperatorRepository operatorRepository;

    @MockBean
    private RegisteredeatRepository registeredeatRepository;

    @MockBean
    private RegistereduneatRepository registereduneatRepository;

    @MockBean
    private UnregisteredeatRepository unregisteredeatRepository;

    @InjectMocks
    private ApiOperatorController apiOperatorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cateringRegister_ShouldSaveSelectedDatesAndDeleteNoRegisterDates() throws Exception {
        RegisterCatering selectedDates = new RegisterCatering();

//        when(registerCateringRepository.deleteRegisterCateringWithNoRegisterDate()).thenReturn(1);

        apiOperatorController.cateringRegister(selectedDates);

        verify(registerCateringRepository, times(1)).save(selectedDates);
        verify(registerCateringRepository, times(1)).deleteRegisterCateringWithNoRegisterDate();
    }

    @Test
    void getRegisteredDate_ShouldReturnListOfRegisteredDates() throws Exception {
        String email = "test@example.com";
        Principal prin = () -> email;
        Operator operator = new Operator();
        operator.setDoorLogNo("1L");
        when(operatorRepository.findByemployeeEmail(email)).thenReturn(operator);
        List<String> registeredDates = Arrays.asList("2023-06-01", "2023-06-02");
        when(registerCateringRepository.getDate(operator.getDoorLogNo())).thenReturn(registeredDates);

        List<String> result = apiOperatorController.getRegisteredDate(prin);

        verify(operatorRepository, times(1)).findByemployeeEmail(email);
        verify(registerCateringRepository, times(1)).getDate(operator.getDoorLogNo());

        // Perform assertions on the result
        // ...
    }

    // Similarly, write tests for other methods in the ApiOperatorController class

    // Utility method to convert object to JSON string
    private static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
