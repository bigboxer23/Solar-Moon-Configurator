package com.bigboxer23.solar_moon.util;

import com.bigboxer23.solar_moon.customer.CustomerComponent;
import com.bigboxer23.solar_moon.data.Customer;
import com.bigboxer23.solar_moon.lambda.data.CognitoUserAttributes;
import com.squareup.moshi.Moshi;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

/** */
@Slf4j
public class AuthUtil {
	private static final Moshi moshi = new Moshi.Builder().build();

	public static Customer authorize(HttpServletRequest servletRequest, CustomerComponent component) {

		String[] chunks = Optional.ofNullable(servletRequest.getHeader(HttpHeaders.AUTHORIZATION))
				.map(authHeader -> authHeader.split("\\."))
				.orElse(null);
		if (chunks == null || chunks.length != 3) {
			return null;
		}
		try {
			return Optional.ofNullable(moshi.adapter(CognitoUserAttributes.class)
							.fromJson(new String(Base64.getUrlDecoder().decode(chunks[1]))))
					.map(CognitoUserAttributes::getSub)
					.flatMap(component::findCustomerByCustomerId)
					.orElse(null);
		} catch (IOException e) {
			log.warn("authorize", e);
		}
		return null;
	}
}
