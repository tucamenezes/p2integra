var app = angular.module ('loja',[]);



app.config(function($routerProvider) {
	
  $routerProvider
          .when("/",{controller:'listController', template:'list.html'}) //listar
          .when("/edit/:name",{controller:'editController', template:'form.html'}) //listar
	      .when("/new",{controller:'newController', template:'form.html'}) //listar
	      .otherwise({redirectTo: "/"})
});


app.run (function($rootScope) {
	$rootScope.frutas = ['banana', 'abacaxi','maca']
});

app.controller('listController',['$scope', function($scope) {
	

}]);


app.controller('editController',['$scope', function($scope,$location,$routeParams) {
	
	$scope.title = "Editar Frutas";
	$scope.fruta = $routeParams.name;
	$scope.frutaIndex = $scope.frutas.indexOf($scope.fruta);
	
	$scope.salvar = function() {
		$scope.frutas[$scope.frutaIndex]=$scope.fruta;
		location.path("/");
		
	}
}]);

app.controller('newController',['$scope', function($scope,$location) {
	
	$scope.title = "Nova Fruta";
	$scope.fruta = "";
	
	$scope.salvar = function() {
		$scope.frutas.push($scope.fruta);
		location.path("/");
		
	}
}]);


