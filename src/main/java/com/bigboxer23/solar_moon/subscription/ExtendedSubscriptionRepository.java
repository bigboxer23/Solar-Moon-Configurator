package com.bigboxer23.solar_moon.subscription;

import com.bigboxer23.solar_moon.data.Subscription;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

/** */
@Repository
public class ExtendedSubscriptionRepository extends DynamoDbSubscriptionRepository {
	@Override
	public DynamoDbTable<Subscription> getTable() {
		return super.getTable();
	}
}
