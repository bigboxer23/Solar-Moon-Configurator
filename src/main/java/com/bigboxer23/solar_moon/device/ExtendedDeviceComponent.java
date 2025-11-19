package com.bigboxer23.solar_moon.device;

import com.bigboxer23.solar_moon.data.Device;
import com.bigboxer23.solar_moon.util.TableCreationUtils;
import java.util.Arrays;
import org.springframework.stereotype.Component;

/** */
@Component
public class ExtendedDeviceComponent extends DeviceComponent {

	@Override
	public ExtendedDeviceRepository getRepository() {
		return (ExtendedDeviceRepository) super.getRepository();
	}

	public void createDeviceTable() {
		TableCreationUtils.createTable(
				Arrays.asList(
						Device.NAME_INDEX,
						Device.DEVICE_NAME_INDEX,
						Device.DEVICE_KEY_INDEX,
						Device.CLIENT_INDEX,
						Device.SITE_INDEX,
						Device.SITEID_INDEX,
						Device.VIRTUAL_INDEX),
				getRepository().getTable());
	}
}
