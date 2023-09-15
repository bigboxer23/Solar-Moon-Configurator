package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.data.Customer;
import com.bigboxer23.solar_moon.web.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** */
@Tag(name = "Customer Controller", description = "APIs for dealing with customer data")
@RestController
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	private ExtendedCustomerComponent component;

	public CustomerController(ExtendedCustomerComponent component) {
		this.component = component;
	}

	@Transaction
	@Operation(
			summary = "API to create the dynamodb table if doesn't exist",
			description = "create the dynamodb table if doesn't exist")
	@PostMapping("/createCustomerTable")
	public ResponseEntity<Void> createTable() {
		component.createCustomerTable();
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	// TODO:this is temp admin function, should be generating customers from cognito data
	@Transaction
	@Operation(summary = "add a customer", description = "api to add a customer")
	@PutMapping("/customer")
	public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) {
		try {
			customer.setCustomerId(DeviceController.getClientId());
			component.addCustomer(customer.getEmail(), customer.getCustomerId());
		} catch (Exception e) {
			logger.warn("addCustomer: " + customer, e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Transaction
	@Operation(summary = "update a customer", description = "api to update a customer by customer id.")
	@PostMapping("/customer")
	public ResponseEntity<Void> updateCustomer(@RequestBody Customer customer) {
		customer.setCustomerId(DeviceController.getClientId());
		component.updateCustomer(customer);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Transaction
	@Operation(summary = "delete a customer", description = "api to delete a customer by customer id")
	@DeleteMapping("/customer")
	public ResponseEntity<Void> deleteCustomer() {
		component.deleteCustomerByCustomerId(DeviceController.getClientId());
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Transaction
	@Operation(summary = "get a customer", description = "api to get a customer's information by customer id")
	@GetMapping("/customer")
	public ResponseEntity<Customer> getCustomer() {
		return new ResponseEntity<>(component.findCustomerByCustomerId(DeviceController.getClientId()), HttpStatus.OK);
	}
}
