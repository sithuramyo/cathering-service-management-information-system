package com.CSMIS.CSMISKhaingFamily.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoService;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantService;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Entity.RestaurantInfo;

@Controller
public class RestaurantController {

	@Autowired
	RestaurantInfoRepository resRepo;
	
	@Autowired
	RestaurantInfoService service;
	
	@Autowired
	RestaurantService rservice;
	
	@PostMapping("/admin/resImport")
	public ResponseEntity<String> handleFormSubmission(@RequestParam String id,
	                                                  @RequestParam String name,
	                                                  @RequestParam String address,
	                                                  @RequestParam String phone,
	                                                  @RequestParam String email,HttpSession session) {
	    // Process the form values and perform necessary operations
	    // Return the appropriate response
		RestaurantInfo ri = new RestaurantInfo();
		ri.setId(id);
		ri.setResaddress(address);
		ri.setResemail(email);
		ri.setResph(phone);
		ri.setResturantname(name);
		ri.setStatus("ACTIVE");
		int i = resRepo.updateStatusToInactive(ri.getId());
		System.out.println("I" + i);
		ri = resRepo.save(ri);
		if(ri != null ) {
			String resId =rservice.generateUserId();
		      session.setAttribute("resId", resId);
	    String successMessage = "Restaurant information saved successfully.";
	    return ResponseEntity.ok(successMessage);
		}else {
	        String errorMessage = "Failed to save.";
	        return ResponseEntity.badRequest().body(errorMessage);
	    }
	}
	@GetMapping("/admin/resView")
	public String unpaidList(HttpSession session) {
		List<RestaurantInfo> list = resRepo.findAll();
		int result = list.size();
		session.setAttribute("result", result);
		session.setAttribute("rlist", list);
		return "restaurantview";
	}
	@PostMapping("/admin/updateRes")
	  @ResponseBody
	  public ResponseEntity<?> updateEmployee(@RequestParam String id,

	      @RequestParam String name,@RequestParam String address,@RequestParam String phone,@RequestParam String email,@RequestParam String status,HttpSession session) {
	    
		int i = service.updateStatus(id, name, address, phone, email, status,id);
		int j = resRepo.updateStatusToInactive(id);
		System.out.println("i" + i);
		System.out.println("j" + j);
	    if (i == 0  && j == 0) {
	    	
	    
	      return ResponseEntity.badRequest().body("Update Failed.");
	    }

	
	    return ResponseEntity.ok().body("Update Successful.");
	  }

	  @PostMapping("/admin/deleteRes")
	  public ResponseEntity<?> deleteEmployee(@RequestParam String id) {
	   int i = service.deleteByResId(id);
	    if (i > 0) {

	      return ResponseEntity.ok("Deleted successful.");
	    } else {
	      return ResponseEntity.badRequest().body("Delete Failed.");
	    }
	  }
}
