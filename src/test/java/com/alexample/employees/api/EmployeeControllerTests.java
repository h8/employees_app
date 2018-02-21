package com.alexample.employees.api;

import com.alexample.employees.EmployeesApplication;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.alexample.employees.TestUtils.insertEmployee;
import static com.alexample.employees.TestUtils.insertEmployeeWithTitleAndDepartment;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = EmployeesApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DSLContext dsl;

    @Test
    @Transactional
    public void getCount() throws Exception {
        insertEmployee(dsl, 1);

        mvc.perform(get("/api/employees/count")
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    @Transactional
    public void getEmployeeTitle() throws Exception {
        insertEmployeeWithTitleAndDepartment(dsl, 1, "1");

        mvc.perform(get("/api/employees/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Developer")));
    }

    @Test
    public void getErrorWithSalary() throws Exception {
        mvc.perform(get("/api/employees/search/salaryRange")
                .param("max", "-1"))
                .andExpect(status().isBadRequest());
    }
}
