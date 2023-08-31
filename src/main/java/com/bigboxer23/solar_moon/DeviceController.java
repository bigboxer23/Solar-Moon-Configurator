package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.data.Device;
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
	@PutMapping("/device")
	public ResponseEntity<Void> addDevice(@RequestBody Device device) {
		try {
			device.setClientId("e8dfcdfd-0752-403c-a3bb-df8e1ff6a873"); // TODO:temp, fetch from cognito
			deviceComponent.addDevice(device);
		} catch (Exception e) {
			logger.warn("addDevice error: " + device, e);
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping("/device/{id}")
	public ResponseEntity<Device> getDevice(@PathVariable("id") String id) {
		return new ResponseEntity<>(deviceComponent.getDevice(id, "sampleClientId"), HttpStatus.OK);
	}

	@PostMapping("/virtualDevice")
	public ResponseEntity<String> addVirtualDevice(Device device) {
		// TODO:
		// addDevice(device);
		return null;
	}

	@PostMapping("/createDeviceTable")
	public ResponseEntity<Void> createDeviceTable() {
		deviceComponent.createDeviceTable();
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
