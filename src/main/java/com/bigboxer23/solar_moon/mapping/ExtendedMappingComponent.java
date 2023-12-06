package com.bigboxer23.solar_moon.mapping;

import com.bigboxer23.solar_moon.util.TableCreationUtils;
import java.util.Collections;

/** */
public class ExtendedMappingComponent extends MappingComponent {
	public void createMappingTable() {
		TableCreationUtils.createTable(Collections.emptyList(), getTable());
	}
}
