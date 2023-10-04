package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.data.Customer;
import com.bigboxer23.solar_moon.web.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** */
@Tag(name = "Customer Controller", description = "APIs for dealing with customer data")
@RestController
public class CustomerController {
	private ExtendedCustomerComponent component;

	public CustomerController(ExtendedCustomerComponent component) {
		this.component = component;
	}

	@Transaction
	@Operation(
			summary = "API to create the dynamodb table if doesn't exist",
			description = "create the dynamodb table if doesn't exist")
	@PostMapping("/createCustomerTable")
	public ResponseEntity<Void> createTable(HttpServletRequest servletRequest) {
		Customer jwtCustomer = AuthUtil.authorize(servletRequest, component);
		if (jwtCustomer == null || !jwtCustomer.isAdmin()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		component.createCustomerTable();
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Transaction
	@Operation(summary = "update a customer", description = "api to update a customer by customer id.")
	@PostMapping("/customer")
	public ResponseEntity<Void> updateCustomer(HttpServletRequest servletRequest, @RequestBody Customer customer) {
		Customer jwtCustomer = AuthUtil.authorize(servletRequest, component);
		if (jwtCustomer == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		customer.setCustomerId(jwtCustomer.getCustomerId());
		component.updateCustomer(customer);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Transaction
	@Operation(summary = "delete a customer", description = "api to delete a customer by customer id")
	@DeleteMapping("/customer")
	public ResponseEntity<Void> deleteCustomer(HttpServletRequest servletRequest) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		component.deleteCustomerByCustomerId(customer.getCustomerId());
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Transaction
	@Operation(summary = "get a customer", description = "api to get a customer's information by customer id")
	@GetMapping("/customer")
	public ResponseEntity<Customer> getCustomer(HttpServletRequest servletRequest) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
}
