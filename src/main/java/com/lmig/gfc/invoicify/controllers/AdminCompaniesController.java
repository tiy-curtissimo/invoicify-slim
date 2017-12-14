package com.lmig.gfc.invoicify.controllers;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.services.CompanyRepository;

@Controller
@RequestMapping("/admin/companies")
public class AdminCompaniesController {
	
	private CompanyRepository companyRepository;
	
	public AdminCompaniesController(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@GetMapping("")
	public ModelAndView showDefault() {
		ModelAndView mv = new ModelAndView("admin/companies/default");
		
		mv.addObject("companies", companyRepository.findAll(new Sort("name")));
		
		return mv;
	}
	
	@PostMapping("")
	public ModelAndView createCompany(Company company) {
		ModelAndView mv = new ModelAndView("redirect:/admin/companies");
		
		companyRepository.save(company);
		
		return mv;
	}
	
}
