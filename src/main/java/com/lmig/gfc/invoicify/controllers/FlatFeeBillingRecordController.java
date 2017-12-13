package com.lmig.gfc.invoicify.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.FlatFeeBillingRecord;

@Controller
@RequestMapping("/billing-records/flat-fees")
public class FlatFeeBillingRecordController {

	@PostMapping("")
	public ModelAndView create(FlatFeeBillingRecord record, long clientId, Authentication auth) {
		// Get the user from the auth.getPrincipal() method
		// Find the client using the client id
		// Set the client on the record
		// Set the user on the record for the created by property
		// Save the record

		return new ModelAndView("redirect:/billing-records");
	}
	
}
