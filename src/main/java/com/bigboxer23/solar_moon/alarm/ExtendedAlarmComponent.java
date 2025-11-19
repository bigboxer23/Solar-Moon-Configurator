package com.bigboxer23.solar_moon.alarm;

import com.bigboxer23.solar_moon.data.Alarm;
import com.bigboxer23.solar_moon.util.TableCreationUtils;
import java.util.Arrays;
import org.springframework.stereotype.Component;

/** */
@Component
public class ExtendedAlarmComponent extends AlarmComponent {

	@Override
	public ExtendedAlarmRepository getRepository() {
		return (ExtendedAlarmRepository) super.getRepository();
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
				getRepository().getTable());
	}
}
