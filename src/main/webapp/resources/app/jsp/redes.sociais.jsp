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
			<table id="tableRedes" class="table table-hover table-condensed">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Tipo</th>
					</tr>
				</thead>
				<tbody>
				<!-- <tr ng-repeat="portal in portais |orderBy:sort.column:sort.descending| filter:paginate"> -->

				<!-- <tr ng-repeat="metrica in metricas"> -->
				<tr
					dir-paginate="rede in redes|orderBy:sortType:reverse|itemsPerPage:10 "
					current-page="currentPage">
					<td>{{ rede.nome }}</td>
					<td>{{ rede.tipo}}</td>
				</tr>
			</tbody>
		</table>
		<div class="text-center">
			<dir-pagination-controls max-size="10" direction-links="true"
				boundary-links="true"></dir-pagination-controls>
		</div>
		</div>
	</div>
	</div>
</div>