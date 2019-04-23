<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Students CRUD</title>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<table class="main">
    <tr>
        <td class="main-td">
            <c:url value="/create" var="createUrl"/>
            <form action="${createUrl}" method="post">
                <table class="table_1" id="students">
                    <caption>Work with student</caption>
                    <c:if test="${student.id ne null}">
                        <tr>
                            <td>Student ID</td>
                            <td>
                                <label>
                                    <input type="number" name="id" value="${student.id}" readonly="readonly">
                                </label>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>Name</td>
                        <td>
                            <label>
                                <input type="text" name="name" value="${student.name}" required>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>Age</td>
                        <td>
                            <label>
                                <input type="number" min="16" name="age" value="${student.age}" required>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>Course</td>
                        <td>
                            <label>
                                <input type="text" name="course" value="${student.course}" required>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td>Speciality</td>
                        <td>
                            <label>
                                <input type="text" name="speciality" value="${student.speciality}" required>
                            </label>
                        </td>
                    </tr>
                    <c:if test="${student.id ne null}">
                        <tr>
                            <td colspan="2"><input type="submit" class="button" value="Update Student"></td>
                        </tr>
                    </c:if>
                    <c:if test="${student.id eq null}">
                        <tr>
                            <td colspan="2"><input type="submit" class="button" value="Add Student"></td>
                        </tr>
                    </c:if>
                </table>
            </form>
        </td>
        <td class="main-td">
            <table class="table_2">
                <caption>List of Students</caption>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Course</th>
                    <th>Speciality</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                <c:forEach items="${studentsList}" var="student">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.age}</td>
                        <td>${student.course}</td>
                        <td>${student.speciality}</td>
                        <td>
                            <form action="<c:url value="/update"/>" method="post">
                                <input type="hidden" name="id" value="${student.id}">
                                <input class="button" type="submit" value="Update">
                            </form>
                        </td>
                        <td>
                            <form action="<c:url value="/delete"/>" method="post">
                                <input type="hidden" name="id" value="${student.id}">
                                <input class="button-delete" type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
</body>
</html>
