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
    <div class="center">
        <div class="register">
            <form method="POST" action="${pageContext.request.contextPath}/cinema/enterLogin">
                <div class="container">

                    <h1><fmt:message key="login.sign.in"/></h1>
                    <p><fmt:message key="login.fill.form"/></p>
                    <hr>
                    <c:if test="${param.successReg == true}">
                        <p class="alert-success"><fmt:message key="login.registration.successful.reconnect"/></p>
                    </c:if>
                    <c:if test="${param.successReg == false}">
                        <p class="errorsM"><fmt:message key="login.registration.exception.input"/></p>
                    </c:if>
                    <c:if test="${param.wrongData == true}">
                        <p class="errorsM"><fmt:message key="login.incorrect.input"/></p>
                    </c:if>
                    <label for="email"><b><fmt:message key="login.title.email"/></b></label>
                    <input id="email" type="text" placeholder="<fmt:message key="login.email"/>" name="email" required>
                    <label for="password"><b><fmt:message key="login.title.password"/></b></label>
                    <input id="password" type="password" placeholder="<fmt:message key="login.password"/>" name="password" required>
                    <hr>
                    <button type="submit" class="register_btn"><fmt:message key="login.submit.btn"/></button>
                </div>

                <div class="container sign_up">
                    <p><fmt:message key="login.have.no.acc"/> <a
                            href="${pageContext.request.contextPath}/cinema/registerUser">
                        <fmt:message key="login.sign.up"/></a>.</p>
                </div>
                <div style="height: 60px"></div>
            </form>
        </div>

    </div>
</div>
</body>
</html>