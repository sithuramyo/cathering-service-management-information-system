package com.CSMIS.CSMISKhaingFamily.Controller;

import java.security.Principal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatListRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisterCateringRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisterDateRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegisteredeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RegistereduneatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.Registerfinal1Repository;
import com.CSMIS.CSMISKhaingFamily.DAO.UnregisteredeatRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Entity.RegisterCatering;
import com.CSMIS.CSMISKhaingFamily.Entity.Registeredeat;
import com.CSMIS.CSMISKhaingFamily.Function.MonthData;

import org.springframework.transaction.annotation.Transactional;
@RestController
@RequestMapping("/api")
public class ApiOperatorController {
	@Autowired
	RegisterCateringRepository registerCateringRepository;
	
	@Autowired
	RegisterDateRepository registerDateRepository;
	
	@Autowired
	AvoidMeatListRepository avoidMeatListRepository;
	
	@Autowired
	OperatorRepository operatorRepository;
	
	@Autowired
	RegisteredeatRepository registeredeatRepository;
	
	@Autowired
	RegistereduneatRepository registereduneatRepository;
	
	@Autowired
	UnregisteredeatRepository unregisteredeatRepository;
	
	@Autowired
	Registerfinal1Repository registerfianlRepository;
	
	@PostMapping("/operator/dateregister")
	 @Transactional
	 public void cateringRegister(@RequestBody RegisterCatering selectedDates) {
	  registerCateringRepository.save(selectedDates);
	  avoidMeatListRepository.deleteAvoidMeatsWithNoRegisterDate();
	  registerCateringRepository.deleteRegisterCateringWithNoRegisterDate();
	 }

	
	@GetMapping("/operator/registereddate")
	public List<String> getRegisteredDate(Principal prin){
		Operator register = operatorRepository.findByemployeeEmail(prin.getName());
		List<String> getRegistered = registerCateringRepository.getDate(register.getDoorLogNo());
		System.out.println("Registered Date" +getRegistered);
		return getRegistered;
	}
	@GetMapping("/operator/avoidmeatlist")
	public List<String> getAvoidmeatlist(Principal prin){
		Operator register = operatorRepository.findByemployeeEmail(prin.getName());
		List<String> getAvoid = registerCateringRepository.getMeat(register.getDoorLogNo());
		System.out.println("Avoid Meat" +getAvoid);
		return getAvoid;
	}
	
	@GetMapping("/operator/registeredeat")
	public List<String> getRegisteredEat(Principal prin){
		Operator regsiter = operatorRepository.findByemployeeEmail(prin.getName());
		List<String> getregisteredEat = registeredeatRepository.getRDateForEat(regsiter.getDoorLogNo());
		return getregisteredEat;
	}
	
	@GetMapping("/operator/registerednoeat")
	public List<String> getRegisteredNoEat(Principal prin){
		Operator regsiter = operatorRepository.findByemployeeEmail(prin.getName());
		List<String> getregisteredNoEat = registereduneatRepository.getRDateForNoEat(regsiter.getDoorLogNo());
		return getregisteredNoEat;
	}
	
	@GetMapping("/operator/unregisteredeat")
	public List<String> getUnRegisteredEat(Principal prin){
		Operator regsiter = operatorRepository.findByemployeeEmail(prin.getName());
		List<String> getunregisteredEat = unregisteredeatRepository.getURDateForEat(regsiter.getDoorLogNo());
		return getunregisteredEat;
	}
	@PostMapping("/operator/getmonth")
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
