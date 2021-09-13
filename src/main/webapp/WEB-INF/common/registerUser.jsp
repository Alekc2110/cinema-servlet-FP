<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html lang="${param.lang}">
<jsp:include page="/WEB-INF/parts/head_tag.jsp"/>

<body class="home_blog">
<div id="wrapper">
    <header id="header">
        <jsp:include page="/WEB-INF/parts/header.jsp"/>
    </header>
    <div class="center">
        <div class="register">
            <form method="POST" action="${pageContext.request.contextPath}/cinema/register">
                <div class="container">
                    <h1><fmt:message key="register.title"/></h1>
                    <p><fmt:message key="register.ask.to.fill"/></p>
                    <hr>

                    <label for="name"><b><fmt:message key="register.name"/></b></label>
                    <c:if test="${param.badInput == true}">
                        <p class="errorsM"><fmt:message key="register.name.error"/></p>
                    </c:if>
                    <input id="name" type="text" placeholder="<fmt:message key="register.name"/>" name="name" required/>

                    <label for="email"><b><fmt:message key="register.email"/></b></label>
                    <c:if test="${param.badInput == true}">
                        <p class="errorsM"><fmt:message key="register.email.error"/></p>
                    </c:if>
                    <c:if test="${param.badEmail == true}">
                        <p class="errorsM"><fmt:message key="register.email.already.exists.error"/></p>
                    </c:if>
                    <input id="email" type="email" placeholder="<fmt:message key="register.email"/>" name="email"
                           required/>
                    <hr>

                    <label for="pass"><b><fmt:message key="register.password"/></b></label>
                    <c:if test="${param.badInput == true}">
                        <p class="errorsM"><fmt:message key="register.password.error"/></p>
                    </c:if>
                    <input id="pass" type="password" placeholder="<fmt:message key="register.enter.password"/>"
                           name="password" required/>

                    <label for="repPass"><b><fmt:message key="register.repeat.password"/></b></label>
                    <input id="repPass" type="password" placeholder="<fmt:message key="register.repeat.password"/>"
                           name="password_repeat" required/>
                    <hr>

                    <p><fmt:message key="register.agree.creating"/><a href="#">
                        <fmt:message key="register.terms.privacy"/></a>.</p>
                    <button type="submit" class="register_btn"><fmt:message key="register.btn.submit"/></button>
                </div>

                <div class="container signin">
                    <p><fmt:message key="register.have.acc"/> <a href="${pageContext.request.contextPath}/cinema/login">
                        <fmt:message key="register.sign.in"/></a>.
                    </p>
                </div>
                <div style="height: 60px"></div>
            </form>
        </div>
    </div>
</div>
</body>
</html>