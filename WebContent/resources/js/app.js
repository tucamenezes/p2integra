var app = angular.module ('p2app',['ngRoute','ngResource','ngAnimate']);





app.config(['$routeProvider', function($routeProvider,$provide, $httpProvide, $locationProvider) {
	
  $routeProvider.when("/",      {controller:"appController",templateUrl:"principal.html"})//listar
          .when("/applist",     {controller:"appController",templateUrl:"app/list.html"})//listar
          .when("/appedit/:id", {controller:"appController", templateUrl:'app/cadastro.html'}) //editar
	      .when("/app/cadastro",{controller:"appController", templateUrl:'app/cadastro.html'}); //cadastrar
	      	
	      //-------------Contas--------------
  
  $routeProvider.when("/contalist",      {controller : "contaController",templateUrl :"cadastros/conta/list.html"})// listar
			    .when("/contaedit/:id",  {controller : "contaController",templateUrl : "cadastros/conta/cadastro.html"})// editar
			    .when("/conta/cadastro", {controller : "contaController",templateUrl : "cadastros/conta/cadastro.html"});// novo
			
         //-------------Empresas--------------
  
  $routeProvider.when("/empresalist",      {controller : "empresaController",templateUrl :"cadastros/empresa/list.html"})// listar
			    .when("/empresaedit/:id",  {controller : "empresaController",templateUrl : "cadastros/empresa/cadastro.html"})// editar
			    .when("/empresa/cadastro", {controller : "empresaController",templateUrl : "cadastros/empresa/cadastro.html"})// novo
			    
         //-------------Usuarios--------------
  
  $routeProvider.when("/usuariolist",      {controller : "usuarioController",templateUrl :"cadastros/usuario/list.html"})// listar
			    .when("/usuarioedit/:id",  {controller : "usuarioController",templateUrl : "cadastros/usuario/cadastro.html"})// editar
			    .when("/usuario/cadastro", {controller : "usuarioController",templateUrl : "cadastros/usuario/cadastro.html"})// novo			    
	
       //-------------Contas APP --------------
  
  $routeProvider.when("/appcontalist",      {controller : "appContaController",templateUrl :"cadastros/appconta/list.html"})// listar
			    .when("/appcontaedit/:id",  {controller : "appContaController",templateUrl : "cadastros/appconta/cadastro.html"})// editar
			    .when("/appconta/cadastro", {controller : "appContaController",templateUrl : "cadastros/appconta/cadastro.html"})// novo		
			    
     //-------------Avaliacoes --------------
  
  $routeProvider.when("/avaliacaolist",      {controller : "avaliacaoController",templateUrl : "produtos/avaliacoes/list.html"})// listar
			    .when("/avaliacaoedit/:id",  {controller : "avaliacaoController",templateUrl : "produtos/avaliacoes/cadastro.html"})// editar
			    .when("/avaliacao/cadastro", {controller : "avaliacaoController",templateUrl : "produtos/avaliacoes/cadastro.html"})// novo	
			    .when("/imagenslist/:idAvaliacao" , {controller : "avaliacaoController",templateUrl : "produtos/avaliacoes/imagens/list.html"})// novo
		    
     //-------------Orcamentos --------------
  
   $routeProvider.when("/orcamentolist",      {controller : "orcamentoController",templateUrl : "produtos/orcamentos/list.html"})// listar		
  
    //-------------Descontos de Novos --------------
   
   $routeProvider.when("/propostaslist",      {controller : "propostaController",templateUrl : "produtos/propostas/list.html"})// listar		
  
   //-------------Auditorias  --------------
   $routeProvider.when("/auditoriaavalia",       {controller : "avaliacaoController",templateUrl : "produtos/avaliacoes/auditoria.html"})// avaliacao
                 .when("/auditoriaorcamento",    {controller : "orcamentoController",templateUrl : "produtos/orcamentos/auditoria.html"}) //orcamento
			    .when("/auditoriadesconto",      {controller : "propostaController",templateUrl : "produtos/propostas/auditoria.html"})// novo	
			  	    
	      .otherwise({redirectTo: "/"});
}]);



app.controller('appController', function($scope,$http, $location, $routeParams) {

  //lista os clientes
  $scope.app ={};

  
  
  
  //seleciona App no grid e joga na variavel
	$scope.selecionaApp = function(appSelecionado){
		
		 $scope.app = appSelecionado;
	
   }
	

  $scope.salvarApp = function() {
	  
	  $http.post("app/salvar", $scope.app).success(function(response) {
	  	  //limpa os dados
		  $scope.app ={};
		  $location.path("applist");
		  
		  
	  }).error(function(data, status, headers, config) {
	  	  console.log("Erro : "  + status);
	  });
	
  }
  
$scope.imprimirAuditoriaAvalia = function() {
	 alert();
	 
	 $http.get("imprimirAuditoria/avalia?dataInicial=01/01/2010&dataFinal=01/01/2020").success(function(response) {
			
		}).error(function(data, status, headers, config) {
			console.log(status);
		})
	
  }
  
  
  //busca dos dados do cliente na base para edicao 
  if ($routeParams.id != null && $routeParams.id!= undefined && $routeParams.id !='') {
	  
	  $http.get("app/buscarapp/"+$routeParams.id).success(function(response) {
			$scope.app = response;
		}).error(function(data, status, headers, config) {
			console.log(status);
		})
		
		
  } else {
	  $scope.app ={};
  }
  
 
  //edita o cadastro de app
  $scope.editarApp = function(idApp) {
	
	  
	  $location.path("appedit/" + idApp);
	  

  }
	
	
  //listar todos
  $scope.listarApps = function(idStatus) {
	  idStatus = "A";
	$http.get("app/listar/"+  idStatus).success(function(response) {
		$scope.data=response;	
	}).error(function(response) {
		console.log(response);
	});
			
			
  };
  
  $scope.removerApp= function(idApp) {
	  
	  if ($scope.app.id === null || $scope.app.id === undefined || $scope.app.id ==='') {
		   
		  alert("Selecione um registro para edição!");
		  
	  } else {
		  
	  
	
	  $http.delete("app/deletar/"+idApp).success(function(response) {
			$scope.listarApps();
			alert("App removido com sucesso");
		}).error(function(data, status, headers, config) {
			console.log(status);
		})
		
	  }
	 
  }
  
  
  
});

