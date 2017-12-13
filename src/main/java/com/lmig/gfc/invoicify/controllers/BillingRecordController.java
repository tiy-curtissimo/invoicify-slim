package com.lmig.gfc.invoicify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/billing-records")
public class BillingRecordController {

	@GetMapping("")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("billing-records/list");
		
		// Get all the billing records and add them to the model and view with the key "records"
		// Get all the companies and add them to the model and view with the key "companies"
		
		return mv;
	}

}
