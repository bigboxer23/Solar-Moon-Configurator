package com.bigboxer23.solar_moon.customer;

import com.bigboxer23.solar_moon.data.Customer;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

/** */
@Repository
public class ExtendedCustomerRepository extends DynamoDbCustomerRepository {
	@Override
	public DynamoDbTable<Customer> getTable() {
		return super.getTable();
	}
}
