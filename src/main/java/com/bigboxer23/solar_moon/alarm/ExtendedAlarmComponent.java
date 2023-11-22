package com.bigboxer23.solar_moon.alarm;

import com.bigboxer23.solar_moon.AlarmComponent;
import com.bigboxer23.solar_moon.DeviceComponent;
import com.bigboxer23.solar_moon.OpenWeatherComponent;
import com.bigboxer23.solar_moon.data.Alarm;
import com.bigboxer23.solar_moon.notifications.NotificationComponent;
import com.bigboxer23.solar_moon.open_search.OpenSearchComponent;
import com.bigboxer23.solar_moon.util.TableCreationUtils;
import java.util.Arrays;
import org.springframework.stereotype.Component;

/** */
@Component
public class ExtendedAlarmComponent extends AlarmComponent {

	public ExtendedAlarmComponent(
			OpenWeatherComponent openWeatherComponent,
			DeviceComponent deviceComponent,
			OpenSearchComponent OSComponent,
			NotificationComponent notificationComponent) {
		super(openWeatherComponent, deviceComponent, OSComponent, notificationComponent);
	}

	public void createAlarmTable() {
		TableCreationUtils.createTable(
				Arrays.asList(
						Alarm.CUSTOMER_INDEX,
						Alarm.SITE_CUSTOMER_INDEX,
						Alarm.SITE_STATE_INDEX,
						Alarm.DEVICE_STATE_INDEX,
						Alarm.STATE_CUSTOMER_INDEX,
						Alarm.DEVICE_CUSTOMER_INDEX,
						Alarm.DEVICEID_STARTDATE_INDEX),
				getTable());
	}
}
