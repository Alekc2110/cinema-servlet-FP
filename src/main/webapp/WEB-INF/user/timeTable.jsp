<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html lang="${param.lang}">
<jsp:include page="/WEB-INF/parts/head_tag.jsp"/>

<body class="home">
<div id="wrapper">
    <header id="header">
        <jsp:include page="/WEB-INF/parts/header.jsp"/>
        <section class="slider_home">
            <ul id="main_slider">
                <li>
                    <div class="grid-containerTable">
                        <div class="grid-itemTable">
                            <h2><c:out value="${requestScope.movieListNow.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${requestScope.movieListNow}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>

                        <div class="grid-itemTable">
                            <h2><c:out value="${requestScope.movieListTomorrow.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${requestScope.movieListTomorrow}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>

                        <div class="grid-itemTable">
                            <h2><c:out value="${requestScope.movieListNowPlTwo.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${requestScope.movieListNowPlTwo}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>

                        <div class="grid-itemTable">
                            <h2><c:out value="${requestScope.movieListNowPlThree.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${requestScope.movieListNowPlThree}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>

                        <div class="grid-itemTable">
                            <h2><c:out value="${requestScope.movieListNowPlFour.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${requestScope.movieListNowPlFour}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>

                        <div class="grid-itemTable">
                            <h2><c:out value="${requestScope.movieListNowPlFive.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${requestScope.movieListNowPlFive}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>

                        <div class="grid-itemTable">
                            <h2><c:out value="${requestScope.movieListNowPlSix.get(0).date}"/></h2>
                            <h4>
                                <c:forEach items="${requestScope.movieListNowPlSix}" var="timeTableDto">
                                    <strong style="color: #a71d2a"><c:out value="${timeTableDto.time}"/></strong> : <c:out value="${timeTableDto.movieTitle}"/>
                                    <br>
                                </c:forEach>
                            </h4>
                        </div>


                    </div>
                    <img src="${pageContext.request.contextPath}/img/multiplex_main.jpg" class="desktop-slide" width="1680"
                         height="1050" alt="main_picture">
                </li>
            </ul>
        </section>
    </header>
</div>
<jsp:include page="/WEB-INF/parts/footer.jsp"/>
</body>
</html>