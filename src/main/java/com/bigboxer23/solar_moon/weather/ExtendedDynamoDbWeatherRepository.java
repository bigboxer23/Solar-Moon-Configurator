package com.bigboxer23.solar_moon.weather;

import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

/** */
@Repository
public class ExtendedDynamoDbWeatherRepository extends DynamoDbWeatherRepository {
	public DynamoDbTable<StoredWeatherData> getTable() {
		return super.getTable();
	}
}
