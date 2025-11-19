package com.bigboxer23.solar_moon.alarm;

import com.bigboxer23.solar_moon.data.Alarm;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

/** */
@Repository
public class ExtendedAlarmRepository extends DynamoDbAlarmRepository {
	@Override
	public DynamoDbTable<Alarm> getTable() {
		return super.getTable();
	}
}
