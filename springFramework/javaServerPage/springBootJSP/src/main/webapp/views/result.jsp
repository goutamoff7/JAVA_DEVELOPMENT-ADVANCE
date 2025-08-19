<%@page language="java"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    </body>
        <h2>Result : <%=session.getAttribute("result")%></h2>   <%-- not use this when using model --%>
         <%--       or use   --%>
        <h2>Result : ${result}</h2>
    </body>
</html>