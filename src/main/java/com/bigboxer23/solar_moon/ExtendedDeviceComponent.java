package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.data.Device;
import com.bigboxer23.solar_moon.utils.TableCreationUtils;
import java.util.Arrays;

import com.bigboxer23.solar_moon.web.TransactionUtil;
import org.springframework.stereotype.Component;

/** */
@Component
public class ExtendedDeviceComponent extends DeviceComponent {

	public void addDevice(Device device) {
		if (getDevice(device.getId(), device.getClientId()) != null) {
			logger.debug(device.getClientId() + ":" + device.getId() + " exists, not putting into db." + TransactionUtil.getLoggingStatement());
			return;
		}
		logAction("add", device.getClientId(), device.getId());
		getTable().putItem(device);
	}

	public void updateDevice(Device device) {
		logAction("update", device.getClientId(), device.getId());
		getTable().updateItem(builder -> builder.item(device));
	}

	public void deleteDevice(String id, String customerId) {
		logAction("delete", customerId, id);
		getTable().deleteItem(new Device(id, customerId));
	}

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

	private void logAction(String action, String customerId, String id) {
		logger.debug(customerId + ":" + id + " device " + action + TransactionUtil.getLoggingStatement());
	}
}
