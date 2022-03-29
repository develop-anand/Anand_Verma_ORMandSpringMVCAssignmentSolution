package com.greatLearning.customerManagement.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.greatLearning.customerManagement.entity.Customer;

@Service
public interface CustomerService {

	List<Customer> findAll();

	Customer findById(int id);

	void save(Customer theCustomer);

	void deleteById(int id);

	// print the loop
	void print(List<Customer> Customer);

}