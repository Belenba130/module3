<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Danh sách tài khoản</title>
</head>
<body>
    <h1>Danh sách tài khoản</h1>
    <p><a href="/users/create">Thêm mới</a></p>
    <table border="1" cellpadding="5" cellspacing="0" width="100%">
        <tr>
            <th>Avatar</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Birthday</th>
            <th>Created</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <c:forEach var="u" items="${data}">
            <tr>
                <td>
                    <img src="${u.avatar}" alt="" width="100">
                </td>
                <td>${u.email}</td>
                <td>${u.phone}</td>
                <td>${u.address}</td>
                <td><fmt:formatDate value="${u.birthday}" pattern="dd-MM-yyyy" /></td>
                <td><fmt:formatDate value="${u.created}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
                <td>${u.status}</td>
                <td>
                    <a href="/users/edit/${u.id}">Edit</a> |
                    <a href="/users/delete/${u.id}" onclick="return confirm('Bạn có muốn xóa không?')">Remove</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
