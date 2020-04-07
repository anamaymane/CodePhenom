<%@ page import="queryTesting.UserInsert" %>
<%@ page import="javax.transaction.SystemException" %>
<%@ page import="javax.transaction.NotSupportedException" %><%--
  Created by IntelliJ IDEA.
  User: aym
  Date: 4/5/20
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    try {
        UserInsert.setUpEntityManagerFactoryAndPopulateTheDatastore();
    } catch (SystemException e) {
        e.printStackTrace();
    } catch (NotSupportedException e) {
        System.out.println("Message :\n*******\n " + e.getMessage());
    }
%>
</body>
</html>
