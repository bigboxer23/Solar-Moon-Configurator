package com.bigboxer23.solar_moon.customer;

import com.bigboxer23.solar_moon.data.Customer;
import com.bigboxer23.solar_moon.util.TableCreationUtils;
import java.util.Arrays;
import org.springframework.stereotype.Component;

/** */
@Component
public class ExtendedCustomerComponent extends CustomerComponent {

	@Override
	public ExtendedCustomerRepository getRepository() {
		return (ExtendedCustomerRepository) super.getRepository();
	}

	public void createCustomerTable() {
		TableCreationUtils.createTable(
				Arrays.asList(Customer.ACCESS_KEY_INDEX, Customer.CUSTOMER_ID_INDEX),
				getRepository().getTable());
	}
}
