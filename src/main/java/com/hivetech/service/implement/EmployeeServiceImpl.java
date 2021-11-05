package com.hivetech.service.implement;

import com.hivetech.jdbc.Database;
import com.hivetech.jdbc.JDBC_Helper;
import com.hivetech.model.Employee;
import com.hivetech.service.EmployeeService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class.getName());
    private static Connection connection = Database.getConnection();

    private static String SELECT_EMPLOYEES = "SELECT * FROM employees LIMIT 10;";
    private static String DELETE_EMPLOYEE_BY_TD = "DELETE FROM employees WHERE Id = ?;";
    private static String GET_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE Id = ?;";
    private static String UPDATE_EMPLOYEE = "UPDATE employees SET Age = ?, Name = ?, City = ? WHERE Id = ?;";
    private static String INSERT_EMPLOYEE = "INSERT INTO employees(Age, Name, City) VALUES (?, ?, ?);";

    @Override
    public List<Employee> employees() {
        List<Employee> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(SELECT_EMPLOYEES);
            rs = statement.executeQuery();
            while (rs.next()) {
                Employee tempEmployee = buildEmployess(rs);
                list.add(tempEmployee);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            JDBC_Helper.closeResultSet(rs);
            JDBC_Helper.closeStatement(statement);
        }
        return list;
    }

    private Employee buildEmployess(ResultSet rs) throws SQLException {
        int id = rs.getInt("Id");
        int age = rs.getInt("Age");
        String name = rs.getString("Name");
        String city = rs.getString("City");
        return new Employee(id, age, name, city);
    }

    @Override
    public Employee getEmployee(int employeeId) {
        Employee employee = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(GET_EMPLOYEE_BY_ID);
            statement.setInt(1, employeeId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee = buildEmployess(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            JDBC_Helper.closeResultSet(resultSet);
            JDBC_Helper.closeStatement(statement);
        }
        return employee;
    }


    @Override
    public List<Employee> getEmployeeById(int employeeId) {
        List<Employee> list = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(GET_EMPLOYEE_BY_ID);
            statement.setInt(1, employeeId);
            rs = statement.executeQuery();
            if (rs.next()) {
                list.add(new Employee(
                        rs.getInt("Id"),
                        rs.getInt("Age"),
                        rs.getString("Name"),
                        rs.getString("City")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            JDBC_Helper.closeResultSet(rs);
            JDBC_Helper.closeStatement(statement);
        }
        return list;
    }

    @Override
    public boolean add(Employee employee) {
        boolean isInserted = false;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_EMPLOYEE);
            statement.setInt(1, employee.getEmployeeAge());
            statement.setString(2, employee.getEmployeeName());
            statement.setString(3, employee.getEmployeeCity());
            isInserted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return false;
        } finally {
            JDBC_Helper.closeStatement(statement);
        }
        return isInserted;
    }

    @Override
    public boolean update(Employee employee) {
        boolean isUpdated = false;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_EMPLOYEE);
            statement.setInt(1, employee.getEmployeeAge());
            statement.setString(2, employee.getEmployeeName());
            statement.setString(3, employee.getEmployeeCity());
            statement.setInt(4, employee.getEmployeeId());
            isUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return false;
        } finally {
            JDBC_Helper.closeStatement(statement);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(int employeeId) {
        boolean isDeleted = false;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_EMPLOYEE_BY_TD);
            statement.setInt(1, employeeId);
            isDeleted = statement.executeUpdate() > 1;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            return false;
        } finally {
            JDBC_Helper.closeStatement(statement);
        }
        return isDeleted;
    }
}

