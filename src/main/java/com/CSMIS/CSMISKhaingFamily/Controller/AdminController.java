package com.CSMIS.CSMISKhaingFamily.Controller;

import java.io.IOException;



import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.CSMIS.CSMISKhaingFamily.Entity.AvoidMeat;
import com.CSMIS.CSMISKhaingFamily.Entity.Doorlog;
import com.CSMIS.CSMISKhaingFamily.Entity.MenuImportData;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Entity.Feedback;
import com.CSMIS.CSMISKhaingFamily.Entity.HolidayData;
import com.CSMIS.CSMISKhaingFamily.Entity.Registeredeat;
import com.CSMIS.CSMISKhaingFamily.Entity.Registereduneat;
import com.CSMIS.CSMISKhaingFamily.Entity.RestaurantInfo;
import com.CSMIS.CSMISKhaingFamily.Entity.Unregisteredeat;
import com.CSMIS.CSMISKhaingFamily.DAO.AdminMenuImportService;
import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.DoorlogRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.FeedbackRepo;
import com.CSMIS.CSMISKhaingFamily.DAO.HolidayService;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisteredeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegistereduneatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.Registerfinal1Repository;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.UnregisteredeatRepository;


import java.util.*;
@Controller
public class AdminController {

	@Autowired
	private OperatorRepository operatorRepository;

	@Autowired
	private RegisteredeatRepository registeredeatRepository;

	@Autowired
	private RegistereduneatRepository registereduneatRepository;

	@Autowired
	private UnregisteredeatRepository unregisteredeatRepository;

	@Autowired
	private Registerfinal1Repository registerfinal1Repository;


	@Autowired
	private AvoidMeatRepository avoidmeatRepository;


	@Autowired
	FeedbackRepo fRepo;
	
	@Autowired
	RestaurantInfoRepository rRepo;
	
	@Autowired
	private DoorlogRepository dRepo;
	
	@Autowired
	private HolidayService hService;

	@GetMapping("/admin/list")
	public String list(Model model , HttpSession session) {

		List<Registeredeat> registeredeat = registeredeatRepository.findAll();
		System.out.println(registeredeat);
		List<Registereduneat> registereduneat = registereduneatRepository.findAll();
		System.out.println(registereduneat);
		List<Unregisteredeat> unregisteredeat = unregisteredeatRepository.findAll();

		
		model.addAttribute("registeredeat", registeredeat);
		model.addAttribute("registereduneat", registereduneat);
		model.addAttribute("unregisteredeat", unregisteredeat);
		int i = 0;
	    i++;
	 
	   
		return "listview";
	}
//	private List<Feedback> retrieveFeedbackData() {
//	      List<Feedback> feedbackList = fRepo.findAll();
//	      
//	      for (Feedback feedback : feedbackList) {
//	          Operator operator = feedback.getOperator();
//	          RestaurantInfo restaurant = feedback.getRestaurant();
//	          
//	          System.out.println(operator + "operator from retrieve");
//	          if (operator != null) {
//	              Operator completeOperator = operatorRepository.findById(operator.getId()).orElse(null);
//	              feedback.setOperator(completeOperator);
//
//	              if (restaurant != null) {
//	                  feedback.setRestaurant(restaurant);
//
//	              }
//	          }
//	      }
//	      return feedbackList;
//	  }
	
	@GetMapping("/admin/importlist")
	public String history(Model model) {
		List<Operator> employeelist = operatorRepository.findAllEmployee();
		List<AvoidMeat> avoidmeatlist = avoidmeatRepository.findAll();
		List<Doorlog> doorloglist = dRepo.findAll();
		List<HolidayData> holidaylist = hService.getAllHoliday();
		   model.addAttribute("employeelist", employeelist);
		model.addAttribute("avoidmeatlist", avoidmeatlist);
		model.addAttribute("doorloglist", doorloglist);
		model.addAttribute("holidaylist", holidaylist);
		int i = 0;
	    i++;
	  //update code
	      // Retrieve feedbacks for the current month
	      List<Feedback> feedbackList = retrieveFeedbackDataForCurrentMonth();
	      
	      // Check if it's the last day of the month
	      LocalDate currentDate = LocalDate.now();
	      LocalTime currentTime = LocalTime.now();

	      int lastDayOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
	      boolean isLastDayOfMonth = currentDate.getDayOfMonth() == lastDayOfMonth;
	      boolean isTimeBeforeDeadline = currentTime.getHour() == 23 && currentTime.getMinute() == 59;

	      
	      if (isLastDayOfMonth && isTimeBeforeDeadline) {
	          // Remove the operator's feedbacks from the database
//	          fRepo.deleteAll(feedbackList);
	          feedbackList.clear();
	          
	      } else {
	          // Filter out operator feedbacks created after the current date
	          feedbackList.removeIf(feedback -> feedback.getCreatedDate().isAfter(currentDate));
	      }
	        
	      if (!feedbackList.isEmpty()) {
	          model.addAttribute("feedbackList", feedbackList);
	          model.addAttribute("count", i);
	      }
	      
	 
        model.addAttribute("count"+ i);
		return "importlist";
	}
	private List<Feedback> retrieveFeedbackDataForCurrentMonth() {
	      // Get the current date
	      LocalDate currentDate = LocalDate.now();

	   // Calculate the start and end dates for the current month
	      LocalDate startOfCurrentMonth = currentDate.withDayOfMonth(1);
	      LocalDate endOfCurrentMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());

	      // Retrieve the feedbacks created within the current month
	      List<Feedback> feedbackList = fRepo.findByCreatedDateBetween(startOfCurrentMonth, endOfCurrentMonth);

	      // Fetch the complete Operator object for each feedback
	      for (Feedback feedback : feedbackList) {
	          Operator operator = feedback.getOperator();
	          if (operator != null) {
	              Operator completeOperator = operatorRepository.findById(operator.getId()).orElse(null);
	              feedback.setOperator(completeOperator);
	          }
	      }

	      return feedbackList;
	  }
//	update holidaylist
	@PostMapping("/admin/updateHoliday")
	public ResponseEntity<?> updateHoliday(@RequestParam("date") String date, @RequestParam("name") String name){
		
//		LocalDate holidayDate = LocalDate.parse(date);
		
	    hService.updateHoliday(date, name);
	 // Return a success response
	    return ResponseEntity.ok("Holiday updated successfully");
	}

// check avoidmeat name is had or not
	@PostMapping("/admin/checkAvoidMeatExist")
	public ResponseEntity<?> checkAvoidMeat(@RequestParam("name") String name){
		
		String meatTrim = name.trim();
		AvoidMeat avoidMeat = avoidmeatRepository.findByMeatName(meatTrim);
		if(avoidMeat != null) {
			// Avoid meat with the given name already exists
	        return ResponseEntity.ok("EXIST");
		}else {
	        // Avoid meat with the given name doesn't exist
	        return ResponseEntity.ok("NOT_EXIST");
	    }
		
	}
	
//	update avoidlist
	@PostMapping("/admin/updateAvoidMeat")
	public ResponseEntity<?> updateAvoidMeat(@RequestParam("id") Long id, @RequestParam("name") String name) {
		
	    avoidmeatRepository.updateAvoidMeatNameById(id, name);

		// Return a success response
	    return ResponseEntity.ok("Avoid meat updated successfully");
	}
	
//	delete avoidlist
	@PostMapping("/admin/deleteAvoidMeat")
	public ResponseEntity<?> deleteAvoidMeat(@RequestParam("id") Long id) {
		
		if (!avoidmeatRepository.existsById(id)) {
	        return ResponseEntity.badRequest().body("Avoid meat not found");
	    }
		
	    avoidmeatRepository.deleteById(id);
	    
	    return ResponseEntity.ok("Avoid meat deleted successfully");

	}
	
	@GetMapping("/admin/deleteFeedback")
	public RedirectView deleteOne(@RequestParam("rowId") Long rowId, 
			@RequestParam("activeTab") String activeTab, 
			 @RequestParam("returnUrl") String returnUrl) {
		Optional<Feedback> feedbackOptional = fRepo.findById(rowId);
				
		if (feedbackOptional.isPresent()) {
			 Feedback feedback = feedbackOptional.get();
		     fRepo.delete(feedback);
		}
	    return new RedirectView(returnUrl + "#"+ activeTab + "-tab", true);
	}

//	@GetMapping("/admin/deleteAllFeedback")
//	public String deleteAllFeedback(Model model) {
//	    fRepo.deleteAll(); // Delete all feedback entries
//	    List<Feedback> feedbackList = new ArrayList<>(); // Empty list for feedback
//	    model.addAttribute("feedbackList", feedbackList);
//
//	    // Retrieve other required data using the common method
//	    List<Operator> employeelist = operatorRepository.findAllEmployee();
//	    List<Registeredeat> registeredeat = registeredeatRepository.findAll();
//	    List<Registereduneat> registereduneat = registereduneatRepository.findAll();
//	    List<Unregisteredeat> unregisteredeat = unregisteredeatRepository.findAll();
//
//	    model.addAttribute("employeelist", employeelist);
//	    model.addAttribute("registeredeat", registeredeat);
//	    model.addAttribute("registereduneat", registereduneat);
//	    model.addAttribute("unregisteredeat", unregisteredeat);
//	    
//	    return "importlistview";
//	}
	@GetMapping("/admin/list/{date}")
	public String list(Model model,@PathVariable String date) {
		List<Registeredeat> registeredeat = registeredeatRepository.getReByDate(date);
		System.out.println(registeredeat);
		List<Registereduneat> registereduneat = registereduneatRepository.getRNeByDate(date);
		System.out.println(registereduneat);
		List<Unregisteredeat> unregisteredeat = unregisteredeatRepository.getUReByDate(date);
		model.addAttribute("registeredeat", registeredeat);
		model.addAttribute("registereduneat", registereduneat);
		model.addAttribute("unregisteredeat", unregisteredeat);
		return "listview";
	}
	

	 

	@GetMapping("/admin/cateringStart")
	public String displayshowForm(Principal prin) {
		Operator operator = operatorRepository.findByemployeeEmail(prin.getName());
		List<String> datelist = registerfinal1Repository.getRDate(operator.getDoorLogNo());
		System.out.println("Date " + datelist);
		LocalDate currentDate = LocalDate.now();
		LocalDate endOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
		LocalDate lastWeekStart = endOfMonth.minusDays(6);
		LocalDate lastWeekEnd = endOfMonth;
		boolean isLastWeek = currentDate.isAfter(lastWeekStart.minusDays(1))
				&& currentDate.isBefore(lastWeekEnd.plusDays(1));

		System.out.println(isLastWeek);
		System.out.println("currentDate: " + currentDate);
		System.out.println("lastWeekStart: " + lastWeekStart);
		System.out.println("lastWeekEnd: " + lastWeekEnd);

		if (isLastWeek) {
			if (datelist.size() > 0) {
				String lastDate = datelist.get(datelist.size() - 1);
				LocalDate date = LocalDate.parse(lastDate, DateTimeFormatter.ISO_LOCAL_DATE);
				LocalDate currentMonthFirstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
				System.out.println("CurrentMonth " + currentMonthFirstDay);
				System.out.println("Date " + date);
				System.out.println("Date Year " + date.getMonth());
				System.out.println("Current Month " + currentMonthFirstDay.getMonth());
				if (date.getMonth() == currentMonthFirstDay.getMonth()) {
					return "redirect:/admin/cateringRegistration";
				} else {

					return "redirect:/admin/cateringUpdate";
				}
			} else {
				return "redirect:/admin/cateringRegistration";
			}
		} else {
			if (datelist.size() > 0) {
				return "redirect:/admin/cateringUpdate";
			} else {
				return "redirect:/admin/cateringRegistration";
			}
		}
	}

	@GetMapping("/admin/cateringRegistration")
	public String displaycateringForm(Model model, Principal prin) {
		List<AvoidMeat> meat = avoidmeatRepository.findAll();
		Operator operator = operatorRepository.findByemployeeEmail(prin.getName());
		System.out.println(operator.getDoorLogNo());
		model.addAttribute("doorlogno", operator.getDoorLogNo());
		model.addAttribute("dept", operator.getDept());
		model.addAttribute("division", operator.getDivision());
		model.addAttribute("employeeName", operator.getEmployeeName());
		model.addAttribute("staffId", operator.getStaffId());
		model.addAttribute("status", operator.getStatus());
		model.addAttribute("team", operator.getTeam());
		model.addAttribute("role", operator.getRole());
		model.addAttribute("meat", meat);
		return "adminlunchregister";
	}

	@GetMapping("/admin/cateringUpdate")
	public String displayupdatecateringForm(Model model, Principal prin) {
		List<AvoidMeat> meat = avoidmeatRepository.findAll();
		Operator operator = operatorRepository.findByemployeeEmail(prin.getName());
		System.out.println(operator.getDoorLogNo());
		model.addAttribute("doorlogno", operator.getDoorLogNo());
		model.addAttribute("dept", operator.getDept());
		model.addAttribute("division", operator.getDivision());
		model.addAttribute("employeeName", operator.getEmployeeName());
		model.addAttribute("staffId", operator.getStaffId());
		model.addAttribute("status", operator.getStatus());
		model.addAttribute("team", operator.getTeam());
		model.addAttribute("role", operator.getRole());
		model.addAttribute("meat", meat);
		return "adminupdatelunchregister";
	}

	@PostMapping("/admin/updateEmployee")
	@ResponseBody
	public ResponseEntity<?> updateEmployee(@RequestParam String staffid,

			@RequestParam String role) {
		System.out.println(staffid);
		System.out.println("hello it is goest to /admin/updateEmployee mapping");
		Operator operator = operatorRepository.findBystaffId(staffid);
		
		if (operator == null) {
			return ResponseEntity.badRequest().body("Employee not found");
		}

		operator.setRole(role);
		operatorRepository.save(operator);
		return ResponseEntity.ok().body("Employee updated successfully");
	}

	@PostMapping("/admin/deleteEmployee")
	public ResponseEntity<?> deleteEmployee(@RequestParam String staffid) {
		Operator operator = operatorRepository.findBystaffId(staffid);
		if (operator != null) {

			operator.setStatus("INACTIVE");
			operatorRepository.save(operator);
			return ResponseEntity.ok("Employee deleted successfully.");
		} else {
			return ResponseEntity.badRequest().body("Employee not found.");
		}
	}
}
