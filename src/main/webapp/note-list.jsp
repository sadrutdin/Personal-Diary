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
    <title>Войти</title>

    <link rel="stylesheet" href="css/diary.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">


    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>

</head>

<body>
<div class="container">
    <div class="row">
        <div class="mx-auto">
            <div class="card card-signin my-5">


                <div class="card-body">


                    <div class="d-flex w-100 justify-content-between">

                        <button class="btn btn-secondary my-2">Выйти из дневника</button>

                        <div class="col d-flex w-100 justify-content-end">
                            <div class="d-flex h-75 w-75 justify-content-end">
                                Фильтр по дате:
                            </div>

                            <div class="h-75 d-flex w-75 justify-content-end">
                                <input type="text" name="daterange" class="form-control"
                                       value="01/01/2018 - 01/15/2018">

                            </div>
                        </div>


                        <button class="btn btn-primary my-2">Создать запись</button>

                    </div>


                    <a href="#" class="list-group-item list-group-item-action  align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">List group item heading11111111111111111111111111111222222222222222</h5>
                            <small>3 days ago</small>
                        </div>

                        <small>Donec id elit non mi porta.</small>
                    </a>
                    <a href="#" class="list-group-item list-group-item-action  align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">List group item heading</h5>
                            <small class="text-muted">3 days ago</small>
                        </div>

                        <small class="text-muted">Donec id elit non mi porta.</small>
                    </a>
                    <a href="#" class="list-group-item list-group-item-action  align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">List group item heading</h5>
                            <small class="text-muted">3 days ago</small>
                        </div>

                        <small class="text-muted">Donec id elit non mi porta.</small>
                    </a>
                </div>


            </div>
        </div>
    </div>
</div>
</div>

<script>
    $(function () {
        $('input[name="daterange"]').daterangepicker({
            opens: 'left'
        }, function (start, end, label) {
            console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
        });
    });
</script>

</body>


</html>
