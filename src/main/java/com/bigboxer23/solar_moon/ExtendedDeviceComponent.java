package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.data.Device;
import com.bigboxer23.solar_moon.utils.TableCreationUtils;
import java.util.Arrays;
import org.springframework.stereotype.Component;

/** */
@Component
public class ExtendedDeviceComponent extends DeviceComponent {

	public void addDevice(Device device) {
		if (getDevice(device.getId(), device.getClientId()) != null) {
			logger.debug(device.getClientId() + ":" + device.getId() + " exists, not putting into db");
			return;
		}
		getTable().putItem(device);
	}

	public void updateDevice(Device device) {
		getTable().updateItem(builder -> builder.item(device));
	}

	public void deleteDevice(String id, String clientId) {
		getTable().deleteItem(new Device(id, clientId));
	}

	public Device getDevice(String id, String clientId) {
		if (id == null || clientId == null) {
			return null;
		}
		return getTable().getItem(new Device(id, clientId));
	}

	/*public Device generateDeviceKey(String id, String clientId, boolean force) {
		Device device = getDevice(id, clientId);
		if (device == null) {
			return device;
		}
		if (device.getDeviceKey() != null && !force)
		{
			return device;
		}
		device.setDeviceKey(generateNewToken());
		updateDevice(device);
		return device;
	}

	public void generateDeviceKeysIfEmpty(String clientId) {
		getDevices(clientId).forEach(page -> page.items().forEach(device -> generateDeviceKey(device.getId(), device.getClientId(), false)));
	}*/

	public void createDeviceTable() {
		TableCreationUtils.createTable(
				Arrays.asList(
						Device.NAME_INDEX, Device.DEVICE_NAME_INDEX, Device.DEVICE_KEY_INDEX, Device.CLIENT_INDEX),
				getTable());
	}
}
