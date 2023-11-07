package com.bigboxer23.solar_moon.alarm;

import com.bigboxer23.solar_moon.customer.ExtendedCustomerComponent;
import com.bigboxer23.solar_moon.data.Customer;
import com.bigboxer23.solar_moon.util.AuthUtil;
import com.bigboxer23.solar_moon.web.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/** */
@RestController
public class AlarmController {
	private ExtendedAlarmComponent alarmComponent;

	private ExtendedCustomerComponent customerComponent;

	public AlarmController(ExtendedAlarmComponent alarmComponent, ExtendedCustomerComponent customerComponent) {
		this.alarmComponent = alarmComponent;
		this.customerComponent = customerComponent;
	}

	@Transaction
	@Operation(
			summary = "API to create the dynamodb table if doesn't exist",
			description = "create the dynamodb table if doesn't exist")
	@PostMapping("/alarm/createTable")
	public ResponseEntity<Void> createDeviceTable(HttpServletRequest servletRequest) {
		Customer customer = AuthUtil.authorize(servletRequest, customerComponent);
		if (customer == null || !customer.isAdmin()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		alarmComponent.createAlarmTable();
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
