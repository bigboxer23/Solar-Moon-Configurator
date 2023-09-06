package com.bigboxer23.solar_moon;

import static org.junit.jupiter.api.Assertions.*;

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
		assertNull(component.addCustomer(null, null));
		assertNull(component.addCustomer(TestCustomerComponent.CUSTOMER_EMAIL, null));
		assertNull(component.addCustomer(null, TestDeviceComponent.clientId));
		assertNull(component.addCustomer("", ""));
		component.deleteCustomerByCustomerId(TestDeviceComponent.clientId);
		component.addCustomer(TestCustomerComponent.CUSTOMER_EMAIL, TestDeviceComponent.clientId);
		assertNotNull(component.findCustomerById(TestDeviceComponent.clientId));
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
		assertNotNull(component.findCustomerById(TestDeviceComponent.clientId));
		assertNull(component.findCustomerById("tacos"));
		assertNull(component.findCustomerById(""));
		assertNull(component.findCustomerById(null));
	}

	@Test
	public void testUpdateCustomer() {
		new TestCustomerComponent().setupTestCustomer();
		Customer customer = component.findCustomerById(TestDeviceComponent.clientId);
		component.updateCustomer(null);
		customer.setAccessKey("tacos");
		component.updateCustomer(customer);
		assertEquals(
				"tacos",
				component.findCustomerById(TestDeviceComponent.clientId).getAccessKey());
	}
}
