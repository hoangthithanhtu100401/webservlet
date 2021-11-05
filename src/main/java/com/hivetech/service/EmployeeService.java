package com.hivetech.service;

import com.hivetech.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> employees();
    Employee getEmployee (int employeeId);

    List<Employee> getEmployeeById(int employeeId);

    boolean add(Employee employee);

    boolean update(Employee employee);

    boolean delete(int employeeId);
}

