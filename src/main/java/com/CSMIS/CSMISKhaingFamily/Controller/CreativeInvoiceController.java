package com.CSMIS.CSMISKhaingFamily.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.CSMIS.CSMISKhaingFamily.DAO.AvoidCountRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.AvoidMeatRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceService;
import com.CSMIS.CSMISKhaingFamily.DAO.OperatorRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoRepository;
import com.CSMIS.CSMISKhaingFamily.Entity.Operator;
import com.CSMIS.CSMISKhaingFamily.Entity.RestaurantInfo;
import com.CSMIS.CSMISKhaingFamily.Model.CreativeInvoiceBean;


@Controller
public class CreativeInvoiceController {
	@Autowired
	AvoidMeatRepository avoidRepo;
	
	@Autowired 
	CreativeInvoiceService invoiceService;
	
	@Autowired 
	CreativeInvoiceRepository invoiceRepo;
	
	@Autowired
	OperatorRepository operatorRepository;
	
	@Autowired
	AvoidCountRepository avoidcountRepo;
	
	@Autowired
	RestaurantInfoRepository resRepo;
	
	@RequestMapping(value="/admin/setupInvoice" , method=RequestMethod.GET)
	public ModelAndView studentRegister(ModelMap model,HttpSession session) {
		String invoiceId = invoiceService.generateUserId();
		session.setAttribute("invoiceId",invoiceId);
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
//	    Date date = new Date();
//	    String today = formatter.format(date);
//		//String today = date.toString();
//	    session.setAttribute("date", today);
		List<Operator> op = operatorRepository.searchUser();
		for(int i = 0;i < op.size();i++) {
			System.out.println(op.get(i).getEmployeeName());
		}
		
		session.setAttribute("op", op);
		CreativeInvoiceBean ci = new CreativeInvoiceBean();
		List<RestaurantInfo> r = resRepo.resList();
		System.out.println(r.size());
		if(r.size() != 0) {
			ci.setResaddress(r.get(0).getResaddress());
			ci.setResemail(r.get(0).getResemail());
			ci.setResph(r.get(0).getResph());
			ci.setResturantname(r.get(0).getResturantname());
		}
		
		return new ModelAndView("invoicecreate","ci",ci);
	}
	
}
