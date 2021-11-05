package com.hivetech.servlet.employee;

import com.hivetech.model.Employee;
import com.hivetech.service.EmployeeService;
import com.hivetech.service.implement.EmployeeServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/employee/edit")
@MultipartConfig
public class EditEmployeeServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(EditEmployeeServlet.class.getName());

    private EmployeeService employeeService;

    public void init() {
        employeeService = new EmployeeServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int employeeId = Integer.parseInt(request.getParameter("Id"));
        Employee employee = employeeService.getEmployee(employeeId);
        request.setAttribute("Employee", employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/editEmployee.jsp");
        dispatcher.forward(request, response);
        LOGGER.info(String.format("Method: %s | action: %s ", request.getMethod(), request.getRequestURI()));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        int employeeAge = Integer.parseInt(request.getParameter("employeeAge"));
        String employeeName = request.getParameter("employeeName");
        String employeeCity = request.getParameter("employeeCity");
        Employee editEmployee = new Employee(employeeId, employeeAge, employeeName, employeeCity);
        boolean updated = employeeService.update(editEmployee);
        response.sendRedirect("/employee/list");
        LOGGER.info(String.format(("Method: %s | action: %s  | Updated: %b | Employee: %d "),
                request.getMethod(), request.getRequestURI(), updated, employeeId));

    }
}
