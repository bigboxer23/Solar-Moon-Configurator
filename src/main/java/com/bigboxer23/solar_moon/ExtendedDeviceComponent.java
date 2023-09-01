package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.data.Device;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.model.EnhancedGlobalSecondaryIndex;
import software.amazon.awssdk.services.dynamodb.model.ProjectionType;

/** */
@Component
public class ExtendedDeviceComponent extends DeviceComponent {
	public void addDevice(Device device) {
		// TODO:validate
		if (getDevice(device.getId(), device.getClientId()) != null) {
			logger.debug(device.getClientId() + ":" + device.getId() + " exists, not putting into db");
			return;
		}
		getDeviceTable().putItem(device);
	}

	public void updateDevice(Device device) {
		getDeviceTable().updateItem(builder -> builder.item(device));
	}

	public void deleteDevice(String id, String clientId) {
		getDeviceTable().deleteItem(new Device(id, clientId));
	}

	public Device getDevice(String id, String clientId) {
		return getDeviceTable().getItem(new Device(id, clientId));
	}

	public void createDeviceTable() {
		getDeviceTable()
				.createTable(builder -> builder.globalSecondaryIndices(
						getBuilder(Device.NAME_INDEX),
						getBuilder(Device.DEVICE_NAME_INDEX),
						getBuilder(Device.DEVICE_KEY_INDEX),
						getBuilder(Device.CLIENT_INDEX)));
	}

	private EnhancedGlobalSecondaryIndex getBuilder(String indexName) {
		return EnhancedGlobalSecondaryIndex.builder()
				.indexName(indexName)
				.projection(projectionBuilder ->
						projectionBuilder.projectionType(ProjectionType.ALL).build())
				.build();
	}
}
