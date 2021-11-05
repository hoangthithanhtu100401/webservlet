<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vuduy
  Date: 10/21/2021
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Employee By Id</title>
</head>
<body>
<h3>Select an employee by ID </h3>
<form action="/employee/GetById" method="get">
    <table style="border: black 1px solid">
        <tr>
            <th>ID</th>
            <th>Select</th>
        </tr>
        <tr>
            <td>
                <input type="text" name="employeeId">
            </td>
            <td>
                <input type="submit" value="Select">
            </td>
        </tr>
    </table>
</form>
<table style="border: black 2px solid">
    <tr>
        <th>Id</th>
        <th>Age</th>
        <th>Name</th>
        <th>City</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="temp" items="${listEmployee}">
        <td>
            <tr>
                <td>${temp.employeeId}</td>
                <td>${temp.employeeAge}</td>
                <td>${temp.employeeName}</td>
                <td>${temp.employeeCity}</td>
                <td><a href="/employee/edit?Id=${temp.employeeId}"><button type="submit" name="Edit">Edit</button></a></td>
                <td><a href="/employee/delete?Id=${temp.employeeId}"><button type="submit" name="Edit">Delete</button></a></td>
            </tr>
        </td>
    </c:forEach>
</table>
</body>
</html>
