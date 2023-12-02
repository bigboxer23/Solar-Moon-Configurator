package com.bigboxer23.solar_moon.device;

import com.bigboxer23.solar_moon.data.DeviceUpdateData;
import com.bigboxer23.solar_moon.util.TableCreationUtils;
import java.util.Collections;
import org.springframework.stereotype.Component;

/** */
@Component
public class ExtendedDeviceUpdateComponent extends DeviceUpdateComponent {
	public void createDeviceUpdateTable() {
		TableCreationUtils.createTable(Collections.singletonList(DeviceUpdateData.IDENTITY_UPDATE_INDEX), getTable());
	}
}
