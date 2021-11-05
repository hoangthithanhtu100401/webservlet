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

@WebServlet("/employee/delete")
public class DeleteEmployeeServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DeleteEmployeeServlet.class.getName());

    private EmployeeService employeeService;

    public void init() {
        employeeService = new EmployeeServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/deleteEmployee.jsp");
        dispatcher.forward(request,response);
        doDelete(request, response);
        LOGGER.info(String.format("Method: %s | action: %s ", request.getMethod(), request.getRequestURI()));
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        boolean isDeleted = employeeService.delete(employeeId);

        response.sendRedirect("/employee/list");

        LOGGER.info("Action: " + request.getServletPath() + " | isDelete: " + isDeleted + " | employeeId: " + employeeId);
    }

}
