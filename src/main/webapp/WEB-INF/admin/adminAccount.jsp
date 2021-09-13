<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html lang="${param.lang}">
<jsp:include page="/WEB-INF/parts/head_tag.jsp"/>

<body class="home blog">

<div id="wrapper">
    <header id="header">
        <jsp:include page="/WEB-INF/parts/header.jsp"/>
    </header>
</div>

<section class="slider_home">
    <ul id="main_slider">
        <li>
            <img src="${pageContext.request.contextPath}/img/multiplex_main.jpg" class="desktop-slide" width="1680"
                 height="1050" alt="main picture">
        </li>
    </ul>
</section>

<jsp:include page="/WEB-INF/parts/footer.jsp"/>
</body>
</html>