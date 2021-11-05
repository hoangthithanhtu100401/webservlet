<%--
  Created by IntelliJ IDEA.
  User: vuduy
  Date: 10/21/2021
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Employee</title>
    <script language="javascript" src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
</head>
<body>
<h3>Delete an employee by ID </h3>
<form name="formDelete" action="/employee/delete" method="Delete">
    <table style="border: black 1px solid">
        <tr>
            <th>ID</th>
            <th>Remove</th>
        </tr>
        <tr>
            <td>
                <input type="text" name="employeeId" id="id">
            </td>
            <td>
                <input type="submit" value="Remove" id="delete">
            </td>
        </tr>
    </table>
</form>
</body>
<script language="javascript">
    $(document).ready(function () {
        $(document).on('click', '#delete', function (e) {
            e.preventDefault();
            var employeeId = document.getElementById("id").value;
            if (employeeId == "") {
                alert("ID trống");
                document.getElementById("id").focus();
                return false;
            } else if (Number.isNaN(Number(employeeId))) {
                alert("Chỉ nhập số");
                document.getElementById("id").focus();
                return false;
            }
            else if (confirm('Bạn có chắc chắn muốn xóa id = ' + employeeId) === true) {
                $.ajax({
                    url: "/employee/delete",
                    type: "Delete",
                    data: {
                        employeeId: employeeId
                    },
                    success: function (result) {
                        console.log('success');
                        $("#list").load(" .list");
                        alert('Đã xóa thành công Id = ' + employeeId);
                        window.location.href = '/employee/list';
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
