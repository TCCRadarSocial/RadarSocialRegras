<%@include file="/header.jsp"%>
<style>
.reticencias {
  max-width: 180px; /* Tamanho */
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap
}
</style>
<div ng-app="myApp" ng-controller='feedController' style="padding-left:20px" class="col-lg-12 col-md-12 col-sm-12">
	<div class="panel panel-default">
		<div class="panel-body">
			<div class='col-lg-12' align="center" style="margin-bottom:25px;">
				<Strong>Filtros:</Strong> <br> <span
					ng-if="labelDataInicial != ''">Data Inicial:
					{{labelDataInicial}} |</span> 
					<span ng-if="labelDataFinal != ''">Data
					Final: {{labelDataFinal}} |</span>
					<!-- <button type="button" class="btn btn-primary" 
					 ng-if="dataInicialAnterior !='' && dataFinalAnterior != ''" 
					 ng-click="RedefinirData()">Redefinir Filtro</button>  -->
					
					<span ng-if="labelPortais != ''">Portais:
					{{labelPortais}}</span> 
					<!--  <button type="button" class="btn btn-primary" ng-click="RedefinirPortal()" ng-if="portal != ''">Redefinir Filtro</button> -->
					<br>
			</div>
			<div>
			<div class="col-lg-12">
				<div class="col-lg-2" >
				<b>Rede Social:</b>
					<div class="radio">
					  <label><input type="radio" name="option" ng-model="checkRedeSocial" value="redeAmbos" ng-click="selecionaRede()">Ambos</label>
					</div>
					<div class="radio">
					  <label><input type="radio" name="option" ng-model="checkRedeSocial" value="redeFacebook" ng-click="selecionaRede()">Facebook</label>
					</div>
					<div class="radio">
					  <label><input type="radio" name="option"  ng-model="checkRedeSocial" value="redeTwitter" ng-click="selecionaRede()">Twitter</label>
					</div>
				</div>
				<!-- ng-if="portais != 'Todos'" -->
				<div class="form-group col-lg-2" >
				  <label for="portais">Portais:</label>
				  <select class="form-control" id="portais" ng-model="opcaoPortal">
				    <option ng-repeat="p in portais" ng-if="p != '' && p != []" value="{{p}}">{{p}}</option>
				  </select>
				</div>
				<div class="col-sm-3">
				<b>Data Cria��o:</b>
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
							<input type='text' class="form-control" id="dataFinal"/> <span
								class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group col-lg-2">
				  <label for="portais">Ordena��o:</label>
				  <select class="form-control" id="ordenacao" ng-model="ordenacao">
				    <option value="dataCriacao">Data cria��o</option>
				    <option value="mensagem">Alfab�tica</option>
				    <option value="retweets" ng-if="checkRedeSocial == 'redeTwitter'">Retweets</option>
				    <option value="reactions" ng-if="checkRedeSocial == 'redeFacebook'">Rea��es</option>
				    
				  </select>
				  	<div ng-if="ordenacao == 'dataCriacao'">
					 	<div class="radio">
						  <label><input type="radio" name="optionOrdenacao" ng-model="checkOrdenacao" value="desc" ng-click="selecionaOrdenacao('desc')">Decrescente</label>
						</div>
						<div class="radio">
						  <label><input type="radio" name="optionOrdenacao" ng-model="checkOrdenacao" value="asc" ng-click="selecionaOrdenacao('asc')">Crescente</label>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<label for="portais">Busca:</label>
					<input type='text' class="form-control" id="buscaPalavraChave" placeholder="Digite a palavra-chave" ng-model="palavraChave"/>
				</div>
			</div>
				<div class="col-lg-12" style="text-align:center;margin-top:15px;">
					<button type="button" class="btn btn-primary" ng-click="buscar()" id="botaoBuscar">
						<span class="glyphicon glyphicon-search"></span> Buscar
					</button>
				</div>
		
			</div> 
		</div>
	</div>
	<!-- <div masonry='{ "transitionDuration" : "0.4s" , "itemSelector" : ".tile"}' class="tile-wall" width="100%">
	  <div masonry-tile ng-repeat="metrica in metricas" class="tile">
	  		<div class="col-xs-12">
				<img src="{{metrica.imagem}}" style="width:100%" />
				<label>
					<a	href="http://{{metrica.link}}" target="_blank">
						<span ng-if="metrica.name != null">{{metrica.name}}</span>
						<span ng-if="metrica.name == null" class="textoResumido">{{metrica.link}}</span>
					</a>
				</label>
			</div>
		</div>
	 </div>
	 <div masonry load-images="true">
	  -->
	 <div masonry='{ "transitionDuration" : "0.4s" , "itemSelector" : ".tile"}' class="tile-wall" width="100%">
	    <div class="masonry-brick" ng-repeat="metrica in metricas">
	        <div class="col-xs-12" style="width:200px;padding:10px;box-shadow:2px 2px 2px 2px #888888;margin:13px;">
	        	<div style="text-align:right;">
	        		<span><strong style="font-size:11px">Criado em: {{metrica.dataCriacao}}</strong></span>
	        	</div>
				<div  ng-if="metrica.imagem != ''">
					<img src="{{metrica.imagem}}" style="width:100%" />
				</div>
					<div>
						<a href="{{metrica.link}}"  target="_blank" title="{{metrica.mensagem}}">
							<span class="reticencias" ng-if="metrica.imagem != ''">{{metrica.mensagem | limitTo:25}}...</span>
							<span ng-if="metrica.imagem == ''">{{metrica.mensagem}}</span>
						</a>
					</div>
					<span ng-if="metrica.nomePagina != null">P�gina Facebok: {{metrica.nomePagina}}<br></span>
					<span ng-if="metrica.nomeTwitter != null" >Twitter: {{metrica.nomeTwitter}}<br></span>
					<div style="text-align:right;" ng-if="metrica.reactions > 0">
						<button type="button" class="btn btn-primary" data-toggle="modal" id="btnFace" ng-click="modalFace(metrica.link)"><i class="fa fa-facebook" aria-hidden="true" ></i> {{metrica.reactions}}</button>
					</div>
					<div style="text-align:right;" ng-if="metrica.retweets > 0">
						<button type="button" class="btn btn-info" data-toggle="modal" id="btnTwitter" ng-click="modalTwitter(metrica.link)"><i class="fa fa-twitter" aria-hidden="true" ></i> {{metrica.retweets}}</button>
					</div>					
			</div>
	    </div>
	</div>
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5 class="modal-title">
        
        <img src="{{imagem}}" width="100px" style="float: left;margin-right: 10px;"/>
        <a href="{{link}}" target="_blank">{{mensagem}}</a>
        
        
        </h5>
      </div>
      <div class="modal-body">
		<nvd3 options='options' data='data' api="api"></nvd3>
		<div class="col-lg-12" ng-if="tipoRedeGrafico == 'facebook'">
		<br><br><br>
			<div class="col-lg-8">
			<table>
				<tr>	
					<td>
						<img src="<c:url value="/resources/images/like.png"/>" title="curti"/>
					</td>
					<td>
						<img src="<c:url value="/resources/images/love.png"/>" title="amei"/>
					</td>
					<td>
						<img src="<c:url value="/resources/images/haha.png"/>" title="haha"/>
					</td>
					<td>
						<img src="<c:url value="/resources/images/wow.png"/>" title="wow"/>
					</td>
					<td>
						<img src="<c:url value="/resources/images/sad.png"/>" title="triste"/>
					</td>
					<td>
						<img src="<c:url value="/resources/images/angry.png"/>" title="com raiva"/>
					</td>
					
				</tr>
				<tr style="text-align: center;">	
					<td>
					{{like}}
					</td>
					<td>
					{{love}}
					</td>
					<td>
					{{haha}}
					</td>
					<td>
					{{wow}}
					</td>
					<td>
					{{sad}}
					</td>
					<td>
					{{angry}}
					</td>
				</tr>
			</table>
			</div>
		<br><br><br>	
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
      </div>
    </div>

  </div>
</div>
</div>