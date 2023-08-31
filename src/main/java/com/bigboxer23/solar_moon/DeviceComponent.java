package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.data.Device;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.EnhancedGlobalSecondaryIndex;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.model.ProjectionType;

/** */
@Component
public class DeviceComponent {

	private static final Logger logger = LoggerFactory.getLogger(DeviceComponent.class);

	private DynamoDbEnhancedClient client;

	private DynamoDbEnhancedClient getClient() {
		if (client == null) {
			client = DynamoDbEnhancedClient.create();
		}
		return client;
	}

	private DynamoDbTable<Device> getDeviceTable() {
		return getClient().table(Device.TABLE_NAME, TableSchema.fromBean(Device.class));
	}

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

	public Stream<Page<Device>> getDevices(String clientId) {
		return getDeviceTable()
				.index(Device.CLIENT_INDEX)
				.query(QueryConditional.keyEqualTo(builder -> builder.partitionValue(clientId)))
				.stream();
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
