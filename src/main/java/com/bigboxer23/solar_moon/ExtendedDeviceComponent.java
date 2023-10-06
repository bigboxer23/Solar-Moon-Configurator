package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.data.Device;
import com.bigboxer23.solar_moon.util.TableCreationUtils;
import java.util.Arrays;
import org.springframework.stereotype.Component;

/** */
@Component
public class ExtendedDeviceComponent extends DeviceComponent {
	public void createDeviceTable() {
		TableCreationUtils.createTable(
				Arrays.asList(
						Device.NAME_INDEX,
						Device.DEVICE_NAME_INDEX,
						Device.DEVICE_KEY_INDEX,
						Device.CLIENT_INDEX,
						Device.SITE_INDEX,
						Device.VIRTUAL_INDEX),
				getTable());
	}
}
