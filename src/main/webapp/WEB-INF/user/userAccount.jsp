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

<div class="card bg-dark text-white">
    <img src="${pageContext.request.contextPath}/img/multiplex_main.jpg" width="1680"  height="1050" alt="main picture">
    <div class="card-img-overlay justify-content-end" >
        <img src="${pageContext.request.contextPath}/img/client-image.jpg"  width="120px" height="120px" alt="user_icon">
        <h5 style="color: #ffffff" class="card-title"><fmt:message key="user.account.role"/><c:out value="${sessionScope.loginedUser.role}"/></h5>
        <br/>
        <h5 style="color: #ffffff" class="card-title"><fmt:message key="user.account.name"/><c:out value="${sessionScope.loginedUser.name}"/></h5>
        <br/>
        <h5 style="color: #ffffff" class="card-title"><fmt:message key="user.account.email"/><c:out value="${sessionScope.loginedUser.email}"/></h5>
    </div>
</div>


<jsp:include page="/WEB-INF/parts/footer.jsp"/>
</body>
</html>