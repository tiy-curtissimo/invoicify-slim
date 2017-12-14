package com.lmig.gfc.invoicify.controllers;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.invoicify.models.BillingRecord;
import com.lmig.gfc.invoicify.models.Company;
import com.lmig.gfc.invoicify.models.Invoice;
import com.lmig.gfc.invoicify.models.InvoiceLineItem;
import com.lmig.gfc.invoicify.models.User;
import com.lmig.gfc.invoicify.services.BillingRecordRepository;
import com.lmig.gfc.invoicify.services.CompanyRepository;
import com.lmig.gfc.invoicify.services.InvoiceRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/invoices")
public class InvoicesController {

	private InvoiceRepository invoiceRepository;
	private BillingRecordRepository billingRecordRepository;
	private CompanyRepository companyRepository;

	public InvoicesController(InvoiceRepository invoiceRepository, BillingRecordRepository billingRecordRepository, CompanyRepository companyRepository) {
		this.invoiceRepository = invoiceRepository;
		this.billingRecordRepository = billingRecordRepository;
		this.companyRepository = companyRepository;
	}

	@GetMapping("")
	public ModelAndView showInvoices() {
		ModelAndView mv = new ModelAndView("invoices/list");

		List<Invoice> invoices = invoiceRepository.findAll();
		mv.addObject("invoices", invoices);
		mv.addObject("showTable", invoices.size() > 0);

		return mv;
	}

	@GetMapping("/clients")
	public ModelAndView chooseClient() {
		ModelAndView mv = new ModelAndView("invoices/clients");

		List<Company> clients = companyRepository.findAll();
		mv.addObject("clients", clients);

		return mv;
	}

	@GetMapping("/clients/{clientId}")
	public ModelAndView createInvoice(@PathVariable Long clientId) {
		ModelAndView mv = new ModelAndView("invoices/billing-records-list");

		List<BillingRecord> records = billingRecordRepository.findByClientIdAndLineItemIsNull(clientId);
		mv.addObject("records", records);
		mv.addObject("clientId", clientId);

		return mv;
	}

	@PostMapping("/clients/{clientId}")
	public String createInvoice(Invoice invoice, @PathVariable Long clientId, long[] recordIds, Authentication auth) {
		User user = (User) auth.getPrincipal();
		Company client = companyRepository.findOne(clientId);
		List<BillingRecord> records = billingRecordRepository.findByIdIn(recordIds);
		
		ArrayList<InvoiceLineItem> lineItems = new ArrayList<InvoiceLineItem>();
		for (BillingRecord record : records) {
			InvoiceLineItem lineItem = new InvoiceLineItem();
			lineItem.setBillingRecord(record);
			lineItem.setCreatedBy(user);
			lineItem.setInvoice(invoice);
			lineItems.add(lineItem);
		}
		
		invoice.setLineItems(lineItems);
		invoice.setCreatedBy(user);
		invoice.setCompany(client);
		invoiceRepository.save(invoice);

		return "redirect:/invoices";
	}

}
