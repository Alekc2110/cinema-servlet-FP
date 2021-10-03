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

    <div class="form-group">
        <form id="form-edit-icon" class="form-horizontal" method="POST"
              action="${pageContext.request.contextPath}/cinema/showMovieTable">
            <label for="sel1"><fmt:message key="sort.by.table.name"/></label>
            <select class="form-table" name="option" id="sel1">
                <option></option>
                <option value="title"><fmt:message key="sort.by.movie.title"/></option>
                <option value="date"><fmt:message key="sort.by.movie.session.date"/></option>
                <option value="time"><fmt:message key="sort.by.movie.session.time"/></option>
            </select>
            <div class="form-group">
                <label class="col-md-4 control-label" for="save"></label>
                <div class="col-md-4">
                    <button id="save" type="submit" class="btn btn-primary">
                        <fmt:message key="sort.button.name"/></button>
                </div>
            </div>
        </form>
    </div>

    <table id="movies" class="table table-bordered table-sm">
        <thead class="thead-light">
        <tr>
            <th data-type="text" class="th-sm cursor"><fmt:message key="sort.show.movie.title"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="sort.show.movie.date"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="sort.show.movie.time"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.movie.details"/></th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:forEach items="${requestScope.movieList}" var="showMovie" begin="0" end="${requestScope.recordPerPage -1}">
            <tr>
                <td><c:out value="${showMovie.movieTitle}"/></td>
                <td><c:out value="${showMovie.date}"/></td>
                <td><c:out value="${showMovie.time}"/></td>
                <td><a style="color: #a71d2a"
                       href="${pageContext.request.contextPath}/cinema/movieDetail?id=${showMovie.id}">
                    <fmt:message key="show.movie.table.details.link"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<nav aria-label="...">
    <ul class="pagination pagination-sm justify-content-center">
        <c:forEach var="pagNumber" begin="1" end="${requestScope.pageNumbers}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/cinema/showMovieTable?pagination=${pagNumber}&option=${requestScope.option}">
                    <c:out value="${pagNumber}"/></a>
            </li>
        </c:forEach>
    </ul>
</nav>

</body>
</html>