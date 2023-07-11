package com.CSMIS.CSMISKhaingFamily;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.CSMIS.CSMISKhaingFamily.DAO.AdminMenuImportRepo;
import com.CSMIS.CSMISKhaingFamily.DAO.AdminMenuImportService;
import com.CSMIS.CSMISKhaingFamily.Entity.MenuImportData;

public class TestAdminMenuImportService {
  
  @Mock
  private AdminMenuImportRepo repo;
  
  @InjectMocks
  private AdminMenuImportService service;
  
  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }
  
  @Test
  public void testCreateCatering() {
    // Create a sample MenuImportData object
    MenuImportData catering = new MenuImportData();
    catering.setId((long) 1);
    catering.setFilename("Catering 1");
//    catering.setDescription("Sample catering");
    
    // Mock the repository's save method
    when(repo.save(catering)).thenReturn(catering);
    
    // Call the service method
    MenuImportData result = service.createCatering(catering);
    
    // Verify the result
    assertEquals(catering, result);
  }
  
  @Test
  public void testGetAllCatering() {
    // Create a list of sample MenuImportData objects
    List<MenuImportData> cateringList = new ArrayList<>();
    MenuImportData catering1 = new MenuImportData();
    catering1.setId((long) 1);
    catering1.setFilename("Catering 1");
//    catering1.setDescription("Sample catering 1");
    cateringList.add(catering1);
    MenuImportData catering2 = new MenuImportData();
    catering2.setId((long) 2);
    catering2.setFilename("Catering 2");
//    catering2.setDescription("Sample catering 2");
    cateringList.add(catering2);
    
    // Mock the repository's findAll method
    when(repo.findAll()).thenReturn(cateringList);
    
    // Call the service method
    List<MenuImportData> result = service.getAllCatering();
    
    // Verify the result
    assertEquals(cateringList, result);
  }
}
