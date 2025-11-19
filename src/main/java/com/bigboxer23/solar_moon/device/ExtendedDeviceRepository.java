package com.bigboxer23.solar_moon.device;

import com.bigboxer23.solar_moon.data.Device;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

/** */
@Repository
public class ExtendedDeviceRepository extends DynamoDbDeviceRepository {
	@Override
	public DynamoDbTable<Device> getTable() {
		return super.getTable();
	}
}
