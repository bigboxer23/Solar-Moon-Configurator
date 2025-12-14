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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** */
@Slf4j
@Tag(name = "Device Controller", description = "Create/Read/Update/Delete devices api")
@RestController
public class DeviceController {

	private final ExtendedDeviceComponent deviceComponent;

	private final ExtendedCustomerComponent component;

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
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		try {
			device.setClientId(customer.getCustomerId());
			device.setId(TokenGenerator.generateNewToken());
			if (deviceComponent.addDevice(device) == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		} catch (Exception e) {
			log.warn("addDevice error: " + device, e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Transaction
	@Operation(summary = "update a device", description = "api to update a device by id.")
	@PostMapping("/device")
	public ResponseEntity<Void> updateDevice(HttpServletRequest servletRequest, @RequestBody Device device) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		device.setClientId(customer.getCustomerId());
		deviceComponent.updateDevice(device);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Transaction
	@Operation(summary = "delete a device", description = "api to delete a device by id")
	@DeleteMapping("/device/{id}")
	public ResponseEntity<Void> deleteDevice(
			HttpServletRequest servletRequest,
			@Parameter(description = "id of the device to delete") @PathVariable("id") String id) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		deviceComponent.deleteDevice(id, customer.getCustomerId());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Transaction
	@Operation(summary = "get a device", description = "api to get a device's information by id")
	@GetMapping("/device/{id}")
	public ResponseEntity<Device> getDevice(
			HttpServletRequest servletRequest,
			@Parameter(description = "id of the device to find") @PathVariable("id") String id) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return new ResponseEntity<>(
				deviceComponent.findDeviceById(id, customer.getCustomerId()).orElse(null), HttpStatus.OK);
	}

	@Transaction
	@Operation(
			summary = "API to create the dynamodb table if doesn't exist",
			description = "create the dynamodb table if doesn't exist")
	@PostMapping("/createDeviceTable")
	public ResponseEntity<Void> createDeviceTable(HttpServletRequest servletRequest) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null || !customer.isAdmin()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		deviceComponent.createDeviceTable();
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Transaction
	@Operation(
			summary = "get all devices for a specific client",
			description = "Return all devices associated with a client")
	@GetMapping("/devices")
	public ResponseEntity<List<Device>> getDevices(HttpServletRequest servletRequest) {
		Customer customer = AuthUtil.authorize(servletRequest, component);
		if (customer == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return new ResponseEntity<>(deviceComponent.getDevicesForCustomerId(customer.getCustomerId()), HttpStatus.OK);
	}
}
