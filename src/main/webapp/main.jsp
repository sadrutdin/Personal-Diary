<%--suppress JspAbsolutePathInspection --%>
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
    <title>Список записей</title>

    <link rel="stylesheet" href="css/diary.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <%--<link rel="stylesheet" href="css/font-awesome.min.css">--%>


    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/moment.min.js"></script>
    <script type="text/javascript" src="js/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/daterangepicker.css"/>

</head>

<body>
<div class="container">
    <div class="row">
        <div class="mx-auto col">
            <div class="card card-signin my-5">


                <div class="card-body">


                    <div class="d-flex justify-content-between my-2">

                        <form method="get" action="/logout">
                            <button class="btn btn-secondary" type="submit">Выйти из дневника</button>
                        </form>


                        <div class="d-flex col" style="margin-left:2px">
                            <input id="searchInput" name="search" type="text" placeholder="Поиск по записям"
                                   style="margin-right:6px"
                                   class=" w-100 form-control my-auto">

                            <input type="text" id="daterange" name="daterange"
                                   class="w-50 my-auto form-control text-center" value="01/01/2018 - 15/03/2018">
                            <button style="margin-left:6px" onclick="searchNote();"
                                    class="btn btn-primary my-auto">Найти
                            </button>
                        </div>


                        <form method="get" action="/create-note">
                            <button onclick="location.href='/create-note'" class="btn btn-primary">Добавить запись
                            </button>
                        </form>


                    </div>


                    <div class="my-1 justify-content-between">

                        <c:if test="${errorMsg ne null}">
                            <p class="text-danger text-center"><c:out value="errorMsg"/></p>
                        </c:if>

                    </div>


                    <%--<jsp:useBean id="items" scope="page" type="java.util.List"/>--%>
                    <c:forEach var="it" items="${items}">
                        <%--<jsp:useBean id="it" type="com.zaynukov.dev.angeldiary.model.ItemOfNoteListDTO"/>--%>

                        <a href="/view?id=${it.id}" class="list-group-item list-group-item-action  align-items-start">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">${it.title}&nbsp;&nbsp;</h5>
                                <small>${it.createDateTimeAsStr()}</small>
                            </div>
                            <c:set var="lastChange" value="${it.lastChangeDateTimeAsStr()}"/>
                            <small class="text-muted"><c:if
                                    test="${lastChange ne null && !lastChange.equals('')}">Посл. редакт.: <c:out
                                    value="${lastChange}"/></c:if>
                            </small>

                        </a>

                    </c:forEach>


                </div>
            </div>


        </div>
    </div>
</div>

<script>
    function searchNote() {
        var search = document.getElementById('searchInput').value;
        var dateRange = document.getElementById('daterange').value;
        location.href = location.href + '?search=' + encodeURI(search) + '&daterange=' + encodeURI(dateRange);
    }

    $(function () {

        var now = new Date();
        var afterMonth = new Date(now.getFullYear(), now.getMonth() + 1, now.getDate());
        document.getElementById("daterange").value =
            now.getDate() + "/" + now.getMonth() + "/" + now.getFullYear()
            + " - "
            + afterMonth.getDate() + "/" + afterMonth.getMonth() + "/" + afterMonth.getFullYear();


        $('input[name="daterange"]').daterangepicker({

            locale: {
                format: "DD/MM/YYYY",
                separator: " - ",
                applyLabel: "Применить",
                cancelLabel: "Скрыть",
                fromLabel: "From",
                toLabel: "To",
                customRangeLabel: "Custom",
                weekLabel: "W",
                daysOfWeek: [
                    "Пн",
                    "Вт",
                    "Ср",
                    "Чт",
                    "Пт",
                    "Сб",
                    "Вс"
                ],
                monthNames: [
                    "Январь",
                    "Февраль",
                    "Март",
                    "Апрель",
                    "Май",
                    "Июнь",
                    "Июль",
                    "Август",
                    "Сентябрь",
                    "Октябрь",
                    "Ноябрь",
                    "Декабрь"
                ],
                "firstDay": 1
            },

            opens: 'left'
        }, function (start, end, label) {
            console.log("A new date selection was made: " + start.format('DD-MM-YYYY') + ' to ' + end.format('DD-MM-YYYY'));
        });


    });


</script>

</body>


</html>
