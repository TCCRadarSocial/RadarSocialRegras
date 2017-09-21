var myApp = angular.module('myApp', [ 'nvd3',
		'angularUtils.directives.dirPagination', 'masonry' ]);

myApp
		.directive(
				"metricsFacebook",
				function() {
					return {
						templateUrl : '/RadarSocialRegras/resources/app/jsp/metrics.facebook.jsp'
					};
				});

myApp
		.directive(
				"metricsTwitter",
				function() {
					return {
						templateUrl : '/RadarSocialRegras/resources/app/jsp/metrics.twitter.jsp'
					};
				});

myApp.directive("metricsFeeds", function() {
	return {
		templateUrl : '/RadarSocialRegras/resources/app/jsp/metrics.feeds.jsp'
	};
});

myApp.controller('AppCtrl', [ '$scope', '$q', '$http',
		function($scope, $q, $http) {

			$scope.selectedTab = 'feed';

			// $scope.modalUser = function() {
			// $('#modalUser').modal();
			// carregaUsuarios();
			// }

			$scope.currentPage = 1;

			$scope.modalRede = function() {
				$('#modalRede').modal();
			}

			$scope.modalExclusao = function() {
				$('#modalExclusao').modal();
				$scope.login = username;
			}

			carregaUsuarios();
			function carregaUsuarios() {

				var deferred = $q.defer();

				var method = 'POST';
				var url = '/RadarSocialRegras/listaUsuario';
				var req = {
					method : method,
					url : url
				}

				$http(req).success(function(data, status, headers, config) {
					deferred.resolve(data);
				}).error(deferred.resolve);

				deferred.promise.then(function(data) {
					$('#tableUser').html($('#tableUser', data).html());

				});

			}

			carregaRedesSociais();
			function carregaRedesSociais() {
				$scope.redes = [];
				var totalReactions = [];
				var deferred = $q.defer();

				var method = 'POST';
				var url = '/RadarSocialRegras/listaRedes';
				var req = {
					method : method,
					url : url
				}

				$http(req).success(function(data, status, headers, config) {
					deferred.resolve(data);
				}).error(deferred.resolve);

				deferred.promise.then(function(data) {
					console.log(data);
					data.map(function(rede) {

						if (data.length > 1) {
							angular.forEach(rede, function(value, key) {
								$scope.redes.push({
									nome : value._id,
									tipo : value.tipo
								});
							});
						} else {
							$scope.redes.push({
								nome : rede._id,
								tipo : rede.tipo
							});
						}

					})

				});

			}

			$scope.confirmaExclusao = function(username) {

			}

		} ]);