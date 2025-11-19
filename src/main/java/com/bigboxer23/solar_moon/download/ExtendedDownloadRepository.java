package com.bigboxer23.solar_moon.download;

import com.bigboxer23.solar_moon.data.DownloadRequest;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

/** */
@Repository
public class ExtendedDownloadRepository extends DynamoDbDownloadRepository {
	@Override
	public DynamoDbTable<DownloadRequest> getTable() {
		return super.getTable();
	}
}
