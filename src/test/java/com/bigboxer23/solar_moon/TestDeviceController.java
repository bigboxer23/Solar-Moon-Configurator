package com.bigboxer23.solar_moon;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bigboxer23.solar_moon.cognito.WithAwsCognitoUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/** */
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/*@ActiveProfiles("test")*/
@WebMvcTest
public class TestDeviceController {

	@Autowired
	protected MockMvc mockMvc;

	@Test
	@WithAwsCognitoUser(roles = "[COMMENTER]")
	void givenRequestFromCommenter_deleteComments_shouldBeForbidden() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/device")).andExpect(status().is4xxClientError());
		System.out.println(status().is2xxSuccessful());
		MvcResult result =
				mockMvc.perform(MockMvcRequestBuilders.post("/device")).andReturn();
		System.out.println(result);
		// MockMvcRequestBuilders.post("/device").
	}
}
