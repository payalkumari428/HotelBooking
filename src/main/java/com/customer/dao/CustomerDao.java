package com.customer.dao;

import java.util.List;

import com.customer.model.Customer;



public interface CustomerDao {
	
	public List<Customer> getCustomerDetails(double id);
	
	
	public void addCustomer(Customer bean);
	
	public boolean verifyEmail(String email);
}
