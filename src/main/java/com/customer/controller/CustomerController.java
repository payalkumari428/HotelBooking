package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customer.dao.CustomerDao;
import com.customer.model.Customer;



@RestController
@RequestMapping("/api")
public class CustomerController {
	
	
	
	@Autowired
	private CustomerDao dao;

	 @RequestMapping(value="/customer",method=RequestMethod.POST)
	    public ResponseEntity<Void> addCustomer(@RequestBody Customer cust){
		 
		    if(dao.verifyEmail(cust.getEmailId()) == true){
		    	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		    }else{
	       
	        cust.setFirstName(cust.getFirstName());
	        cust.setLastName(cust.getLastName());
	        cust.setDob(cust.getDob());
	        cust.setEmailId(cust.getEmailId());
	        cust.setPassword(cust.getPassword());
	        dao.addCustomer(cust);
	        
	        return new ResponseEntity<Void>(HttpStatus.CREATED);
		    }
	    }
	
	
	 @RequestMapping(value="/customer/{id}",method=RequestMethod.GET)
	    public ResponseEntity<List<Customer>> getCustomer(@PathVariable("id") double id) {
	         List<Customer> customer = dao.getCustomerDetails(id);
	       // for(Customer cust : customer)
	        if(customer.isEmpty()){
	            return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);
	        }else{
	        return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
	        }
	    }
	
		
	

}
