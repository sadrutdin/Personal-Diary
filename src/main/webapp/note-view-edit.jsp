<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

                    <form:form method="post" action="/edit-note">

                        <div class="d-flex justify-content-between my-3">

                            <div>
                                <button type="button" onclick="location.href='/main'" class="btn btn-secondary my-auto">
                                    Назад
                                </button>
                            </div>

                            <div class="col-7">
                                <p class="lead my-auto">
                                    <input name="title" required type="text" class="text-center border-0 bg-white form-control my-auto"
                                           value='<c:out value="${dto.title}"/>' id="title1" maxlength="45" autofocus
                                           placeholder="Заголовок" disabled>
                                </p>
                            </div>

                           <%-- <div>
                                <div class="col">
                                    <div class="row">
                                        <button id="editButton" type="button" class="btn btn-primary my-auto" onclick="toEditableContent();">
                                            Редактировать
                                        </button>
                                    </div>
                                    <div class="row">
                                        <button id="saveButton" type="submit" class="btn btn-primary my-auto d-none">
                                            Сохранить
                                        </button>
                                    </div>

                                    <div class="row mt-1">
                                        <button type="button" class="btn btn-danger my-auto" style="margin-top:20px;">Удалить запись</button>
                                    </div>
                                </div>
                            </div>

--%>
                            <div>
                                <div class="col">
                                    <div class="row">
                                        <button type="button" class="btn btn-danger my-auto" style="margin-top:20px;">
                                            Удалить запись
                                        </button>
                                        <button id="editButton" type="button" class="btn ml-1 btn-primary my-auto" onclick="toEditableContent();">
                                         Изменить
                                    </button>
                                        <button id="saveButton" type="submit" class="btn ml-1 btn-primary my-auto d-none">
                                            Сохранить
                                        </button></div>



                                </div>
                            </div>

                        </div>

                        <div class="text-left">


                            <p class="d-flex">
              <textarea id="text1" name="text" style="height: 450px" required disabled
                        class="d-flex bg-white border-0 form-control textarea-control" rows="3"
                        placeholder="Введите текст дневника.
Чтобы вставить emoji нажмите Win + точка"><c:out value="${dto.noteText}"/></textarea>
                            </p>


                        </div>
                        <label>
                            <input value="${dto.id}" class="d-none" name="id">
                        </label>
                    </form:form>

                </div>


            </div>
        </div>
    </div>
</div>


<script>

    function toEditableContent() {
        var d_none = "d-none";
        document.getElementById("editButton").classList.add(d_none);
        document.getElementById("saveButton").classList.remove(d_none);

        var disabled = "disabled";

        var title = document.getElementById("title1");
        var text = document.getElementById("text1");

        title.removeAttribute(disabled);
        text.removeAttribute(disabled);

        var border0 = "border-0";

        title.classList.remove(border0);
        text.classList.remove(border0);
    }


    $(function () {
        var textarea = document.querySelector('textarea');

        textarea.addEventListener('keydown', autosize);

        function autosize() {
            var k = this;

            setTimeout(function () {
                k.style.cssText = 'height:auto; padding:0';
                k.style.cssText = 'height:' + (k.scrollHeight < 450 ? 450 : k.scrollHeight + 48) + 'px';
            }, 0);
        }
    });

</script>

</body>


</html>
