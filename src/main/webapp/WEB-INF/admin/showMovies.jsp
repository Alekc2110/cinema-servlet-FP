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

<div class="container">
    <p><fmt:message key="show.movie.table.title"/></p>
    <c:if test="${param.badInput == true}">
        <p class="errorsM"><fmt:message key="update.movie.bad.input"/></p>
    </c:if>
    <c:if test="${param.successUpdate == true}">
        <p class="alert-success"><fmt:message key="update.movie.successful"/></p>
    </c:if>
    <c:if test="${param.successUpdate == false}">
        <p class="errorsM"><fmt:message key="update.movie.false"/></p>
    </c:if>
    <c:if test="${param.successDel == true}">
        <p class="alert-success"><fmt:message key="delete.movie.successful"/></p>
    </c:if>
    <c:if test="${param.successDel == false}">
        <p class="errorsM"><fmt:message key="delete.movie.false"/></p>
    </c:if>
    <c:if test="${param.successAdd == true}">
        <p class="alert-success"><fmt:message key="add.movie.successful"/></p>
    </c:if>
    <c:if test="${param.successAdd == false}">
        <p class="errorsM"><fmt:message key="add.movie.false"/></p>
    </c:if>
    <a href="${pageContext.request.contextPath}/cinema/addMovie">
    <button type="button" class="btn btn-primary btn-lg btn-block"><fmt:message key="add.movie.button.add"/></button>
    </a>

    <table id="all_movies" class="table table-bordered table-sm">
        <thead class="thead-dark">
        <tr>
            <th data-type="integer" class="th-sm cursor"><fmt:message key="show.movie.id"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.movie.title"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.movie.director"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.movie.year"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.movie.country"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.movie.movie.sessions"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.movie.edit"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.movie.delete"/></th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:forEach items="${requestScope.allMoviesList}" var="movie">
            <tr>
                <td><c:out value="${movie.id}"/></td>
                <td><c:out value="${movie.title}"/></td>
                <td><c:out value="${movie.director}"/></td>
                <td><c:out value="${movie.year}"/></td>
                <td><c:out value="${movie.country}"/></td>
                <td><a href="${pageContext.request.contextPath}/cinema/manageMovieSession?movieId=${movie.id}">
                    <fmt:message key="nav.bar.movie.sessions"/></a></td>
                <td><a href="${pageContext.request.contextPath}/cinema/editMovie?movieId=${movie.id}"><fmt:message
                        key="nav.bar.movie.edit"/></a></td>
                <td><a href="${pageContext.request.contextPath}/cinema/deleteMovie?movieId=${movie.id}"><fmt:message
                        key="nav.bar.movie.delete"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>