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
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/employee/list")
public class ListEmployeeServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ListEmployeeServlet.class.getName());

    private EmployeeService employeeService;

    public void init() {
        employeeService = new EmployeeServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<Employee> list = employeeService.employees();
        request.setAttribute("listEmployee", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listEmployee.jsp");
        dispatcher.forward(request, response);

        LOGGER.info(String.format("Method: %s; Servlet Path: %s; Num of Employee: %d",
                request.getMethod(), request.getServletPath(), list.size()));
    }
}
