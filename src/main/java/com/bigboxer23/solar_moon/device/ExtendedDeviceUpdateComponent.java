package com.bigboxer23.solar_moon.device;

import com.bigboxer23.solar_moon.DeviceUpdateComponent;
import com.bigboxer23.solar_moon.data.DeviceUpdateData;
import com.bigboxer23.solar_moon.util.TableCreationUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 *
 */
@Component
public class ExtendedDeviceUpdateComponent extends DeviceUpdateComponent
{
	public void createDeviceUpdateTable() {
		TableCreationUtils.createTable(Collections.singletonList(DeviceUpdateData.IDENTITY_UPDATE_INDEX), getTable());
	}
}
