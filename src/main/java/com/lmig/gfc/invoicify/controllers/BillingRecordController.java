package com.lmig.gfc.invoicify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@Controller
@RequestMapping("/billing-records")
public class BillingRecordController {
	
	private CompanyRepository companyRepository;
	private BillingRecordRepository billingRecordRepository;
	
	public BillingRecordController(CompanyRepository companyRepository, BillingRecordRepository billingRecordRepository) {
		this.companyRepository = companyRepository;
		this.billingRecordRepository = billingRecordRepository;
	}

	@GetMapping("")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("billing-records/list");
		
		mv.addObject("records", billingRecordRepository.findAll());
		mv.addObject("companies", companyRepository.findAll());
		
		return mv;
	}

}
