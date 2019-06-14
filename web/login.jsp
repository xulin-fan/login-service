<%--
  Created by IntelliJ IDEA.
  User: barryfan
  Date: 6/10/19
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/login">
    用户名: <input name="username"/><br/>
    密码: <input name="password"/><br/>
    记住我: <input type="checkbox" name="remeber"/><br/>
    <button type="submit">提交</button>
</form>

<%
    if(request.getAttribute("loginFailed") != null){
        %><%=request.getAttribute("loginFailed")%><%
    }
%>

<%--<%=loginFailed%>--%>
<%--<jsp:attribute name="loginFailed"></jsp:attribute>--%>
</body>
</html>
