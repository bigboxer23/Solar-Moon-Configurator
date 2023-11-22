package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.maintenance.ExtendedMaintenanceComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/** */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TestExtendedMaintenanceComponent {
	@Autowired
	private ExtendedMaintenanceComponent component;

	@Test
	public void createMaintenanceTable() {
		component.createMaintenanceTable();
	}
}
