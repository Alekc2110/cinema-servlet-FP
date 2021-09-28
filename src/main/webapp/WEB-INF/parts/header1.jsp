<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<div style="margin: 0; padding-left: 0" class="nav justify-content-end">
    <div>
        <div class="lang_block">
            <div>
                <h4>
                    <c:if test="${sessionScope.loginedUser.role.name().equals('USER')}">
                        <a href="${pageContext.request.contextPath}/cinema/userAccount">
                            <fmt:message key="nav.bar.profile"/></a>
                    </c:if>
                    <c:if test="${sessionScope.loginedUser.role.name().equals('ADMIN')}">
                        <a href="${pageContext.request.contextPath}/cinema/adminAccount">
                            <fmt:message key="nav.bar.profile"/></a>
                    </c:if>
                    <c:if test="${!sessionScope.loginedUser.role.name().equals('ADMIN') && !sessionScope.loginedUser.role.name().equals('USER')}">
                        <a href="${pageContext.request.contextPath}/cinema/login">
                            <fmt:message key="nav.bar.profile"/></a>
                    </c:if>
                </h4>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link active" href="${pageContext.request.contextPath}/cinema/showMovies">
                <fmt:message key="nav.bar.now.showing"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/jsp/common/multiplexInfo.jsp">
                <fmt:message key="nav.bar.contacts"/></a>
        </li>
        <li class="nav-item">
            <c:if test="${sessionScope.loginedUser.role.name().equals('ADMIN')}">
                <a class="nav-link" href="${pageContext.request.contextPath}/cinema/manageMovie">
                    <fmt:message key="nav.bar.all.movies"/></a>
            </c:if>
        </li>

        <li class="nav-item">
            <c:if test="${sessionScope.loginedUser.role.name().equals('ADMIN') || sessionScope.loginedUser.role.name().equals('USER')}">
                <a class="nav-link" href="${pageContext.request.contextPath}/cinema/showMovieTable">
                    <fmt:message key="nav.bar.all.movies.table"/></a>
            </c:if>
        </li>

        <li class="nav-item">
            <c:if test="${sessionScope.loginedUser.role.name().equals('USER')}">
                <a class="nav-link" href="${pageContext.request.contextPath}/cinema/movieTimeTable">
                    <fmt:message key="nav.bar.all.movies.timetable"/></a>
            </c:if>
        </li>

        <li class="nav-item">
            <c:if test="${sessionScope.loginedUser.role.name().equals('ADMIN') || sessionScope.loginedUser.role.name().equals('USER')}">
                <a class="nav-link" href="${pageContext.request.contextPath}/cinema/ticketsTable">
                    <fmt:message key="nav.bar.all.movies.tickets"/></a>
            </c:if>
        </li>

        <li class="nav-item">
            <c:if test="${!sessionScope.loginedUser.role.name().equals('ADMIN') && !sessionScope.loginedUser.role.name().equals('USER')}">
                <a class="nav-link" href="${pageContext.request.contextPath}/cinema/login">
                    <fmt:message key="user.account.login"/></a>
            </c:if>
        </li>
        <li class="nav-item">
            <c:if test="${sessionScope.loginedUser.role.name().equals('ADMIN') || sessionScope.loginedUser.role.name().equals('USER')}">
                <a class="nav-link" href="${pageContext.request.contextPath}/cinema/logOut">
                    <fmt:message key="user.account.logout"/></a>
            </c:if>
        </li>
    </ul>
</nav>

