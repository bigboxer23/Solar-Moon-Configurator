package com.bigboxer23.solar_moon;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/** */
@Tag(name = "Customer Controller", description = "APIs for dealing with customer data")
@RestController
public class CustomerController {
	private ExtendedCustomerComponent component;

	public CustomerController(ExtendedCustomerComponent component) {
		this.component = component;
	}

	@Operation(
			summary = "API to create the dynamodb table if doesn't exist",
			description = "create the dynamodb table if doesn't exist")
	@PostMapping("/createCustomerTable")
	public ResponseEntity<Void> createTable() {
		component.createCustomerTable();
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
