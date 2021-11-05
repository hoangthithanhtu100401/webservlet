<%--
  Created by IntelliJ IDEA.
  User: vuduy
  Date: 10/21/2021
  Time: 8:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Employee</title>
</head>
<body>
<h3>Edit an employee by ID</h3>
<form action="/employee/edit" method="post">
    <table style="border: black 1px solid">
        <tr>
            <th>ID</th>
            <th>Age</th>
            <th>Name</th>
            <th>City</th>
            <th>Submit</th>
        </tr>
        <tr>
            <td><input type="text" name="employeeId" value="${Employee.employeeId}"></td>
            <td><input type="text" name="employeeAge" value="${Employee.employeeAge}"></td>
            <td><input type="text" name="employeeName" value="${Employee.employeeName}"></td>
            <td><input type="text" name="employeeCity" value="${Employee.employeeCity}"></td>
            <td><input type="submit" value="Submit"></td>
        </tr>
    </table>
</form>

</body>
</html>
