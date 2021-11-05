<%--
  Created by IntelliJ IDEA.
  User: vuduy
  Date: 10/21/2021
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Employee</title>
</head>
<body>
<h3>Add new employee</h3>

<form action="/employee/add" method="post">
    <table style="border: black 1px solid">
        <tr>
            <th>Age</th>
            <th>Name</th>
            <th>City</th>
            <th>Add</th>
        </tr>
        <tr>
            <td><input type="text" name="employeeAge"></td>
            <td><input type="text" name="employeeName"></td>
            <td><input type="text" name="employeeCity"></td>
            <td><input type="submit" value="Add"></td>
        </tr>
    </table>
</form>
</body>
</html>
