package com.bigboxer23.solar_moon.mapping;

import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

/** */
@Repository
public class ExtendedMappingRepository extends DynamoDbMappingRepository {
	@Override
	public DynamoDbTable<AttributeMap> getTable() {
		return super.getTable();
	}
}
