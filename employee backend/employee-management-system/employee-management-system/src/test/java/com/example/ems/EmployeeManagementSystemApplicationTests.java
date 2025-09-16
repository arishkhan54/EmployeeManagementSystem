package com.example.ems;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ems.service.EmployeeService;

@SpringBootTest
class EmployeeManagementSystemApplicationTests {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void contextLoads() {
        assertThat(employeeService).isNotNull();
    }
}
