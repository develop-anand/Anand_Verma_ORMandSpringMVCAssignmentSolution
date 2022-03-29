package com.greatLearning.customerManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.customerManagement.entity.Customer;
import com.greatLearning.customerManagement.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/")
	public String getIndex() {

		return "index";
	}

	// add mapping for "/list"
	@RequestMapping("/list")
	public String listCustomers(Model theModel) {

		// get Customers from db
		List<Customer> customerList = customerService.findAll();

		// add to the spring model
		theModel.addAttribute("Customers", customerList);

		return "list-customers";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Customer currentCustomer = new Customer();

		theModel.addAttribute("Customer", currentCustomer);

		return "customer-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int theId, Model theModel) {

		// get the Customer from the service
		Customer currentCustomer = customerService.findById(theId);

		// set Customer as a model attribute to pre-populate the form
		theModel.addAttribute("Customer", currentCustomer);

		// send over to our form
		return "customer-form";
	}

	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id,

			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email) {

		System.out.println(id);
		Customer currentCustomer;
		if (id != 0) {
			currentCustomer = customerService.findById(id);
			currentCustomer.setFirstName(firstName);
			currentCustomer.setLastName(lastName);
			currentCustomer.setEmail(email);
		} else
			currentCustomer = new Customer(firstName, lastName, email); // save
		customerService.save(currentCustomer);

		// use a redirect to prevent duplicate submissions
		return "redirect:/customers/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int theId) {

//	  delete the Customer
		customerService.deleteById(theId);

//	  redirect to /Customers/list
		return "redirect:/customers/list";

	}

}
