package com.CSMIS.CSMISKhaingFamily.Controller;

import java.security.Principal;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;


@Controller
public class OperatorAccController {
    
  @Autowired
    private OperatorRepository operatorRepository;
  
    @PostMapping("/operator/account")
	@ResponseBody
	public ResponseEntity<?> sendEmail(@RequestParam("name") String name, 
	                        
	                        HttpSession session,ModelMap model) throws MessagingException{
    String email = (String) session.getAttribute("email");
    if(name == "") {
    	 String errorMessage = "Please enter valid name and email.";
         return ResponseEntity.badRequest().body(errorMessage);
    }

	   int i = operatorRepository.updateWithEmail1(name, email);
       if(i > 0) {
    	   String successMessage = "Account details saved successfully.";
    	    session.setAttribute("operatorname",name);
	        return ResponseEntity.ok().body(successMessage);
       }
       
       else {
    	    
 	        String errorMessage = "Failed to save account details.";
 	        return ResponseEntity.badRequest().body(errorMessage);
    }
  
    }
  
}