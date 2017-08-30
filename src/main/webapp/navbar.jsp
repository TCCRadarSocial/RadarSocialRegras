<%@include file="/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Radar Social</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <sec:authorize access="hasRole('ROLE_ADMIN')">
		  <li><a href="#">Usuários</a></li>
		</sec:authorize>
      <li><a href="#">Metas</a></li>
      <li><a href="<c:url value="/logout" />">Logout</a></li>
    </ul>
  </div>
</nav>