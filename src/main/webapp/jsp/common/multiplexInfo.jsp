<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html lang="${param.lang}">
<jsp:include page="/WEB-INF/parts/head_tag.jsp"/>

<body class="home">
<div id="wrapper">
    <header id="header">
        <jsp:include page="/WEB-INF/parts/header.jsp"/>
<%--        <section class="slider_home">--%>
            <div class="panel panel-default">
                <div class="panel-body">
                    <h1><strong><fmt:message key="cinema.hall.about"/></strong></h1>
                    <br>
                    <h1><strong><fmt:message key="cinema.hall.description"/></strong></h1>
                    <br>
                    <h3><fmt:message key="cinema.hall.address"/></h3>
                    <br>
                    <h3><fmt:message key="cinema.hall.email"/></h3>
                    <br>
                    <h3><fmt:message key="cinema.hall.number"/></h3>
                </div>
            </div>
<%--            <ul id="main_slider">--%>
<%--                <li>--%>
<%--                    <img src="${pageContext.request.contextPath}img/multiplex_main.jpg" class="desktop-slide"--%>
<%--                         width="1680"--%>
<%--                         height="1050">--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--        </section>--%>
    </header>
</div>
<%--<jsp:include page="/WEB-INF/parts/footer.jsp"/>--%>
</body>
</html>