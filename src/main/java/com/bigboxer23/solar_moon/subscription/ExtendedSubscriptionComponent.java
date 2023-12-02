package com.bigboxer23.solar_moon.subscription;

import com.bigboxer23.solar_moon.util.TableCreationUtils;
import java.util.Collections;
import org.springframework.stereotype.Component;

/** */
@Component
public class ExtendedSubscriptionComponent extends SubscriptionComponent {
	public void createSubscriptionTable() {
		TableCreationUtils.createTable(Collections.emptyList(), getTable());
	}
}
