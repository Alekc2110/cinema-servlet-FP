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
        <%--        <jsp:include page="/WEB-INF/parts/header.jsp"/>--%>
        <div class="container">
            <div class="row">
                <div class="col-xs-2 border1 col-xs-offset-4">
                    <h1 class="card-title"><c:out value="${requestScope.movie.title}"/></h1>
                    <img class="card-img-bottom" src="<c:out value="${requestScope.movie.photoUrl}"/>"
                         alt="movie image cap"
                         style="width:80%; position: center">
                </div>
                <div style="text-align: center" class="col-xs-2 border1">
                    <h2><strong><fmt:message key="order.confirmation.title"/></strong></h2>
                    <br>
                    <h3><strong><fmt:message key="order.confirmation.movie.session.time"/></strong>
                        <c:out value="${requestScope.movieSession.showTime.dayOfMonth}"/>/
                        <c:out value="${requestScope.movieSession.showTime.month}"/>/
                        <c:out value="${requestScope.movieSession.showTime.year}"/> ---
                        <c:out value="${requestScope.movieSession.showTime.hour}"/>:
                        <c:out value="${requestScope.movieSession.showTime.minute}"/>0
                    </h3>
                    <br>
                    <h3><strong><fmt:message key="order.confirmation.order.price"/></strong><c:out
                            value="${sessionScope.order.orderPrice}"/> <fmt:message key="order.price.currency"/></h3>
                    <br>
                    <h3>
                        <c:forEach items="${sessionScope.order.ticketList}" var="ticket">
                            <span><fmt:message key="order.row.number"/></span> : <span style="color: #830b21"><c:out
                                value="${ticket.row.number}"/></span>
                            <span><fmt:message key="order.seat.number"/></span> : <span style="color: #830b21"><c:out
                                value="${ticket.seat.number}"/></span>
                            <hr>
                            <br>
                        </c:forEach>
                    </h3>
                    <br>
                    <h5><fmt:message key="order.confirmation.order.time"/>
                        <c:out value="${sessionScope.order.orderTime.dayOfMonth}"/>/
                        <c:out value="${sessionScope.order.orderTime.month}"/>/
                        <c:out value="${sessionScope.order.orderTime.year}"/>---
                        <c:out value="${sessionScope.order.orderTime.hour}"/>:
                        <c:out value="${sessionScope.order.orderTime.minute}"/>
                    </h5>
                    <div class="row">
                        <div class="col-lg-12">
                            <a href="${pageContext.request.contextPath}/cinema/confirmOrder?movieSesId=${requestScope.movieSession.id}">
                                <button type="submit" class="logout"><fmt:message key="order.confirm"/></button>
                            </a>
                            <a href="${pageContext.request.contextPath}/cinema/cancelOrder">
                                <button type="submit" class="logout"><fmt:message key="order.cancel"/></button>
                            </a>
                        </div>
                    </div>

                </div>
            </div>

        </div>

    </header>

</div>
</html>
