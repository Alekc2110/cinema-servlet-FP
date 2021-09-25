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

    <input class="form-control table-sm" id="myInput" type="text" placeholder="Search..">
    <br>

    <table id="tickets" class="table table-bordered table-sm">
        <thead class="thead-light">
        <tr>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.ticket.date"/>
                <img src="${pageContext.request.contextPath}/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.ticket.time"/>
                <img src="${pageContext.request.contextPath}/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.ticket.row.number"/>
                <img src="${pageContext.request.contextPath}/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.ticket.seat.number"/>
                <img src="${pageContext.request.contextPath}/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="show.ticket.price"/>
                <img src="${pageContext.request.contextPath}/img/sort-icon.png" width="20px" height="20px" alt="sort">
            </th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:forEach items="${requestScope.ticketList}" var="ticket" begin="0" end="${requestScope.recordPerPage -1}">
            <tr>
                <td><c:out value="${ticket.movieSession.date}"/></td>
                <td><c:out value="${ticket.movieSession.time}"/></td>
                <td><c:out value="${ticket.row.number}"/></td>
                <td><c:out value="${ticket.seat.number}"/></td>
                <td><c:out value="${ticket.movieSession.ticketPrice}"/></td>

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
                   href="${pageContext.request.contextPath}/cinema/ticketsTable?pagination=${pagNumber}">
                    <c:out value="${pagNumber}"/></a>
            </li>
        </c:forEach>
    </ul>
</nav>

<script src="${pageContext.request.contextPath}/js/search.js"></script>
<script src="${pageContext.request.contextPath}/js/orderSort.js"></script>
</body>
</html>