<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="IdeaProjects/java-adlister/src/main/webapp/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="IdeaProjects/java-adlister/src/main/webapp/partials/navbar.jsp" />

    <div class="container">
        <p>Wassup, ${user}!</p>
        <h1>Viewing your profile.</h1>
    </div>

</body>
</html>
