<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html lang="${param.lang}">
<jsp:include page="/WEB-INF/parts/head_tag.jsp"/>

<div id="wrapper">
    <header id="header">
        <jsp:include page="/WEB-INF/parts/header.jsp"/>
        <div style="width: 100%;" class="container">
            <h1 style="color: #a71d2a"><c:out value="${requestScope.movie.title}"/></h1>
            <h3><fmt:message key="show.movie.session.title"/>
                <c:out value="${requestScope.movieSession.showTime.dayOfMonth}"/>/
                <c:out value="${requestScope.movieSession.showTime.month}"/>/
                <c:out value="${requestScope.movieSession.showTime.year}"/> --
                <c:out value="${requestScope.movieSession.showTime.hour}"/>:
                <c:out value="${requestScope.movieSession.showTime.minute}"/>0
            </h3>
            <div class="grid-container">
                <c:forEach items="${requestScope.allSeatsList}" var="seat">
                    <div class="grid-item">
                        <img src="${pageContext.request.contextPath}/img/seat_icon.jpg" alt="seat">
                        <c:out value="${seat.number}"/>
                    </div>
                </c:forEach>
            </div>
        </div>
    </header>
</div>
</html>
