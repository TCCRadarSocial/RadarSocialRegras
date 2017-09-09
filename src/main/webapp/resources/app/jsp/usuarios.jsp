<%@include file="/header.jsp"%>
<%@include file="/navbar.jsp"%>
<div ng-app="myApp" ng-controller="AppCtrl">
	<div class="modal-body">
		<form method="POST"
			action="http://localhost:8080/RadarSocialRegras/salvaUsuario">
			<div class="form-group">
				<label for="usr">Nome: *</label> <input type="text"
					class="form-control" id="nome" name="nome" value="${valorNome}" maxlength="50">
			</div>
			<div class="form-group">
				<label for="usr">Login: *</label> <input type="text"
					class="form-control" id="login" name="login" value="${valorLogin}" maxlength="50">
			</div>
			<div class="form-group">
				<label for="pwd">Senha: *</label> <input type="password"
					class="form-control" id="senha" name="senha" maxlength="50">
			</div>
			<button type="submit" class="btn btn-primary">Salvar</button>
			<span style="color:red;" id="msg">${mensagem}</span>
		</form>
		<div class="col-md-12" style="padding-top: 20px;">
			<table id="tableUser" class="table table-hover table-condensed">
				<thead>
					<tr>
						<th>Login</th>
						<th>Nome</th>
					</tr>
				</thead>
				<cF:forEach items="${users}" var="user">
					<tr>
						<td>${user.getUsername()}</td>
						<td>${user.getName()}</td>
						<td><a href="/RadarSocialRegras/editaUsuario/${user.getUsername()}&${user.getName()}"><i
								class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
						<td><a href="#" data-toggle="modal"  ng-click="modalExclusao()"><i class="fa fa-times"
								aria-hidden="true"></i></a></td>
					</tr>
				</cF:forEach>
			</table>
		</div>
	</div>
	<div id="modalExclusao" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 class="modal-title">Excluir Usuário</h3>
				</div>
				<div class="modal-body">
					Deseja realmente excluir o usuário {{login}} ?
					<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="confirmaExclusao(login)">Sim</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Não</button>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>
</div>