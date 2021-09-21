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
        <div class="card">
            <div class="card-body">
                <h1 class="card-title"><c:out value="${requestScope.movie.title}"/></h1>
                <h3 class="card-text"><small class="text-black-50"><fmt:message key="movie.director"/><c:out
                        value="${requestScope.movie.director}"/></small></h3>
                <h3 class="card-text"><small class="text-black-50"><fmt:message key="movie.country"/><c:out
                        value="${requestScope.movie.country}"/></small></h3>
                <h3 class="card-text"><small class="text-black-50"><fmt:message key="movie.year"/><c:out
                        value="${requestScope.movie.year}"/></small></h3>
                <h3 class="card-text"><small class="text-black-50"><fmt:message key="movie.description"/><c:out
                        value="${requestScope.movie.description}"/></small></h3>
            </div>
            <img class="card-img-bottom" src="<c:out value="${requestScope.movie.photoUrl}"/>" alt="movie image cap"
                 style="width:50%; position: center">
            <a href="${pageContext.request.contextPath}/cinema/manageOrder?movieId=${requestScope.movie.id}" class="btn btn-primary"><fmt:message key="show.movie.order.tickets"/></a>
        </div>
    </header>
</div>
</html>
