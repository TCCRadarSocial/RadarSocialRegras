<%@include file="/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="cF" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div ng-app="myApp" ng-controller="AppCtrl">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value="/home"/>">Radar Social</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/home"/>">Home</a></li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="<c:url value="/usuarios"/>">Usu�rios</a></li>
				</sec:authorize>
				<li><a href="<c:url value="/redesSociais"/>">Redes
						Sociais</a></li>
				<li><a href="<c:url value="/comparativo"/>">Comparativo Redes
						Sociais</a></li>
				<li><a href="<c:url value="/logout" />">Logout</a></li>
			</ul>
		</div>
	</nav>
	<!-- <div id="modalRede" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 class="modal-title">Gerenciamento de Redes Sociais</h3>
				</div>
				<div class="modal-body">
					<form method="POST"
						action="http://localhost:8080/RadarSocialRegras/salvaRedeSocial">
						<div class="form-group">
							<label for="sel1">Tipo Rede Social:</label> <select
								class="form-control" id="tipo" name="tipo">
								<option value="facebook">Facebook</option>
								<option value="twitter">Twitter</option>
							</select>
						</div>
						<div class="form-group">
							<label for="usr">Nome:</label> <input type="text"
								class="form-control" id="nome" name="nome">
						</div>
						<button type="submit" class="btn btn-primary">Salvar</button>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>-->
	<!-- <div id="modalExclusao" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 class="modal-title">Excluir Usu�rio</h3>
				</div>
				<div class="modal-body">
					Deseja realmente excluir o usu�rio {{login}} ?
					<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="confirmaExclusao(login)">Sim</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">N�o</button>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>-->
</div>