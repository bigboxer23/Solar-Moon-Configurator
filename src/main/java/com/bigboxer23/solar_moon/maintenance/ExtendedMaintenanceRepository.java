package com.bigboxer23.solar_moon.maintenance;

import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

/** */
@Repository
public class ExtendedMaintenanceRepository extends DynamoDbMaintenanceRepository {
	@Override
	public DynamoDbTable<MaintenanceMode> getTable() {
		return super.getTable();
	}
}
