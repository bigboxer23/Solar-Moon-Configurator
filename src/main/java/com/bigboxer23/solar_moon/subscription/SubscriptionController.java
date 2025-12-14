package com.bigboxer23.solar_moon.subscription;

import com.bigboxer23.solar_moon.customer.ExtendedCustomerComponent;
import com.bigboxer23.solar_moon.data.Customer;
import com.bigboxer23.solar_moon.data.Subscription;
import com.bigboxer23.solar_moon.util.AuthUtil;
import com.bigboxer23.solar_moon.web.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** */
@RestController
public class SubscriptionController {
	private final ExtendedCustomerComponent component;

	private final ExtendedSubscriptionComponent subscriptionComponent;

	public SubscriptionController(
			ExtendedCustomerComponent component, ExtendedSubscriptionComponent subscriptionComponent) {
		this.component = component;
		this.subscriptionComponent = subscriptionComponent;
	}

	@Transaction
	@Operation(summary = "get seat number", description = "returns the number of seats allocated to the customer.")
	@GetMapping("/subscription")
	public ResponseEntity<Integer> getCustomer(HttpServletRequest servletRequest) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return new ResponseEntity<>(
				subscriptionComponent
						.getSubscription(customer.getCustomerId())
						.map(Subscription::getPacks)
						.orElse(0),
				HttpStatus.OK);
	}
}
