package com.bigboxer23.solar_moon.device;

import com.bigboxer23.solar_moon.util.TableCreationUtils;
import java.util.Collections;
import org.springframework.stereotype.Component;

/** */
@Component
public class ExtendedLinkedDeviceComponent extends LinkedDeviceComponent {

	@Override
	public ExtendedLinkedDeviceRepository getRepository() {
		return (ExtendedLinkedDeviceRepository) super.getRepository();
	}

	public void createDeviceUpdateTable() {
		TableCreationUtils.createTable(Collections.emptyList(), getRepository().getTable());
	}
}
