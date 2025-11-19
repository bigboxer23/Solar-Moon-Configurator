package com.bigboxer23.solar_moon.weather;

import com.bigboxer23.solar_moon.util.TableCreationUtils;
import java.util.Collections;

/** */
public class ExtendedPirateWeatherComponent extends PirateWeatherComponent {
	@Override
	public ExtendedDynamoDbWeatherRepository getRepository() {
		return (ExtendedDynamoDbWeatherRepository) super.getRepository();
	}

	public void createWeatherTable() {
		TableCreationUtils.createTable(Collections.emptyList(), getRepository().getTable());
	}
}
