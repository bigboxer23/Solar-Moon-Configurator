package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.data.Customer;
import com.bigboxer23.solar_moon.web.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class SubscriptionController
{
	private ExtendedCustomerComponent component;

	private ExtendedSubscriptionComponent subscriptionComponent;

	public SubscriptionController(ExtendedCustomerComponent component, ExtendedSubscriptionComponent subscriptionComponent) {
		this.component = component;
		this.subscriptionComponent = subscriptionComponent;
	}

	@Transaction
	@Operation(summary = "get seat number", description = "returns the number of seats allocated to the customer.")
	@GetMapping("/subscription")
	public ResponseEntity<Integer> getCustomer(HttpServletRequest servletRequest) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(subscriptionComponent.getSubscriptionPacks(customer.getCustomerId()), HttpStatus.OK);
	}
}
