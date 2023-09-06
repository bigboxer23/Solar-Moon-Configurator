package com.bigboxer23.solar_moon;

import com.bigboxer23.solar_moon.data.Customer;
import com.bigboxer23.solar_moon.utils.TableCreationUtils;
import com.bigboxer23.solar_moon.utils.TokenGenerator;
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

	public Customer addCustomer(String email, String customerId) {
		if (email == null || customerId == null || email.isBlank() || customerId.isBlank()) {
			logger.warn("email or customer id is null/empty, cannot fetch.");
			return null;
		}
		if (findCustomerById(customerId) != null) {
			logger.debug(customerId + ":" + email + " exists, not putting into db");
			return null;
		}
		Customer customer = new Customer(customerId, email, TokenGenerator.generateNewToken());
		getTable().putItem(customer);
		return customer;
	}

	public void updateCustomer(Customer customer) {
		if (customer == null || customer.getCustomerId() == null || customer.getEmail() == null) {
			logger.warn("invalid customer passed, not updating");
			return;
		}
		getTable().updateItem(builder -> builder.item(customer));
	}

	public void deleteCustomerByEmail(String email) {
		Optional.ofNullable(email).filter(e -> !e.isBlank()).ifPresent(e -> getTable()
				.deleteItem(new Customer(null, email, null)));
	}

	public void deleteCustomerByCustomerId(String customerId) {
		Optional.ofNullable(findCustomerById(customerId))
				.ifPresent(customer -> getTable().deleteItem(customer));
	}

	public Customer findCustomerById(String customerId) {
		return customerId != null && !customerId.isEmpty()
				? this.getTable()
						.index(Customer.CUSTOMER_ID_INDEX)
						.query(QueryConditional.keyEqualTo((builder) -> builder.partitionValue(customerId)))
						.stream()
						.findFirst()
						.flatMap((page) -> page.items().stream().findFirst())
						.orElse(null)
				: null;
	}
}
