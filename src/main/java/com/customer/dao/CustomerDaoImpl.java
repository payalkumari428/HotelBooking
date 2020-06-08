package com.customer.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory factory;

	private Session getSession() {
		Session session = null;
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException ex) {
			session = factory.openSession();
		}
		return session;
		}


	@Override
	public void addCustomer(Customer bean) {
		   Session session = getSession();        
	        addCustomer(session,bean);        
	        
		
	}
	
	
	 private void addCustomer(Session session, Customer bean){
		 Customer customer = new Customer();
		 customer.setFirstName(bean.getFirstName());
		 customer.setLastName(bean.getLastName());
		 customer.setDob(bean.getDob());
		 customer.setEmailId(bean.getEmailId());
		 customer.setPassword(bean.getPassword()); 
	        session.save(customer);
	    }
	 
	 public boolean verifyEmail(String email){
		 Query query = getSession().createQuery("select count(*) from Customer cust where cust.emailId=?");
		 query.setString(0, email);
		 Long count = (Long)query.uniqueResult();
		 if(count>0){
			 return true;
		 }else{
		 return false;
		 }
		 
	 }

	@Override
	public List<Customer> getCustomerDetails(double id) {
		Query query = getSession().createQuery("from  Customer cust where cust.id =?");
		query.setDouble(0, id);
		List<Customer> employees =  query.list();
		return employees;
	}
	    

}
