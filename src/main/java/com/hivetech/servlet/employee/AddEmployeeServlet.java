package com.hivetech.servlet.employee;

import com.hivetech.model.Employee;
import com.hivetech.service.EmployeeService;
import com.hivetech.service.implement.EmployeeServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/employee/add")
public class AddEmployeeServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddEmployeeServlet.class.getName());

    private EmployeeService employeeService;

    public void init() {
        employeeService = new EmployeeServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/addEmployee.jsp");
        dispatcher.forward(request,response);
        LOGGER.info(String.format("Method: %s | action: %s ", request.getMethod(), request.getRequestURI()));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int employeeAge = Integer.parseInt(request.getParameter("employeeAge"));
        String employeeName = request.getParameter("employeeName");
        String employeeCity = request.getParameter("employeeCity");

        Employee newEmployee = new Employee(employeeAge, employeeName, employeeCity);
        boolean added = employeeService.add(newEmployee);
        response.sendRedirect("/employee/list");
        LOGGER.info(String.format("Method: %s | action: %s | Added: %b |",
                request.getMethod(), request.getRequestURI(), added));
    }

}
