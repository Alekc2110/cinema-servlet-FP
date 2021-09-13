<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>


<html lang="${param.lang}">
<jsp:include page="/WEB-INF/parts/head_tag.jsp"/>

<form id="form-edit-icon" class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/cinema/editMovieSession?movieSesId=${requestScope.movieSesEdit.id}">
    <fieldset>
        <!-- Form Name -->
        <legend><c:out value="${requestScope.movieTitle}"/></legend>

        <!-- Show_time input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="show_time"><fmt:message key="update.movie.session.showtime"/></label>
            <div class="col-md-5">
                <input
                        id="show_time"
                        name="show_time"
                        type="text"
                        class="form-control input-md"
                        data-pattern-error="<fmt:message key='data.non-valid'/>"
                        data-required-error="<fmt:message key='data.required'/>"
                        required pattern="^[0-9]{4}-[0-9]{2}-[0-9]{2}\s+[0-9]{2}:[0-9]{2}$"
                        value="<c:out value="${requestScope.movieSesEdit.showTime.year}"/>-<c:out value="${requestScope.movieSesEdit.showTime.month.ordinal()}"/>-<c:out value="${requestScope.movieSesEdit.showTime.dayOfMonth}"/> <c:out value="${requestScope.movieSesEdit.showTime.hour}"/>:<c:out value="${requestScope.movieSesEdit.showTime.minute}"/>"
                >
            </div>
        </div>

        <!-- Price-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="price"><fmt:message key="update.movie.session.ticket.price"/></label>
            <div class="col-md-5">
                <input
                        id="price"
                        name="price"
                        type="text"
                        class="form-control input-md"
                        data-pattern-error="<fmt:message key='data.non-valid'/>"
                        data-required-error="<fmt:message key='data.required'/>"
                        required pattern="^[0-9]{1,3}$"

                        value="<c:out value="${requestScope.movieSesEdit.ticketPrice}"/>"
                />
            </div>
        </div>
    </fieldset>
    <fieldset>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="save"></label>
            <div class="col-md-4">
                <button id="save" type="submit" name="save" class="btn btn-primary"><fmt:message key="update.movie.session.button.save"/></button>
            </div>
        </div>
    </fieldset>
</form>
<!-- Button -->
<div class="form-group">
    <label class="col-md-4 control-label" for="close"></label>
    <div class="col-md-4">
        <a href="${pageContext.request.contextPath}/cinema/manageMovie">
            <button  id="close" name="close" data-dismiss="modal" aria-label="Close" class="btn btn-info"><fmt:message key="update.movie.session.button.close"/></button>
        </a>
    </div>
</div>
