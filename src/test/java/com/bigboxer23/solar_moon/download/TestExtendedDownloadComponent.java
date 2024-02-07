package com.bigboxer23.solar_moon.download;

import org.junit.jupiter.api.Test;

/** */
public class TestExtendedDownloadComponent {
	private static final ExtendedDownloadComponent downloadComponent = new ExtendedDownloadComponent();

	@Test
	public void createDownloadRequestTable() {
		downloadComponent.createDownloadRequestTable();
	}
}
