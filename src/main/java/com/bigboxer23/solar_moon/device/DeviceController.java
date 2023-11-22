package com.bigboxer23.solar_moon.device;

import com.bigboxer23.solar_moon.customer.ExtendedCustomerComponent;
import com.bigboxer23.solar_moon.data.Customer;
import com.bigboxer23.solar_moon.data.Device;
import com.bigboxer23.solar_moon.util.AuthUtil;
import com.bigboxer23.solar_moon.util.TokenGenerator;
import com.bigboxer23.solar_moon.web.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** */
@Tag(name = "Device Controller", description = "Create/Read/Update/Delete devices api")
@RestController
public class DeviceController {
	private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

	private final ExtendedDeviceComponent deviceComponent;

	private ExtendedCustomerComponent component;

	public DeviceController(ExtendedDeviceComponent deviceComponent, ExtendedCustomerComponent component) {
		this.deviceComponent = deviceComponent;
		this.component = component;
	}

	// @PreAuthorize("")
	@Transaction
	@Operation(summary = "add a device", description = "api to add a device")
	@PutMapping("/device")
	public ResponseEntity<Void> addDevice(HttpServletRequest servletRequest, @RequestBody Device device) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		try {
			device.setClientId(customer.getCustomerId());
			device.setId(TokenGenerator.generateNewToken());
			if (!deviceComponent.addDevice(device)) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.warn("addDevice error: " + device, e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Transaction
	@Operation(summary = "update a device", description = "api to update a device by id.")
	@PostMapping("/device")
	public ResponseEntity<Void> updateDevice(HttpServletRequest servletRequest, @RequestBody Device device) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		device.setClientId(customer.getCustomerId());
		deviceComponent.updateDevice(device);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Transaction
	@Operation(summary = "delete a device", description = "api to delete a device by id")
	@DeleteMapping("/device/{id}")
	public ResponseEntity<Void> deleteDevice(
			HttpServletRequest servletRequest,
			@Parameter(description = "id of the device to delete") @PathVariable("id") String id) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		deviceComponent.deleteDevice(id, customer.getCustomerId());
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Transaction
	@Operation(summary = "get a device", description = "api to get a device's information by id")
	@GetMapping("/device/{id}")
	public ResponseEntity<Device> getDevice(
			HttpServletRequest servletRequest,
			@Parameter(description = "id of the device to find") @PathVariable("id") String id) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(deviceComponent.getDevice(id, customer.getCustomerId()), HttpStatus.OK);
	}

	@Transaction
	@Operation(
			summary = "API to create the dynamodb table if doesn't exist",
			description = "create the dynamodb table if doesn't exist")
	@PostMapping("/createDeviceTable")
	public ResponseEntity<Void> createDeviceTable(HttpServletRequest servletRequest) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null || !customer.isAdmin()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		deviceComponent.createDeviceTable();
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Transaction
	@Operation(
			summary = "get all devices for a specific client",
			description = "Return all devices associated with a client")
	@GetMapping("/devices")
	public ResponseEntity<List<Device>> getDevices(HttpServletRequest servletRequest) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(deviceComponent.getDevicesForCustomerId(customer.getCustomerId()), HttpStatus.OK);
	}
}
