package com.CSMIS.CSMISKhaingFamily.Controller;

import java.util.HashMap;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CSMIS.CSMISKhaingFamily.DAO.ContactInfoRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.ContactInfo;


@Controller
public class AdminContactController {
	
	@Autowired
    private ContactInfoRepository contactRepo;

	@PostMapping("/admin/contactInfo")
	@ResponseBody
	public ResponseEntity<?> saveContactInfo(@RequestParam("email") String email,
	                                         @RequestParam("phone") String phone,@RequestParam("address") String address) throws MessagingException {
	    if(email == "" || phone == "" || address == "") {
	    	String errorMessage = "Please enter valid contact information.";
	         return ResponseEntity.badRequest().body(errorMessage);
	    }
		ContactInfo contact = new ContactInfo();
	    contact.setEmail(email);
	    contact.setPhone(phone);
	    contact.setAddress(address);
	    contact = contactRepo.save(contact);

	   
	    if(contact != null) {
	    	String successMessage = "Contact information saved successfully.";
	    	return ResponseEntity.ok().body(successMessage);
	    }
	    else {
	    	 String errorMessage = "Failed to save contact information.";
 	         return ResponseEntity.badRequest().body(errorMessage);
	    }
	}

	
	}