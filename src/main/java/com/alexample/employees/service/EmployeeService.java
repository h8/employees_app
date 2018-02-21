package com.alexample.employees.service;

import com.alexample.employees.dao.ElasticDAO;
import com.alexample.employees.dto.EmployeeDTO;
import com.alexample.generated.db.tables.records.DepartmentsRecord;
import com.alexample.generated.db.tables.records.EmployeesRecord;
import com.alexample.generated.db.tables.records.SalariesRecord;
import com.alexample.generated.db.tables.records.TitlesRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectOnConditionStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.alexample.generated.db.tables.Departments.DEPARTMENTS;
import static com.alexample.generated.db.tables.DeptEmp.DEPT_EMP;
import static com.alexample.generated.db.tables.Employees.EMPLOYEES;
import static com.alexample.generated.db.tables.Salaries.SALARIES;
import static com.alexample.generated.db.tables.Titles.TITLES;

@Service
public class EmployeeService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private DSLContext dsl;

    private ElasticDAO elasticDAO;

    @Autowired
    public EmployeeService(DSLContext dsl, ElasticDAO elasticDAO)
    {
        this.dsl = dsl;
        this.elasticDAO = elasticDAO;
    }

    public int getCount() {
        return dsl.selectCount().from(EMPLOYEES).fetchOne(0, int.class);
    }

    public Optional<EmployeeDTO> getById(int id) {
        List<Record> one = fetchById(id);

        if (one.size() > 0) {
            return Optional.of(withDepTitleDTO(one.get(0)));
        } else {
            return Optional.empty();
        }
    }

    public List<EmployeeDTO> getWithTitleAndDepartment(int startFrom, int limit) {
        return getTitleDep(startFrom, limit)
                .stream()
                .map(this::withDepTitleDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getWithSalaries(
            int startFrom, int limit,
            int minSalary, int maxSalary
    ) {
        return getSalaries(startFrom, limit, minSalary, maxSalary)
                .stream()
                .map(this::withSalaryDTO)
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getWithDepartmentByUserWithActivity(int id, int startFrom, int limit) {
        Optional<String> elasticResult;

        try {
            elasticResult = elasticDAO.hasClicksAndViews(id);
        } catch (IOException e) {
            log.error("Failed to get data from ElasticSearch.", e);
            return Collections.emptyList();
        }

        if (elasticResult.isPresent()) {
            String depNo = elasticResult.get();

            return getByDep(depNo, startFrom, limit)
                    .stream()
                    .map(this::withEmployeeOnlyDTO)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    private EmployeeDTO withDepTitleDTO(Record r) {
        EmployeesRecord employee = r.into(EMPLOYEES);
        DepartmentsRecord department = r.into(DEPARTMENTS);
        TitlesRecord title = r.into(TITLES);

        return EmployeeDTO.buildFrom(employee, department, title);
    }

    private EmployeeDTO withSalaryDTO(Record r) {
        EmployeesRecord employee = r.into(EMPLOYEES);
        SalariesRecord salary = r.into(SALARIES);

        return EmployeeDTO.buildFrom(employee, salary);
    }

    private EmployeeDTO withEmployeeOnlyDTO(Record r) {
        return EmployeeDTO.buildFrom(r.into(EMPLOYEES));
    }

    private List<Record> getTitleDep(int startFrom, int limit) {
        return queryDepTitle()
                .orderBy(EMPLOYEES.EMP_NO)
                .seek(startFrom)
                .limit(limit)
                .fetch();
    }

    private List<Record> getSalaries(
            int startFrom, int limit,
            int minSalary, int maxSalary
    ) {
        return querySalaries()
                .where(SALARIES.SALARY.between(minSalary, maxSalary))
                .orderBy(EMPLOYEES.EMP_NO)
                .seek(startFrom)
                .limit(limit)
                .fetch();
    }

    private List<Record> fetchById(int id) {
        return queryDepTitle()
                .where(EMPLOYEES.EMP_NO.eq(id))
                .fetch();
    }

    private List<Record> getByDep(String depNo, int startFrom, int limit) {
        return queryByDepartment()
                .where(DEPT_EMP.DEPT_NO.eq(depNo))
                .orderBy(EMPLOYEES.EMP_NO)
                .seek(startFrom)
                .limit(limit)
                .fetch();
    }

    private SelectOnConditionStep<Record> querySalaries() {
        return dsl.select()
                .from(EMPLOYEES)

                .join(SALARIES)
                .on(EMPLOYEES.EMP_NO
                        .eq(SALARIES.EMP_NO)
                        .and(SALARIES.FROM_DATE.in(
                                dsl.select(SALARIES.FROM_DATE.max())
                                        .from(SALARIES)
                                        .where(SALARIES.EMP_NO.eq(EMPLOYEES.EMP_NO)))));
    }

    private SelectOnConditionStep<Record> queryByDepartment() {
        return dsl.select()
                .from(DEPT_EMP)
                .join(EMPLOYEES).on(EMPLOYEES.EMP_NO.eq(DEPT_EMP.EMP_NO));
    }

    private SelectOnConditionStep<Record> queryDepTitle() {
        return dsl.select()
                .from(EMPLOYEES)

                .join(DEPT_EMP)
                .on(EMPLOYEES.EMP_NO
                        .eq(DEPT_EMP.EMP_NO)
                        .and(DEPT_EMP.FROM_DATE.in(
                                dsl.select(DEPT_EMP.FROM_DATE.max())
                                        .from(DEPT_EMP)
                                        .where(DEPT_EMP.EMP_NO.eq(EMPLOYEES.EMP_NO)))))

                .join(DEPARTMENTS)
                .on(DEPT_EMP.DEPT_NO.eq(DEPARTMENTS.DEPT_NO))

                .join(TITLES)
                .on(EMPLOYEES.EMP_NO
                        .eq(TITLES.EMP_NO)
                        .and(TITLES.FROM_DATE.in(
                                dsl.select(TITLES.FROM_DATE.max())
                                        .from(TITLES)
                                        .where(TITLES.EMP_NO.eq(EMPLOYEES.EMP_NO)))));
    }
}
