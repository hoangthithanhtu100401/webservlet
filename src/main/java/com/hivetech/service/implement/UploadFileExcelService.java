package com.hivetech.service.implement;

import com.hivetech.model.Employee;
import com.hivetech.service.EmployeeService;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

public class UploadFileExcelService {
    private EmployeeService employeeService = new EmployeeServiceImpl();

    public void uploadEmployee(Part part) {
        try {
            List<Employee> employees = UploadFileExeclHelper.excelEmployees(part.getInputStream());
            for (int i = 0; i < employees.size(); i++) {
                employeeService.add(employees.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}

