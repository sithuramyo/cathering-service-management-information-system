package com.CSMIS.CSMISKhaingFamily.Controller;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.*;
import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.Registerfinal1Repository;
import com.CSMIS.CSMISKhaingFamily.Entity.AvoidMeat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
@Controller
public class OperatorController {
	@Autowired
	private AvoidMeatRepository avoidmeatRepository;
	
	@Autowired
	OperatorRepository operatorRepository;
	
	@Autowired
	Registerfinal1Repository registerfinal1Repository;
	
	
	@GetMapping("/operator/cateringStart")
	public String displayshowForm(Principal prin) {
	    Operator operator = operatorRepository.findByemployeeEmail(prin.getName());
	    List<String> datelist = registerfinal1Repository.getRDate(operator.getDoorLogNo());
	    System.out.println("Date " + datelist);
	    LocalDate currentDate = LocalDate.now();
	    LocalDate endOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());
	    LocalDate lastWeekStart = endOfMonth.minusDays(6);
	    LocalDate lastWeekEnd = endOfMonth;
	    boolean isLastWeek = currentDate.isAfter(lastWeekStart.minusDays(1)) && currentDate.isBefore(lastWeekEnd.plusDays(1));

	    if (isLastWeek) {
	    	if (datelist.size() > 0) {
	    	    String lastDate = datelist.get(datelist.size() - 1);
	    	    LocalDate date = LocalDate.parse(lastDate, DateTimeFormatter.ISO_LOCAL_DATE);
	    	    LocalDate currentMonthFirstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
	    	    System.out.println("CurrentMonth " + currentMonthFirstDay);
	    	    System.out.println("Date " + date);
	    	    System.out.println("Date Year " +date.getMonth());
	    	    System.out.println("Current Month " + currentMonthFirstDay.getMonth());
	    	    if (date.getMonth() == currentMonthFirstDay.getMonth()) {
	    	    	 return "redirect:/operator/cateringRegistration";
	    	    } else {
	    	       
	    	        return "redirect:/operator/cateringUpdate";
	    	    }
	    	} else {
	    	    return "redirect:/operator/cateringRegistration";
	    	}
	    }
	    else {
	        if (datelist.size() > 0) {
	            return "redirect:/operator/cateringUpdate";
	        } else {
	            return "redirect:/operator/cateringRegistration";
	        }
	    }
	}

	@GetMapping("/operator/cateringRegistration")
    public String displaycateringForm(Model model , Principal prin) {
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
        return "lunchregister";
	}
	@GetMapping("/operator/cateringUpdate")
    public String displayupdatecateringForm(Model model , Principal prin) {
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
        return "updatelunchregister";
	}
}
