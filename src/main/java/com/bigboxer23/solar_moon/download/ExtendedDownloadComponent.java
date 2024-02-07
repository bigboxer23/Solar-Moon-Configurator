package com.bigboxer23.solar_moon.download;

import com.bigboxer23.solar_moon.util.TableCreationUtils;

import java.util.Collections;

/**
 *
 */
public class ExtendedDownloadComponent extends DownloadComponent
{
	public void createDownloadRequestTable() {
		TableCreationUtils.createTable(Collections.emptyList(), getTable());
	}

}
