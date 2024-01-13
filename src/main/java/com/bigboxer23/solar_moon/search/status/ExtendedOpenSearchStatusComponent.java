package com.bigboxer23.solar_moon.search.status;

import com.bigboxer23.solar_moon.util.TableCreationUtils;

import java.util.Collections;

/**
 *
 */
public class ExtendedOpenSearchStatusComponent extends OpenSearchStatusComponent
{

	public void createOpenSearchStatusTable() {
		TableCreationUtils.createTable(Collections.emptyList(), getTable());
	}
}
