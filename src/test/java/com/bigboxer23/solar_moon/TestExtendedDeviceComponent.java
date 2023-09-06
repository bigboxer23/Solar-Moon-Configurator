package com.bigboxer23.solar_moon;

import static org.junit.jupiter.api.Assertions.*;

import com.bigboxer23.solar_moon.data.Device;
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

		Device dbDevice = component.getDevice(device.getId(), device.getClientId());
		assertEquals(device.getSite(), dbDevice.getSite());
		dbDevice.setSite("test site 2");
		component.updateDevice(dbDevice);
		assertEquals(
				dbDevice.getSite(),
				component.getDevice(dbDevice.getId(), dbDevice.getClientId()).getSite());
		component.deleteDevice(dbDevice.getId(), dbDevice.getClientId());
		assertNull(component.getDevice(dbDevice.getId(), dbDevice.getClientId()));
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
