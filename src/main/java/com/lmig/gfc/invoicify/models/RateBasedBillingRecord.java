package com.lmig.gfc.invoicify.models;

//This needs to be an entity
public class RateBasedBillingRecord extends BillingRecord {

	// This does NOT need an id because it inherits it from the BillingRecord class
	// This needs a double field named rate
	// This needs a double field named quantity
	
	// This needs to override the getTotal() method and return the product of the rate and quantity
	// This needs getters and setters
	
}
