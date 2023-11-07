package com.bigboxer23.solar_moon;

import static org.junit.jupiter.api.Assertions.*;

import com.bigboxer23.solar_moon.customer.ExtendedCustomerComponent;
import com.bigboxer23.solar_moon.data.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/** */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TestExtendedCustomerComponent {
	@Autowired
	private ExtendedCustomerComponent component;

	@Test
	public void testAddCustomer() {
		assertNull(component.addCustomer(
				null, null, TestCustomerComponent.CUSTOMER_NAME, TestCustomerComponent.CUSTOMER_STRIPE_ID));
		assertNull(component.addCustomer(
				TestCustomerComponent.CUSTOMER_EMAIL,
				null,
				TestCustomerComponent.CUSTOMER_NAME,
				TestCustomerComponent.CUSTOMER_STRIPE_ID));
		assertNull(component.addCustomer(
				null,
				TestDeviceComponent.clientId,
				TestCustomerComponent.CUSTOMER_NAME,
				TestCustomerComponent.CUSTOMER_STRIPE_ID));
		assertNull(component.addCustomer("", "", "", ""));
		component.deleteCustomerByCustomerId(TestDeviceComponent.clientId);
		component.addCustomer(
				TestCustomerComponent.CUSTOMER_EMAIL,
				TestDeviceComponent.clientId,
				TestCustomerComponent.CUSTOMER_NAME,
				TestCustomerComponent.CUSTOMER_STRIPE_ID);
		assertNotNull(component.findCustomerByCustomerId(TestDeviceComponent.clientId));
		component.deleteCustomerByCustomerId(TestDeviceComponent.clientId);
	}

	@Test
	public void testDeleteCustomer() {
		component.deleteCustomerByEmail(null);
		component.deleteCustomerByEmail("");
		component.deleteCustomerByEmail(TestCustomerComponent.CUSTOMER_EMAIL);
		component.deleteCustomerByCustomerId(null);
		component.deleteCustomerByCustomerId("");
		component.deleteCustomerByCustomerId(TestDeviceComponent.clientId);
	}

	@Test
	public void testFindCustomer() {
		new TestCustomerComponent().setupTestCustomer();
		assertNotNull(component.findCustomerByCustomerId(TestDeviceComponent.clientId));
		assertNull(component.findCustomerByCustomerId("tacos"));
		assertNull(component.findCustomerByCustomerId(""));
		assertNull(component.findCustomerByCustomerId(null));
	}

	@Test
	public void testUpdateCustomer() {
		new TestCustomerComponent().setupTestCustomer();
		Customer customer = component.findCustomerByCustomerId(TestDeviceComponent.clientId);
		component.updateCustomer(null);
		customer.setAccessKey("tacos");
		component.updateCustomer(customer);
		assertEquals(
				"tacos",
				component.findCustomerByCustomerId(TestDeviceComponent.clientId).getAccessKey());
	}
}
