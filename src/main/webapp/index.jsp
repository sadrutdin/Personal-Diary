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
    <title>emoji-picker Demo</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

    <link href="css/emoji.css" rel="stylesheet">
</head>

<body class="text-center">
<h1>Дневник</h1>
<p>
    <!--<a class="btn btn-secondary" href="https://github.com/one-signal/emoji-picker" target="_blank">
      View on GitHub <i class="fa fa-lg fa-github"></i>
    </a>-->
</p>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-10">
            <div class="text-left">
                <p class="lead emoji-picker-container">
              <textarea style="height: 500px;" class="form-control textarea-control" rows="3"
                        placeholder="Textarea with emoji Unicode input"
                        data-emojiable="true" data-emoji-input="unicode">

			  </textarea>
                </p>
            </div>
        </div>
    </div>
</div>

<script src="js/jquery-1.11.3.min.js"></script>

<!-- Begin emoji-picker JavaScript -->
<script src="js/config.js"></script>
<script src="js/util.js"></script>
<script src="js/jquery.emojiarea.js"></script>
<script src="js/emoji-picker.js"></script>


<script>
    $(function () {
        window.emojiPicker = new EmojiPicker({
            emojiable_selector: '[data-emojiable=true]',
            assetsPath: '../lib/img/',
            popupButtonClasses: 'fa fa-smile-o'
        });
        window.emojiPicker.discover();
    });
</script>
<script>
    /*(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
    m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-49610253-3', 'auto');
    ga('send', 'pageview');*/
</script>
</body>
</html>

