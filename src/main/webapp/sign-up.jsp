<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--suppress ALL --%>
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

    <title>Новый дневник</title>

    <link rel="stylesheet" href="css/diary.css">

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">Создать новый дневник</h5>
                    <form:form action="/sign-up" method="post" class="form-signin">
                        <div class="form-label-group">
                            <input type="text" id="login" name="login" class="form-control" placeholder="Логин" required
                                   autofocus>
                            <label for="login">Логин</label>
                        </div>

                        <div class="form-label-group">
                            <input type="password" id="password" name="password" class="form-control" placeholder="Пароль"
                                   required>
                            <label for="password">Пароль</label>
                        </div>

                        <button id="button1" class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">
                            Создать
                        </button>

                        <hr class="my-4">
                        <button onclick="location.href='sign-in'" id="button2"
                                class="btn btn-lg btn-primary btn-block text-uppercase" type="button">Назад
                        </button>

                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>


</html>