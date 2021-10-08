<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript">
      //异步请求
    </script>
  </head>
  <body>
    <!-- 发送请求的时候 携带两个信息 类名AtmController 方法名login  -->
    <!--    AtmController.do?method=login    -->
    <!-- 发送请求的时候 遵循HTTP协议 . ? : 多个 -->
    <a href="login.do?name=zzt&pass=123">模拟ATM系统功能点1(登录)</a><br>
    <a href="query.do?name=zzt&pass=123">模拟ATM系统功能点2(查询)</a><br>
    <a href="kindQuery.do?name=zzt&pass=123">模拟Shopping系统功能点1(查询种类)</a><br>
    <a href="kindInsert.do?name=zzt&pass=123">模拟Shopping系统功能点2(种类添加)</a><br>

    <%--<hr>--%>
    <%--${requestScope.result}--%>
    <%--<form action="login.do" method="post">--%>
      <%--account:<input type="text" name="name" value=""><br>--%>
      <%--password:<input type="password" name="pass" value=""><br>--%>
      <%--<input type="submit" value="login"><br>--%>
    <%--</form>--%>

  </body>
</html>
