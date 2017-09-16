<%@include file="/header.jsp"%>
<%@include file="/navbar.jsp"%>
<div ng-app="myApp" ng-controller="AppCtrl">
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
		<div class="col-md-12" style="padding-top: 20px;">
			<table id="tableUser" class="table table-hover table-condensed">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Tipo</th>
					</tr>
				</thead>
				<cF:forEach items="${users}" var="user">
					<tr>
						<td>${user.getUsername()}</td>
						<td>${user.getName()}</td>
						<td><a href="/RadarSocialRegras/editaUsuario/${user.getUsername()}&${user.getName()}"><i
								class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
						<td><a href="/RadarSocialRegras/excluiUsuario/${user.getUsername()}"><i class="fa fa-times"
								aria-hidden="true"></i></a></td>
								<!--<i class="fa fa-times"
								aria-hidden="true"> <a href="#" data-toggle="modal" data-target="#modalExclusao" ng-click="modalExclusao()">Excluir</a></button>
						 -->
					</tr>
				</cF:forEach>
			</table>
		</div>
	</div>
	</div>
</div>