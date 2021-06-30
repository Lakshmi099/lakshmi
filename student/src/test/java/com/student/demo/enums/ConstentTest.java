
package com.student.demo.enums;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EnumSet;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ConstentTest {

	@ParameterizedTest

	@EnumSource(value = Constent.class, names = { "STUDENT_DEMO_APP_NAME", "STUDENT_APP_NAME" })
	public void testContent(Constent con) {

		assertTrue(EnumSet.of(Constent.STUDENT_APP_NAME, Constent.STUDENT_DEMO_APP_NAME).contains(con));

	}

}
