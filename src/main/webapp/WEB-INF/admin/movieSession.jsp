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
        <jsp:include page="/WEB-INF/parts/header_admin_account.jsp"/>
    </header>
</div>

<div class="container">
    <c:if test="${param.movSessionBadInput == true}">
        <p class="errorsM"><fmt:message key="update.movie.session.bad.input"/></p>
    </c:if>
    <c:if test="${param.movSessionSuccessUpdate == true}">
        <p class="alert-success"><fmt:message key="update.movie.session.successful"/></p>
    </c:if>
    <c:if test="${param.movSessionSuccessUpdate == false}">
        <p class="errorsM"><fmt:message key="update.movie.session.false"/></p>
    </c:if>

    <c:if test="${param.movieSesSuccessDel == true}">
        <p class="alert-success"><fmt:message key="delete.movie.session.successful"/></p>
    </c:if>
    <c:if test="${param.movieSesSuccessDel == false}">
        <p class="errorsM"><fmt:message key="delete.movie.session.false"/></p>
    </c:if>
    <a href="${pageContext.request.contextPath}/cinema/addMovieSession?movieId=${requestScope.movieId}">
        <button type="button" class="btn btn-primary btn-lg btn-block"><fmt:message key="add.movie.session.button.add"/></button>
    </a>
    <table id="movie_sessions" class="table table-bordered table-sm">
        <thead class="thead-dark">
        <tr>
            <th data-type="text" class="th-sm cursor"><fmt:message key="movie.movie.session.show.time.date"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="movie.movie.session.show.time.time"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="movie.movie.session.price"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="movie.session.nav.bar.edit"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="movie.session.nav.bar.delete"/></th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:forEach items="${requestScope.moviesSesList}" var="movieSession">
            <tr>
                <td>
                    <c:out value="${movieSession.showTime.dayOfMonth}"/>/
                    <c:out value="${movieSession.showTime.month}"/>/
                    <c:out value="${movieSession.showTime.year}"/>
                </td>
                <td><c:out value="${movieSession.showTime.hour}"/>:
                    <c:out value="${movieSession.showTime.minute}"/>
                </td>
                <td><c:out value="${movieSession.ticketPrice}"/></td>
                <td><a href="${pageContext.request.contextPath}/cinema/editMovieSession?id=${movieSession.id}">
                    <fmt:message key="movie.session.nav.bar.edit"/></a></td>
                <td><a href="${pageContext.request.contextPath}/cinema/deleteMovieSession?movieSesId=${movieSession.id}">
                    <fmt:message key="movie.session.nav.bar.delete"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>