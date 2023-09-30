package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.data.Customer;
import com.bigboxer23.solar_moon.util.TableCreationUtils;
import com.bigboxer23.solar_moon.util.TokenGenerator;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

/** */
@Component
public class ExtendedCustomerComponent extends CustomerComponent {

	public void createCustomerTable() {
		TableCreationUtils.createTable(
				Arrays.asList(Customer.ACCESS_KEY_INDEX, Customer.CUSTOMER_ID_INDEX), getTable());
	}

	public void updateCustomer(Customer customer) {
		if (customer == null || customer.getCustomerId() == null || customer.getEmail() == null) {
			logger.warn("invalid customer passed, not updating");
			return;
		}
		logAction(customer.getCustomerId(), "update");
		getTable().updateItem(builder -> builder.item(customer));
	}

	public void deleteCustomerByEmail(String email) {
		Optional.ofNullable(email).filter(e -> !e.isBlank()).ifPresent(e -> {
			logAction(email, "delete by customer email");
			getTable().deleteItem(new Customer(null, email, null));
		});
	}

	public void deleteCustomerByCustomerId(String customerId) {
		Optional.ofNullable(findCustomerByCustomerId(customerId)).ifPresent(customer -> {
			logAction(customer.getCustomerId(), "delete by customer id");
			getTable().deleteItem(customer);
		});
	}

	private void logAction(String action, String customerId) {
		logger.debug(customerId + " customer " + action);
	}
}
