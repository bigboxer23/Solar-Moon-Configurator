package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.data.Customer;
import com.bigboxer23.solar_moon.open_search.OpenSearchComponent;
import com.bigboxer23.solar_moon.open_search.OpenSearchUtils;
import com.bigboxer23.solar_moon.open_search.SearchJSON;
import com.bigboxer23.solar_moon.web.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** */
@Tag(name = "Search Controller", description = "Search for device data")
@RestController
public class OpenSearchController {
	private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

	private final ExtendedDeviceComponent deviceComponent;

	private final ExtendedCustomerComponent component;

	private final OpenSearchComponent OSComponent;

	public OpenSearchController(
			ExtendedDeviceComponent deviceComponent,
			ExtendedCustomerComponent component,
			OpenSearchComponent OSComponent) {
		this.deviceComponent = deviceComponent;
		this.component = component;
		this.OSComponent = OSComponent;
	}

	@Transaction
	@Operation(summary = "update a device", description = "api to update a device by id.")
	@PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> search(HttpServletRequest servletRequest, @RequestBody SearchJSON searchRequest) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		searchRequest.setCustomerId(customer.getCustomerId());
		return new ResponseEntity<>(OpenSearchUtils.queryToJson(OSComponent.search(searchRequest)), HttpStatus.OK);
	}
}
