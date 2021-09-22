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

    <input class="form-control table-sm" id="myInput" type="text" placeholder="Search..">
    <br>
<%--<div class="container">--%>
    <table id="movies" class="table table-bordered table-sm">
        <thead class="thead-dark">
        <tr>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.movie.title"/>
                <img src="${pageContext.request.contextPath}/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.movie.director"/>
                <img src="${pageContext.request.contextPath}/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.movie.year"/>
                <img src="${pageContext.request.contextPath}/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.movie.country"/>
                <img src="${pageContext.request.contextPath}/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.movie.details"/></th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:forEach items="${requestScope.movieList}" var="movie">
            <tr>
                <td><c:out value="${movie.title}"/></td>
                <td><c:out value="${movie.director}"/></td>
                <td><c:out value="${movie.year}"/></td>
                <td><c:out value="${movie.country}"/></td>
                <td><a style="color: #a71d2a" href="${pageContext.request.contextPath}/cinema/movieDetail?id=${movie.id}">
                    <fmt:message key="show.movie.table.details.link"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<%--</div>--%>
<script src="${pageContext.request.contextPath}/js/search.js"></script>
<script src="${pageContext.request.contextPath}/js/orderSort.js"></script>
</body>
</html>