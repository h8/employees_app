package com.alexample.employees;

import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeesApplicationTests {

	@Autowired
    DSLContext dsl;

	@Test
	public void contextLoads() {
	    assertNotNull(dsl);
        assertNotNull(dsl.fetch("SELECT 1"));
	}
}
