package com.bigboxer23.solar_moon.maintenance;

import com.bigboxer23.solar_moon.util.TableCreationUtils;
import java.util.Collections;
import org.springframework.stereotype.Component;

/** */
@Component
public class ExtendedMaintenanceComponent extends MaintenanceComponent {
	public void createMaintenanceTable() {
		TableCreationUtils.createTable(Collections.emptyList(), getTable());
	}
}
