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
    <title><c:out value="${dto.title}"/></title>


    <link rel="stylesheet" href="css/diary.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">


    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>


</head>

<body>
<div class="container">


    <!-- Modal -->
    <div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">

                    <div class='col-1'>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <div class='col'>

                        <h4 class="modal-title">Подтверждение</h4>
                    </div>


                </div>
                <div class="modal-body">
                    <p>Вы действительно хотите удалить эту запись?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn" data-dismiss="modal">Нет</button>

                    <form:form action="/delete-note" method="post">
                        <input name="id" class="d-none" value="${dto.id}">
                        <button type="submit" class="ml-2 btn btn-danger" <%--data-dismiss="modal"--%>>Удалить</button>
                    </form:form>

                </div>
            </div>

        </div>
    </div>


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
                                    <input name="title" required type="text"
                                           class="text-center border-0 bg-white form-control my-auto"
                                           value='<c:out value="${dto.title}"/>' id="title1" maxlength="45" autofocus
                                           placeholder="Заголовок" disabled>
                                </p>
                            </div>


                            <div>
                                <div class="col">
                                    <div class="row">
                                        <button data-toggle="modal" data-target="#myModal" type="button"
                                                class="btn btn-danger my-auto" style="margin-top:20px;">
                                            Удалить запись
                                        </button>
                                        <button id="editButton" type="button" class="btn ml-1 btn-primary my-auto"
                                                onclick="toEditableContent();">
                                            Изменить
                                        </button>
                                        <button id="saveButton" type="submit"
                                                class="btn ml-1 btn-primary my-auto d-none">
                                            Сохранить
                                        </button>
                                    </div>


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


    function autosize() {
        var k = this;

        setTimeout(function () {
            k.style.cssText = 'height:auto; padding:0';
            k.style.cssText = 'height:' + (k.scrollHeight < 450 ? 450 : k.scrollHeight + 48) + 'px';
        }, 0);
    }

    $(function () {
        var textarea = document.querySelector('textarea');

        textarea.addEventListener('keydown', autosize);

    });

    {
        var elt = document.getElementById("text1");
        elt.onkeydown = autosize;
        elt.onkeydown();
        elt.onkeydown = null;
    }



</script>

</body>


</html>
