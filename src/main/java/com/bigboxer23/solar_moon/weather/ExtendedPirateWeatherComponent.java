package com.bigboxer23.solar_moon.weather;

import com.bigboxer23.solar_moon.util.TableCreationUtils;

import java.util.Collections;

/**
 *
 */
public class ExtendedPirateWeatherComponent extends PirateWeatherComponent
{
	public void createWeatherTable() {
		TableCreationUtils.createTable(Collections.emptyList(), getTable());
	}

}
