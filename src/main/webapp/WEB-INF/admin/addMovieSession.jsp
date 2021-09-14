<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>


<html lang="${param.lang}">
<jsp:include page="/WEB-INF/parts/head_tag.jsp"/>

<form id="form-edit-icon" class="form-horizontal" method="POST"
      action="${pageContext.request.contextPath}/cinema/addMovieSession?movieId=${requestScope.movieId}">
    <fieldset>
        <!-- Form Name -->
        <legend><fmt:message key="add.movie.session.table.title"/></legend>

        <!-- Show_time input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="show_time"><fmt:message key="add.movie.session.table.show.time"/></label>
            <div class="col-md-5">
                <input
                        id="show_time"
                        name="show_time"
                        type="text"
                        class="form-control input-md"
                        required pattern="^[0-9]{4}-[0-9]{2}-[0-9]{2}\s+[0-9]{2}:[0-9]{2}$"
                >
            </div>
        </div>

        <!-- Price-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="price"><fmt:message key="add.movie.session.table.price"/></label>
            <div class="col-md-5">
                <input
                        id="price"
                        name="price"
                        type="text"
                        class="form-control input-md"
                        required pattern="^[0-9]{1,3}$"
                />
            </div>
        </div>
    </fieldset>
    <fieldset>
        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="save"></label>
            <div class="col-md-4">
                <button id="save" type="submit" name="save" class="btn btn-primary">
                    <fmt:message key="add.movie.session.button.save"/></button>
            </div>
        </div>
    </fieldset>
</form>
<!-- Button -->
<div class="form-group">
    <label class="col-md-4 control-label" for="close"></label>
    <div class="col-md-4">
        <a href="${pageContext.request.contextPath}/cinema/manageMovieSession">
            <button id="close" name="close" data-dismiss="modal" aria-label="Close" class="btn btn-info"><fmt:message
                    key="add.movie.session.button.close"/></button>
        </a>
    </div>
</div>
