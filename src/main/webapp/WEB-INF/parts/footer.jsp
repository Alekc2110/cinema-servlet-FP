<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>


<footer id="footer">
    <div class="center">
        <div class="copyright">
            <div class="a-center">
                <fmt:message key="footer.info"/>
            </div>
        </div>
    </div>
</footer>
