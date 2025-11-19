package com.bigboxer23.solar_moon.download;

import com.bigboxer23.solar_moon.util.TableCreationUtils;
import java.util.Collections;

/** */
public class ExtendedDownloadComponent extends DownloadComponent {

	@Override
	public ExtendedDownloadRepository getRepository() {
		return (ExtendedDownloadRepository) super.getRepository();
	}

	public void createDownloadRequestTable() {
		TableCreationUtils.createTable(Collections.emptyList(), getRepository().getTable());
	}
}
