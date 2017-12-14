package com.lmig.gfc.invoicify.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.RateBasedBillingRecord;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@Controller
@RequestMapping("/billing-records/rate-baseds")
public class RateBasedBillingRecordController {

	private BillingRecordRepository billingRecordRepository;
	private CompanyRepository companyRepository;

	public RateBasedBillingRecordController(BillingRecordRepository billingRecordRepository, CompanyRepository companyRepository) {
		this.billingRecordRepository = billingRecordRepository;
		this.companyRepository = companyRepository;
	}

	@PostMapping("")
	public ModelAndView create(RateBasedBillingRecord record, long clientId, Authentication auth) {
		User user = (User) auth.getPrincipal();
		Company client = companyRepository.findOne(clientId);
		record.setClient(client);
		record.setCreatedBy(user);
		billingRecordRepository.save(record);
		
		return new ModelAndView("redirect:/billing-records");
	}
	
}
