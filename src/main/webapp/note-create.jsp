<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: zaynu
  Date: 01.02.2019
  Time: 19:51
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
    <title>Список записей</title>


    <link rel="stylesheet" href="css/diary.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">


    <link rel="stylesheet" type="text/css" href="css/daterangepicker.css"/>

    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/moment.min.js"></script>

    <link rel="stylesheet" href="css/font-awesome.min.css">


</head>

<body>
<div class="container">
    <div class="row">
        <div class="mx-auto col-9">
            <div class="card card-signin my-5">


                <div class="card-body">

                    <form:form method="post" action="/create-note">

                        <div class="d-flex justify-content-between my-3">

                            <div>
                                <button onclick="location.href='/main'" class="btn btn-secondary my-auto">Назад
                                </button>
                            </div>

                            <div class="col-8">
                                <p class="lead my-auto">
                                    <input name="title" required type="text" class="text-center form-control my-auto"
                                           maxlength="40"  autofocus placeholder="Заголовок">
                                </p>
                            </div>

                            <div>
                                <button class="btn btn-primary my-auto">Сохранить</button>
                            </div>


                        </div>

                        <div class="text-left">


                            <p class="d-flex">
              <textarea name="text" style="height: 450px" required class="d-flex form-control textarea-control" rows="3"
                        placeholder="Введите текст дневника.
Чтобы вставить emoji нажмите Win + точка"></textarea>
                            </p>


                        </div>

                    </form:form>

                </div>


            </div>
        </div>
    </div>
</div>


<script>
    $(function () {


        var textarea = document.querySelector('textarea');

        textarea.addEventListener('keydown', autosize);

        function autosize() {
            var el = this;


            setTimeout(function () {
                el.style.cssText = 'height:auto; padding:0';
                el.style.cssText = 'height:' + (el.scrollHeight < 450 ? 450 : el.scrollHeight + 48) + 'px';
            }, 0);
        }


    });

</script>

</body>


</html>
