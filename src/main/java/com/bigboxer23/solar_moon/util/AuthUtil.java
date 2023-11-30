package com.bigboxer23.solar_moon.util;

import com.bigboxer23.solar_moon.CustomerComponent;
import com.bigboxer23.solar_moon.data.Customer;
import com.bigboxer23.solar_moon.lambda.data.CognitoUserAttributes;
import com.squareup.moshi.Moshi;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/** */
public class AuthUtil {
	private static final Moshi moshi = new Moshi.Builder().build();

	private static final Logger logger = LoggerFactory.getLogger(AuthUtil.class);

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
			logger.warn("authorize", e);
		}
		return null;
	}
}
