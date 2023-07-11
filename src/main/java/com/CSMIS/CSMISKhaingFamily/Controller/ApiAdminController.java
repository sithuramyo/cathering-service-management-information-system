package com.CSMIS.CSMISKhaingFamily.Controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.CSMIS.CSMISKhaingFamily.DAO.AdminMenuImportRepo;
import com.CSMIS.CSMISKhaingFamily.DAO.AdminMenuImportService;
import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatListRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.HolidayRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.HolidayService;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisterCateringRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisterDateRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisteredeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegistereduneatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.Registerfinal1Repository;
import com.CSMIS.CSMISKhaingFamily.DAO.UnregisteredeatRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.AvoidMeat;
import com.CSMIS.CSMISKhaingFamily.Entity.HolidayData;
import com.CSMIS.CSMISKhaingFamily.Entity.MenuImportData;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Entity.RegisterCatering;
import com.CSMIS.CSMISKhaingFamily.Function.DoorLogExcelReader;
import com.CSMIS.CSMISKhaingFamily.Function.DuplicateEntryException;
import com.CSMIS.CSMISKhaingFamily.Function.ExcelReader;
import com.CSMIS.CSMISKhaingFamily.Function.HolidayExcelReader;
import com.CSMIS.CSMISKhaingFamily.Function.MonthData;
import com.CSMIS.CSMISKhaingFamily.Function.ValidationResult;
import com.CSMIS.CSMISKhaingFamily.Function.ValitdateAvoid;
import com.CSMIS.CSMISKhaingFamily.Function.ValitedateHoliday;
import com.CSMIS.CSMISKhaingFamily.Function.WrongFileException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.apache.pdfbox.rendering.PDFRenderer;


@RestController
@RequestMapping("/api")
public class ApiAdminController {
	@Autowired
    private HolidayRepository holidayRepository;
	
	@Autowired
	private ExcelReader excelReader;
	
	@Autowired
	private DoorLogExcelReader doorLogExcelReader;
	
	@Autowired
	private HolidayExcelReader holidayExcelReader;

	@Autowired
	private AvoidMeatRepository avoidmeatRepository;


	@Autowired
	private AdminMenuImportService service;
	
	@Autowired
	private RegisterCateringRepository registerCateringRepository;
	
	@Autowired
	RegisterDateRepository registerDateRepository;
	
	@Autowired
    AvoidMeatListRepository avoidMeatListRepository;
	
	@Autowired
	private OperatorRepository operatorRepository;
	
	@Autowired
	private RegisteredeatRepository registeredeatRepository;
	
	@Autowired
	private ValitedateHoliday valitedate;
	
	@Autowired
	private RegistereduneatRepository registereduneatRepository;
	
	@Autowired
	private UnregisteredeatRepository unregisteredeatRepository;
	
	@Autowired
	Registerfinal1Repository registerfianlRepository;
	
	
	
	@Autowired
	private ValitdateAvoid valildateavoid;
	
	@PostMapping("/admin/dateregister")
	 @Transactional
	 public void cateringRegister(@RequestBody RegisterCatering selectedDates) {
	  registerCateringRepository.save(selectedDates);
	  avoidMeatListRepository.deleteAvoidMeatsWithNoRegisterDate();
	  registerCateringRepository.deleteRegisterCateringWithNoRegisterDate();
	 }
	
	@GetMapping("/admin/dates")
	public List<HolidayData> getHolidayDates() {
	    return holidayRepository.findAll();
	}
	
	@GetMapping("/admin/registereddate")
	public List<String> getRegisteredDate(Principal prin){
		Operator register = operatorRepository.findByemployeeEmail(prin.getName());
		List<String> getRegistered = registerCateringRepository.getDate(register.getDoorLogNo());
		System.out.println("Registered Date" +getRegistered);
		return getRegistered;
	}
	@GetMapping("/admin/avoidmeatlist")
	public List<String> getAvoidmeatlist(Principal prin){
		Operator register = operatorRepository.findByemployeeEmail(prin.getName());
		List<String> getAvoid = registerCateringRepository.getMeat(register.getDoorLogNo());
		System.out.println("Avoid Meat" +getAvoid);
		return getAvoid;
	}
	
	@GetMapping("/admin/registeredeat")
	public List<String> getRegisteredEat(Principal prin){
		Operator regsiter = operatorRepository.findByemployeeEmail(prin.getName());
		List<String> getregisteredEat = registeredeatRepository.getRDateForEat(regsiter.getDoorLogNo());
		return getregisteredEat;
	}
	
	@GetMapping("/admin/registerednoeat")
	public List<String> getRegisteredNoEat(Principal prin){
		Operator regsiter = operatorRepository.findByemployeeEmail(prin.getName());
		List<String> getregisteredNoEat = registereduneatRepository.getRDateForNoEat(regsiter.getDoorLogNo());
		return getregisteredNoEat;
	}
	
	@GetMapping("/admin/unregisteredeat")
	public List<String> getUnRegisteredEat(Principal prin){
		Operator regsiter = operatorRepository.findByemployeeEmail(prin.getName());
		List<String> getunregisteredEat = unregisteredeatRepository.getURDateForEat(regsiter.getDoorLogNo());
		return getunregisteredEat;
	}
	@PostMapping("/admin/importEmployee")
	@ResponseBody
	public String importExcel(@RequestParam("employeeFile") MultipartFile employeeFile)
	        throws IOException, MessagingException {
	    try {
	    	 excelReader.importOperator(employeeFile);
	        ValidationResult validationResult = excelReader.validateExcel(employeeFile);
	        if (validationResult.hasErrors()) {
	            List<String> errors = validationResult.getErrors();
	            return String.join(",", errors);
	        }
	       
	    } catch (WrongFileException w) { 
	        return w.getMessage();
	    }
	    
	    return "Success";
	}





	@PostMapping("/admin/importdoorlog")
	@ResponseBody
	public String importDoorlog(@RequestParam("doorlogFile") MultipartFile doorlogFile) throws IOException,MessagingException  {
	    try {
	    	doorLogExcelReader.importDoorlogExcel(doorlogFile);
	        ValidationResult validationResult = doorLogExcelReader.validateDoorlog(doorlogFile);
	        if (validationResult.hasErrors()) {
	            List<String> errors = validationResult.getErrors();
	            return String.join(",", errors); 
	        }        
	        
	    } catch (WrongFileException e) {
	        return e.getMessage();
	    }
	    return "Success";
	}
	@PostMapping("/admin/importHoliday")
	@ResponseBody
	public String importHolidayExcel(@RequestParam("holidayFile") MultipartFile holidayFile) throws IOException, MessagingException {
		try {
			holidayExcelReader.holidayimportExcel(holidayFile);
			ValidationResult validationResult = holidayExcelReader.validateHoliday(holidayFile);
			if(validationResult.hasErrors()) {
				List<String> errors = validationResult.getErrors();
	            return String.join(",", errors); 
			}		
		}catch (WrongFileException e) {
	        return e.getMessage();
	    }	
		return "Success";
	}
	@PostMapping("/admin/importavoidMeat")
	@ResponseBody
	public String importAvoidMeat(@RequestParam("meatName") String avoidMeat) {
		
		try {
			ValidationResult validationResult = valildateavoid.validateAvoid(avoidMeat);
			if(validationResult.hasErrors()) {
			List<String> errors = validationResult.getErrors();
            return String.join(",", errors);          
			}
			valildateavoid.avoidAdd(avoidMeat);
		}catch(Exception e) {
			return e.getMessage();
		}
	    return "Success";
	}
	@PostMapping("/admin/upload")
	@ResponseBody
	public ResponseEntity<String> uploadFile(@RequestParam("menuFile") MultipartFile menuFile) {
	    try {
	        String originalFilename = menuFile.getOriginalFilename();
	        System.out.println("OriginalFilename: " + originalFilename);
	        String destinationFolder = "static/img/";
	        Path destinationPath = Paths.get("src", "main", "resources", destinationFolder);
	        Path destinationFile = destinationPath.resolve(originalFilename);
	        Files.copy(menuFile.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
	        if (originalFilename.toLowerCase().endsWith(".pdf")) {
	            PDDocument document = PDDocument.load(destinationFile.toFile());
	            PDFRenderer renderer = new PDFRenderer(document);
	            BufferedImage image = renderer.renderImageWithDPI(0, 300);
	            document.close();
	            String generatedFilename = originalFilename + ".jpeg";
	            Path imageDestinationFile = destinationPath.resolve(generatedFilename);
	            File outputImageFile = imageDestinationFile.toFile();
	            ImageIO.write(image, "jpeg", outputImageFile);
	            MenuImportData menuImportData = new MenuImportData();
	            menuImportData.setFilename(generatedFilename);
	            service.createCatering(menuImportData);
	            
	            return ResponseEntity.status(HttpStatus.OK).body("File uploaded and converted successfully.");
	        }
	        MenuImportData menuImportData = new MenuImportData();
	        menuImportData.setFilename(originalFilename);
	        service.createCatering(menuImportData);
	        
	        return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully.");
	    } catch (IOException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file.");
	    }
	}
	@PostMapping("/admin/importHolidaybymanually")
	@ResponseBody
	public String importHolidayManully(@RequestParam("holidayname") String holidayName , @RequestParam("holidaydate") String holidayDate) {
		try {
			ValidationResult validationResult = valitedate.validateHoliday(holidayDate);
			if(validationResult.hasErrors()) {
			List<String> errors = validationResult.getErrors();
            return String.join(",", errors);          
			}
			valitedate.addHoiday(holidayDate, holidayName);
			}catch(Exception e) {
				return e.getMessage();
		}
		
		return "Success";
	}
	@PostMapping("/admin/getmonth")
	public MonthData getMonth(@RequestParam("month") String month, @RequestParam("doorlogno") String doorlogno) {
	    System.out.println("Doorlogno: " + doorlogno);
	    System.out.println("Get Month: " + month);
	    
	    double retotalPrice = registeredeatRepository.registeredeatpricefor(doorlogno, month);
	    double rnetotalPrice = registereduneatRepository.registereduneatpricefor(doorlogno, month);
	    double uetotalPrice = unregisteredeatRepository.unregisteredeatpricefor(doorlogno, month);
	    double totalPrice = retotalPrice + rnetotalPrice + uetotalPrice;
	    
	    int countre = registeredeatRepository.countre(doorlogno,month);
	    int countrne = registereduneatRepository.countrne(doorlogno,month);
	    int countue = unregisteredeatRepository.countue(doorlogno,month);
	    
	    MonthData monthData = new MonthData();
	    monthData.setTotalPrice(totalPrice);
	    monthData.setCountre(countre);
	    monthData.setCountrne(countrne);
	    monthData.setCountue(countue);

	    return monthData;
	}


}
