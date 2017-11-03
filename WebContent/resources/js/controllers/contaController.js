

app.controller('contaController', function($scope,$http, $location, $routeParams) {

  //lista os clientes
  $scope.conta ={};

  //seleciona App no grid e joga na variavel
	$scope.selecionaConta = function(contaSelecionada){
		
		 $scope.conta = contaSelecionada;
	
   }
	

  $scope.salvarConta = function() {
	  
	 
	  $http.post("contas/salvar", $scope.conta).success(function(response) {
	  	  //limpa os dados
		  $scope.conta ={};
		  $location.path("contalist");
		  
		  
	  }).error(function(data, status, headers, config) {
	  	  console.log("Erro : "  + status);
	  });
	
  }
  
  
  //busca dos dados do cliente na base para edicao 
  if ($routeParams.id != null && $routeParams.id!= undefined && $routeParams.id !='') {
	  
	  $http.get("contas/buscarconta/"+$routeParams.id).success(function(response) {
			$scope.conta = response;
		}).error(function(data, status, headers, config) {
			console.log(status);
		})
		
		
  } else {
	  $scope.conta ={};
  }
  
 
  //edita o cadastro de app
  $scope.editarConta = function(idConta) {
	
	  if ($scope.conta.id === null || $scope.conta.id === undefined || $scope.conta.id ==='') {
		   
		  alert("Selecione um registro para edição!");
		  
	  } else {
		  
	     $location.path("contaedit/" + idConta);
	  }

  }
	
	
  //listar todos
  $scope.listarContas = function(idStatus) {
	  if (idStatus=== undefined || idStatus === '') {
	     idStatus = "A"; 
	  }
	$http.get("contas/listar/"+idStatus).success(function(response) {
		$scope.data=response;	
	}).error(function(response) {
		console.log(response);
	});
			
			
  };
  
  $scope.removerConta= function(idConta) {
	  
	  if ($scope.conta.id === null || $scope.conta.id === undefined || $scope.conta.id ==='') {
		   
		  alert("Selecione um registro para edição!");
		  
	  } else {
		  
	  
	
	  $http.delete("contas/deletar/"+idConta).success(function(response) {
			$scope.listarContas();
			alert("Conta Cancelada com sucesso!");
		}).error(function(data, status, headers, config) {
			console.log(status);
		})
		
	  }
	 
  }
  
  
});

