package com.bigboxer23.solar_moon.mapping;

import com.bigboxer23.solar_moon.util.TableCreationUtils;
import java.util.Collections;

/** */
public class ExtendedMappingComponent extends MappingComponent {
	@Override
	public ExtendedMappingRepository getRepository() {
		return (ExtendedMappingRepository) super.getRepository();
	}

	public void createMappingTable() {
		TableCreationUtils.createTable(Collections.emptyList(), getRepository().getTable());
	}
}
