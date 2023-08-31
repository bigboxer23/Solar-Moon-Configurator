package com.bigboxer23.solar_moon.cognito;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithSecurityContext;

/** */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@WithSecurityContext(factory = WithMockAwsCognitoUserSecurityContextFactory.class)
public @interface WithAwsCognitoUser {
	String username() default "";

	String roles() default "";

	String email() default "bigboxer23@gmail.com";

	TestExecutionEvent setupBefore() default TestExecutionEvent.TEST_METHOD;
}
