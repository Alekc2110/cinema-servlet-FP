<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>


<div class="header_white">
    <div>
        <div>
            <div class="lang_block">
                <ul id="lang">
                    <li>
                        <a href="?locale=ru">ru</a>
                    </li>
                    <li class="lang-item lang-item-5">
                        <a href="?locale=en">en</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <ul class="navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/cinema/showMovies">
                <fmt:message key="nav.bar.now.showing"/></a>
        </li>
        <li class="nav-item">
            <c:if test="${sessionScope.loginedUser.role.name().equals('USER')}">
                <p style="float: left; padding-top: 10px; color: #4b4a77">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cinema/userAccount">
                        <fmt:message key="nav.bar.profile"/></a>
                </p>
            </c:if>
            <c:if test="${sessionScope.loginedUser.role.name().equals('ADMIN')}">
                <p style="float: left; padding-top: 10px; color: #4b4a77">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cinema/adminAccount">
                        <fmt:message key="nav.bar.profile"/></a>
                </p>
            </c:if>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/jsp/common/multiplexInfo.jsp"><fmt:message key="nav.bar.contacts"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/cinema/login"><fmt:message key="user.account.login"/></a>
        </li>
    </ul>
</nav>

