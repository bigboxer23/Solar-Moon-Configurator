package com.bigboxer23.solar_moon.device;

import com.bigboxer23.solar_moon.data.LinkedDevice;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

/** */
@Repository
public class ExtendedLinkedDeviceRepository extends DynamoDbLinkedDeviceRepository {
	@Override
	public DynamoDbTable<LinkedDevice> getTable() {
		return super.getTable();
	}
}
