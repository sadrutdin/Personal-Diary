<%@ page import="com.zaynukov.dev.angeldiary.exception.DiaryIsExistException" %>
<%@ page import="java.sql.SQLException" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zaynu
  Date: 03.02.2019
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="img/fav.png">
    <title>Войти в дневник</title>

    <link rel="stylesheet" href="css/diary.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">


</head>

<body>


<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">Войти в дневник</h5>
                    <form:form method="post" action="/sign-in" class="form-signin">
                        <div class="form-label-group">
                            <input type="text" id="login" name="login" class="form-control" placeholder="Логин" required
                                   autofocus>
                            <label for="login">Логин</label>
                        </div>

                        <div class="form-label-group">
                            <input type="password" id="password" name="password" class="form-control"
                                   placeholder="Пароль"
                                   required>
                            <label for="password">Пароль</label>
                        </div>

                        <%
                            Object obj = request.getAttribute("e");
                            if (obj instanceof DiaryIsExistException) {
                        %>
                            <p class="text-center text-danger">Такой дневник уже существует</p>
                        <%
                            } else
                                if (obj instanceof SQLException) {
                        %>
                            <p class="text-center text-danger">Ошибка при работе с базой данных. Обратитесь администратору.</p>
                        <%  } else if (request.getParameter("error")!=null){%>
                            <p class="text-center text-danger">Неправильный логин/пароль</p>

                        <%}%>

                        <button id="button1" class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">
                            Войти
                        </button>
                        <hr class="my-4">
                        <button onclick="location.href='sign-up'" id="button2"
                                class="btn btn-lg btn-primary btn-block text-uppercase" type="button">Создать дневник
                        </button>

                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>


</html>

