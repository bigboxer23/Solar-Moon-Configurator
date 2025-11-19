package com.bigboxer23.solar_moon.device;

import com.bigboxer23.solar_moon.data.DeviceUpdateData;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

/** */
@Repository
public class ExtendedDeviceUpdateRepository extends DynamoDbDeviceUpdateRepository {
	@Override
	public DynamoDbTable<DeviceUpdateData> getTable() {
		return super.getTable();
	}
}
