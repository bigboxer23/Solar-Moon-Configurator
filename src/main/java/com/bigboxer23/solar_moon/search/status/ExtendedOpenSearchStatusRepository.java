package com.bigboxer23.solar_moon.search.status;

import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

/** */
@Repository
public class ExtendedOpenSearchStatusRepository extends DynamoDbOpenSearchStatusRepository {
	@Override
	public DynamoDbTable<OpenSearchStatus> getTable() {
		return super.getTable();
	}
}
