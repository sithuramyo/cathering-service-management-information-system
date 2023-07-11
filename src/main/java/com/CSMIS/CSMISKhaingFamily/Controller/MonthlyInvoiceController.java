package com.CSMIS.CSMISKhaingFamily.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.CSMIS.CSMISKhaingFamily.DAO.CreativeInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.RestaurantInfoRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyInvoiceRepository;
import com.CSMIS.CSMISKhaingFamily.DAO.WeeklyInvoiceService;
import com.CSMIS.CSMISKhaingFamily.Entity.CreativeInvoice;
import com.CSMIS.CSMISKhaingFamily.Entity.RestaurantInfo;
import com.CSMIS.CSMISKhaingFamily.Entity.WeeklyInvoice;
import com.CSMIS.CSMISKhaingFamily.Model.CreativeInvoiceBean;
import com.CSMIS.CSMISKhaingFamily.Model.MonthlyInvoiceBean;

@Controller
public class MonthlyInvoiceController {

	@Autowired
	WeeklyInvoiceRepository weeklyInvoiceRepository;
	
	@Autowired
	WeeklyInvoiceService wiService;
	
	@Autowired
	CreativeInvoiceRepository ciRepository;
    
	@Autowired
	RestaurantInfoRepository resRepo;
	
	@GetMapping("/admin/monthlyInvoiceList")
	public String paidList(HttpSession session) {
		//List<WeeklyInvoice> wi = wiService.getMonthlyInvoiceList();
		//wi.size();
		
		List<MonthlyInvoiceBean> list = new ArrayList();
		List<WeeklyInvoice> wi = weeklyInvoiceRepository.dateList();
		List<CreativeInvoice> ciList = new ArrayList();
		List<RestaurantInfo> riList = new ArrayList();
		
		double totalAmount = 0.0;
		
		for(int i = 0;i < wi.size();i++) {
			MonthlyInvoiceBean bean = new MonthlyInvoiceBean();
			CreativeInvoice ci = ciRepository.getInvoiceInfo(wi.get(i).getCreateinvoiceid());
			
			bean.setApprovedby(ci.getApprovedby());
			bean.setCashier(ci.getCashier());
			bean.setDescription(wi.get(i).getDescription());
			bean.setPaymentdate(wi.get(i).getPaymentdate());
			bean.setPaymentmethod(ci.getPaymethod());
			bean.setPrice(wi.get(i).getPrice());
			bean.setReceivedby(ci.getReceivedby());
			bean.setStatus(wi.get(i).getStatus());
			bean.setTotalamount(wi.get(i).getTotalamount());
			bean.setTotalpax(wi.get(i).getTotalpax());
			
			totalAmount += bean.getTotalamount();
			list.add(bean);
		}
		RestaurantInfo ri = resRepo.findLastRow();
		
		session.setAttribute("mlist", list);
		session.setAttribute("totalAmount", totalAmount);
		if(ri != null) {
		      session.setAttribute("resName", ri.getResturantname());
		      session.setAttribute("resAdd", ri.getResaddress());
		      session.setAttribute("resPhone", ri.getResph());
		      session.setAttribute("resEmail", ri.getResemail());
		      if(ri.getResemail() == null || ri.getResemail() == "" || ri.getResemail().isEmpty() || ri.getResemail().isEmpty()) {
		        
		        session.setAttribute("result", 0);
		      }
		      
		    }
	
		return "monthlyinvoiceview";
	}
	@GetMapping("/admin/monthyInvoiceSearchError")
	public String monthlySearchError(HttpSession session) {
		
		return "monthlyinvoiceview";
	}
	
	@GetMapping("/admin/monthyInvoiceSearchSuccess")
	public String monthlySearchSuccess(HttpSession session) {
		
		return "monthlyinvoiceview";
	}
	@GetMapping("/admin/monthyInvoiceResetSuccess")
	public String monthlyResetSuccess(HttpSession session) {
		
		return "monthlyinvoiceview";
	}
	@GetMapping("/admin/monthyInvoiceSearchNullError")
	public String monthlySearchNullError(HttpSession session) {
		List<MonthlyInvoiceBean> list = new ArrayList();
		List<WeeklyInvoice> wi = weeklyInvoiceRepository.dateList();
		List<CreativeInvoice> ciList = new ArrayList();
		List<RestaurantInfo> riList = new ArrayList();
		
		double totalAmount = 0.0;
		
		for(int i = 0;i < wi.size();i++) {
			MonthlyInvoiceBean bean = new MonthlyInvoiceBean();
			CreativeInvoice ci = ciRepository.getInvoiceInfo(wi.get(i).getCreateinvoiceid());
			RestaurantInfo ri = resRepo.getResInfo(wi.get(i).getResid());
			bean.setApprovedby(ci.getApprovedby());
			bean.setCashier(ci.getCashier());
			bean.setDescription(wi.get(i).getDescription());
			bean.setPaymentdate(wi.get(i).getPaymentdate());
			bean.setPaymentmethod(ci.getPaymethod());
			bean.setPrice(wi.get(i).getPrice());
			bean.setReceivedby(ci.getReceivedby());
			bean.setStatus(wi.get(i).getStatus());
			bean.setTotalamount(wi.get(i).getTotalamount());
			bean.setTotalpax(wi.get(i).getTotalpax());
			
			totalAmount += bean.getTotalamount();
			list.add(bean);
		}
		
		
		session.setAttribute("mlist", list);
		session.setAttribute("totalAmount", totalAmount);
		return "monthlyinvoiceview";
	}
	
	@PostMapping("/admin/monthlyInvoice")
	public String searchMonthlyInvoice(HttpServletRequest request,HttpSession session,RedirectAttributes redirectAttributes) {
		String startDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		List<MonthlyInvoiceBean> list = new ArrayList();
		
		if(startDate == "" || endDate == "" ) {
				
			list = (List<MonthlyInvoiceBean>) session.getAttribute("mlist");
			session.setAttribute("mlist", list);
			redirectAttributes.addFlashAttribute("nullErrorMessage", "Please enter valid start date and end date.");
            return "redirect:/admin/monthyInvoiceSearchNullError";
		}
		else {
			List<WeeklyInvoice> wi = weeklyInvoiceRepository.findAllByInvoicedateBetween(startDate,endDate);
			System.out.println("Wi list:" + wi.size());
			if(wi.size() == 0) {
				session.setAttribute("mlist", wi);
				session.setAttribute("totalAmount", 0.0);
				redirectAttributes.addFlashAttribute("errorMessage", "Searching failed.");
	            return "redirect:/admin/monthyInvoiceSearchError";
				
			}
			List<CreativeInvoice> ciList = new ArrayList();
			List<RestaurantInfo> riList = new ArrayList();
			
			double totalAmount = 0.0;
			
			for(int i = 0;i < wi.size();i++) {
				MonthlyInvoiceBean bean = new MonthlyInvoiceBean();
				CreativeInvoice ci = ciRepository.getInvoiceInfo(wi.get(i).getCreateinvoiceid());
				RestaurantInfo ri = resRepo.getResInfo(wi.get(i).getResid());
				bean.setApprovedby(ci.getApprovedby());
				bean.setCashier(ci.getCashier());
				bean.setDescription(wi.get(i).getDescription());
				bean.setPaymentdate(wi.get(i).getPaymentdate());
				bean.setPaymentmethod(ci.getPaymethod());
				bean.setPrice(wi.get(i).getPrice());
				bean.setReceivedby(ci.getReceivedby());
				bean.setStatus(wi.get(i).getStatus());
				bean.setTotalamount(wi.get(i).getTotalamount());
				bean.setTotalpax(wi.get(i).getTotalpax());
				
				totalAmount += bean.getTotalamount();
				list.add(bean);
			}
			if(list.size() != 0) {
				session.setAttribute("mlist", list);
				session.setAttribute("totalAmount", totalAmount);
				redirectAttributes.addFlashAttribute("successMessage", "Searching successful.");
	            return "redirect:/admin/monthyInvoiceSearchSuccess";
			}
			
		}
		
		
		return "monthlyinvoiceview";
	}
	@GetMapping("/admin/monthlyInvoiceReset")
	public String resetInvoiceMonthly(RedirectAttributes redirectAttributes,HttpSession session) {
		redirectAttributes.addFlashAttribute("resetSuccessMessage", "Reset successful.");
		List<MonthlyInvoiceBean> list = new ArrayList();
		List<WeeklyInvoice> wi = weeklyInvoiceRepository.dateList();
		List<CreativeInvoice> ciList = new ArrayList();
		List<RestaurantInfo> riList = new ArrayList();
		
		double totalAmount = 0.0;
		
		for(int i = 0;i < wi.size();i++) {
			MonthlyInvoiceBean bean = new MonthlyInvoiceBean();
			CreativeInvoice ci = ciRepository.getInvoiceInfo(wi.get(i).getCreateinvoiceid());
			RestaurantInfo ri = resRepo.getResInfo(wi.get(i).getResid());
			bean.setApprovedby(ci.getApprovedby());
			bean.setCashier(ci.getCashier());
			bean.setDescription(wi.get(i).getDescription());
			bean.setPaymentdate(wi.get(i).getPaymentdate());
			bean.setPaymentmethod(ci.getPaymethod());
			bean.setPrice(wi.get(i).getPrice());
			bean.setReceivedby(ci.getReceivedby());
			bean.setStatus(wi.get(i).getStatus());
			bean.setTotalamount(wi.get(i).getTotalamount());
			bean.setTotalpax(wi.get(i).getTotalpax());
			
			totalAmount += bean.getTotalamount();
			list.add(bean);
		}
		
		
		session.setAttribute("mlist", list);
		session.setAttribute("totalAmount", totalAmount);
		return "redirect:/admin/monthyInvoiceSearchSuccess";
	}
}