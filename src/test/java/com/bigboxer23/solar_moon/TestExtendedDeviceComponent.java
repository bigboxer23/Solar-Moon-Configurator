package com.bigboxer23.solar_moon;

import static org.junit.jupiter.api.Assertions.*;

import com.bigboxer23.solar_moon.data.Device;
import com.bigboxer23.solar_moon.device.ExtendedDeviceComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/** */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TestExtendedDeviceComponent {

	@Autowired
	ExtendedDeviceComponent component;

	@Test
	public void testAddDevice() {
		Device device = new Device("testSample id", "test1234");
		device.setSite("test site");
		component.addDevice(device);

		Device dbDevice =
				component.findDeviceById(device.getId(), device.getClientId()).orElse(null);
		assertNotNull(dbDevice);
		assertEquals(device.getSite(), dbDevice.getSite());
		dbDevice.setSite("test site 2");
		component.updateDevice(dbDevice);
		assertEquals(
				dbDevice.getSite(),
				component
						.findDeviceById(dbDevice.getId(), dbDevice.getClientId())
						.map(Device::getSite)
						.get());
		component.deleteDevice(dbDevice.getId(), dbDevice.getClientId());
		assertFalse(component
				.findDeviceById(dbDevice.getId(), dbDevice.getClientId())
				.isPresent());
	}

	/*@Test
	public void testGenerateDeviceKey() {
		new TestDeviceComponent().setupTestDevice();
		Device device = component.generateDeviceKey(TestDeviceComponent.deviceId, TestDeviceComponent.clientId, false);
		assertNotNull(device);
		assertNotNull(device.getDeviceKey());

		assertNull(component.generateDeviceKey("invalid", "invalid", false));
		assertNull(component.generateDeviceKey(null, null, false));
	}*/
}
