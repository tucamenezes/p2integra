

app.controller('appContaController', function($scope,$http, $location, $routeParams) {

  //lista os clientes
  $scope.appConta ={};


  //seleciona App no grid e joga na variavel
	$scope.selecionaAppConta = function(appContaSelecionado){
		
		 $scope.appConta = appContaSelecionado;
	
   }
	
  $scope.cancelarOperacao = function() {
	  $location.path("appcontalist");
  }
  $scope.salvarAppConta = function() {
	 
	    
	  $http.post("appconta/salvar", $scope.appConta).success(function(response) {
	  	  //limpa os dado
		      resposta = response;
		      if (resposta==="0") {
		    	     $scope.appConta ={};
			     $location.path("appcontalist");
			  } else if(resposta==="1") {
				  alert("App já liberado para esta conta, registro não salvo!")
			  }
			
		      
		     
 
	  }).error(function(data, status, headers, config) {
	  	  console.log("Erro : "  + status);
	  });
	  
  }
  


  

	
  //listar todos
  $scope.listarAppConta = function() {

	  
	$http.get("appconta/listar").success(function(response) {
		$scope.data=response;	
	}).error(function(response) {
		console.log(response);
	});
			
			
  };
  
  $scope.excluirAppConta = function(idAppConta) {
	  
	  if ($scope.appConta.id === null || $scope.appConta.id === undefined || $scope.appConta.id ==='') {
		   
		  alert("Selecione um registro para Exclusão!");
		  
	  } else {
		  
	  
	
	  $http.delete("appconta/deletar/"+idAppConta).success(function(response) {
			$scope.listarAppConta();
			
		}).error(function(data, status, headers, config) {
			console.log(status);
		})
		
	  }
	 
  }
  
  
  //listar todos
  $scope.carregarContas = function() {
	  
	$http.get("contas/listar/A").success(function(response) {
		$scope.dataContas=response;	
	}).error(function(response) {
		console.log(response);
	});
		
  };
  
  $scope.carregarApp = function() {
	  
		$http.get("app/listar/A").success(function(response) {
			$scope.dataApps=response;	
		}).error(function(response) {
			console.log(response);
		});
			
	  };
  


});




