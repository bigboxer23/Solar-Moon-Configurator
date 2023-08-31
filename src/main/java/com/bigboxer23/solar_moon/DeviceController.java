package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.data.Device;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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

	private final DeviceComponent deviceComponent;

	public DeviceController(DeviceComponent deviceComponent) {
		this.deviceComponent = deviceComponent;
	}

	// @PreAuthorize("")
	@Operation(summary = "add a device", description = "api to add a device")
	@PutMapping("/device")
	public ResponseEntity<Void> addDevice(@RequestBody Device device) {
		try {
			device.setClientId(getClientId());
			deviceComponent.addDevice(device);
		} catch (Exception e) {
			logger.warn("addDevice error: " + device, e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Operation(summary = "update a device", description = "api to update a device by id.")
	@PostMapping("/device")
	public ResponseEntity<Void> updateDevice(@RequestBody Device device) {
		device.setClientId(getClientId());
		deviceComponent.updateDevice(device);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Operation(summary = "delete a device", description = "api to delete a device by id")
	@DeleteMapping("/device/{id}")
	public ResponseEntity<Void> deleteDevice(
			@Parameter(description = "id of the device to delete") @PathVariable("id") String id) {
		deviceComponent.deleteDevice(id, getClientId());
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@Operation(summary = "get a device", description = "api to get a device's information by id")
	@GetMapping("/device/{id}")
	public ResponseEntity<Device> getDevice(
			@Parameter(description = "id of the device to find") @PathVariable("id") String id) {
		return new ResponseEntity<>(deviceComponent.getDevice(id, getClientId()), HttpStatus.OK);
	}

	@Operation(
			summary = "API to create the dynamodb table if doesn't exist",
			description = "create the dynamodb table if doesn't exist")
	@PostMapping("/createTable")
	public ResponseEntity<Void> createDeviceTable() {
		deviceComponent.createDeviceTable();
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	private String getClientId() {
		return "e8dfcdfd-0752-403c-a3bb-df8e1ff6a873"; // TODO:temp, fetch from cognito
	}
}
