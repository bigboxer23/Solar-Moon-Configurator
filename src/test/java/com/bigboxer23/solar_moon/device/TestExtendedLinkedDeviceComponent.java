package com.bigboxer23.solar_moon.device;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/** */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TestExtendedLinkedDeviceComponent {
	@Autowired
	private ExtendedLinkedDeviceComponent component;

	@Test
	public void createDeviceUpdateTable() {
		component.createDeviceUpdateTable();
	}
}
