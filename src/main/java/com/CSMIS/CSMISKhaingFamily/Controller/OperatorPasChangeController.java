package com.CSMIS.CSMISKhaingFamily.Controller;

import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Arrays;
import java.util.Base64;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Model.Password;

@Controller
public class OperatorPasChangeController {

  
  @Autowired
    private OperatorRepository operatorRepository;

 
  	@PostMapping("/operator/pasChange")
	@ResponseBody
	public ResponseEntity<?> changePassword(@RequestParam("cpas") String currentPassword,
	                                        @RequestParam("vpas") String verifyPassword,
	                                        @RequestParam("npas") String newPassword,Principal principal,ExtendedModelMap model) {
      Operator op = operatorRepository.findByemployeeEmail(principal.getName());
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      
      if(currentPassword == "" || verifyPassword == "" || newPassword == "") {
     	 String warningMessage = "Please enter valid passwords.";
          
	          return ResponseEntity.badRequest().body(warningMessage);
     }
     	
      else if (!encoder.matches(currentPassword, op.getEmployeePassword())) {
          //model.addAttribute("errorMessage", "Old password is incorrect !!");
          String warningMessage = "Old password is incorrect !!";
         
	          return ResponseEntity.badRequest().body(warningMessage);
      }
     
      
      else if (!newPassword.equals(verifyPassword)) {
         // model.addAttribute("errorMessage3", "Password Doesn't Match !!");
          String warningMessage = "Password Doesn't Match !!";
          
	          return ResponseEntity.badRequest().body(warningMessage);
      }
        op.setEmployeePassword(encoder.encode(verifyPassword));
        op = operatorRepository.save(op);
        if(op != null) {
      	  String successMessage = "Password changed successfully.";
      	   return ResponseEntity.ok().body(successMessage);
        }
        else {
      	  String errorMessage = "Failed to save new password.";
	          return ResponseEntity.badRequest().body("{\"message\": \"" + errorMessage + "\"}");
        }
    
      

		
		
		
		
		
//		 String encodedString = op.getEmployeePassword(); // an example base64 encoded string
//
//	        // decode the string
//	        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//	        String decodedString = new String(decodedBytes);
//
//	        // print the decoded string
//	        System.out.println(decodedString); // output: "Hello World!"

		
		    
		
	    
		    
		    
				  
//		String encodedString = op.getEmployeePassword();
//      
//      byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//      String decodedString = new String(decodedBytes);
//      
//      System.out.println(decodedString);
		
		
		
		
		//		operatorRepository.updateWithEmail2(npassword, email1, email1);
		
		
		
		
		
//		 PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		 String encodedPassword = passwordEncoder.encode("12345");
//		 System.out.println(encodedPassword);
//		    
		
		
//		return "redirect:/admin/adminSetting";
	}
}