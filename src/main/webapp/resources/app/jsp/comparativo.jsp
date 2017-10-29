<%@include file="/header.jsp"%>
<%@include file="/navbar.jsp"%>
<div ng-app="myApp" ng-controller="feedController">
	<div class="modal-body">
		<div class="col-lg-12" style="align: center;">
		<div class='col-lg-12' align="center" style="margin-bottom:25px;">
				<Strong>Filtros:</Strong> <br> <span
					ng-if="labelDataInicial != ''">Data Inicial:
					{{labelDataInicialComparativo}} |</span> 
					<span ng-if="labelDataFinal != ''">Data
					Final: {{labelDataFinalComparativo}} |</span>
			</div>
			<div class="col-lg-8">
			<div class="col-lg-6">
			
			
				<div class="col-sm-12">
					<b>Data Criação:</b>
					<div class='col-sm-12'>
						Inicial:
						<div class='input-group date' id='datetimepicker1'
							data-ng-model="dataInicial">
							<input type='text' class="form-control" id="dataInicial" /> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<br><br><br>
					<div class='col-sm-12'>
						Final:
						<div class='input-group date' id='datetimepicker2'
							data-ng-model="dataFinal">
							<input type='text' class="form-control" id="dataFinal" /> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				</div>
				<div class="form-group col-lg-6">
					<label for="portais">Portal Facebook:</label> <select class="form-control"
						ng-model="facebookOpcao" id="portaisFace">
						<option value="">Selecione</option>
						<option ng-repeat="f in facebookPortais"
							ng-if="f != '' && f != []" value="{{f}}">{{f}}</option>
					</select>
				</div>
				<div class="form-group col-lg-6">
					<label for="portais">Portal Twitter:</label> <select class="form-control"
						ng-model="twitterOpcao" id="portaisTwitter">
						<option value="">Selecione</option>
						<option ng-repeat="t in twitterPortais" ng-if="t != '' && t != []"
							value="{{t}}">{{t}}</option>
					</select>
				</div>
				<br>
				<br>
				<div class="form-group col-lg-6">
					<button type="button" class="btn btn-primary"
						ng-click="buscarComparativo()" id="botaoBuscarComparativo">
						<span class="glyphicon glyphicon-search"></span> Comparar
					</button>
				</div>
				<br>
				<nvd3 options='optionsComparativo' data='data' api="api"></nvd3>
			</div>
		</div>
	</div>

</div>