var app = angular.module ('loja',['ngRoute']);



app.config(['$routeProvider', function($routeProvider) {
	
  $routeProvider.when("/",{controller:"listController",templateUrl:"list.html"})//listar
          .when("/edit/:name",{controller:'editController', templateUrl:'form.html'}) //listar
	      .when("/new",{controller:'newController', templateUrl:'form.html'}) //listar
	      .otherwise({redirectTo: "/"})
}]);


app.run (function($rootScope) {
	$rootScope.frutas = ['banana', 'abacaxi','maca']
});

app.controller('listController',['$scope','$routeParams', '$rootScope', '$route', '$location', function($scope,$routeParams, $rootScope, $route, $location) {
	

}]);


app.controller('editController',['$scope','$routeParams', '$rootScope', '$route', '$location', function($scope,$routeParams, $rootScope, $route, $location) {
	
	$scope.title = "Editar Frutas";
	$scope.fruta = $routeParams.name;
	$scope.frutaIndex = $scope.frutas.indexOf($scope.fruta);
	
	$scope.salvar = function() {
		$scope.frutas[$scope.frutaIndex]=$scope.fruta;
		location.path("/");
		
	}
}]);

app.controller('newController',['$scope','$routeParams', '$rootScope', '$route', '$location', function($scope,$routeParams, $rootScope, $route, $location) {
	
	$scope.title = "Nova Fruta";
	$scope.fruta = "";
	
	$scope.salvar = function() {
		$scope.frutas.push($scope.fruta);
		location.path("/");
		
	}
}]);






