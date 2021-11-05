<%--
  Created by IntelliJ IDEA.
  User: vuduy
  Date: 10/13/2021
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Management</title>
    <script language="javascript" src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
</head>
<body>
<h2><a href="/index.jsp">Home page</a></h2>
<h2><a href="/employee/list">List All</a></h2>
<h2><a href="/employee/add">Add Employee</a></h2>
<h2><a href="/employee/delete">Delete an Employee</a></h2>

<h3>Select an employee by ID </h3>
<form action="/employee/GetById" method="get">
    <table style="border: black 1px solid">
        <tr>
            <th>Id</th>
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

<h3>List all employee</h3>
<div id="list">
    <table class="list" style="border: black 2px solid">
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
                    <td><a href="<c:url value="/employee/edit?Id=${temp.employeeId}"/>">
                        <button type="submit" name="Edit">Edit</button>
                    </a></td>
                    <td>
                        <button type="submit" name="Edit" id="delete" data-id="${temp.employeeId}">Delete</button>
                    </td>
                </tr>
            </td>
        </c:forEach>
    </table>
</div>
</body>
<script language="javascript">
    $(document).ready(function () {
        $(document).on('click', '#delete', function (e) {
            e.preventDefault();
            var employeeId = $(this).data("id");
            if (confirm('Bạn có chắc chắn muốn xóa') === true) {
                $.ajax({
                    url: "/employee/delete",
                    type: "Delete",
                    data: {
                        employeeId: employeeId
                    },
                    success: function (result) {
                        console.log('success')
                        $("#list").load(" .list");
                        alert('Đã xóa thành công Id = '+ employeeId);
                    },
                    error: function (request, status, error) {
                        alert("some error");
                    }
                });
            }
        })
    })
</script>
</html>
