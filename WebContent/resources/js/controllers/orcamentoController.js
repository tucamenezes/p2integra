

app.controller('orcamentoController', function($scope,$http, $location, $routeParams) {

  //lista os clientes
  $scope.orcamento ={};

  //seleciona App no grid e joga na variavel
	$scope.selecionaOrcamento = function(orcamentoSelecionada){
		
		 $scope.orcamento = orcamentoSelecionada;
	
   }
	

  $scope.liberarOrcamento = function(idOrcamento) {
	  
	 
	  $http.post("contas/salvar", $scope.conta).success(function(response) {
	  	  //limpa os dados
		  $scope.conta ={};
		  $location.path("contalist");
		  
		  
	  }).error(function(data, status, headers, config) {
	  	  console.log("Erro : "  + status);
	  });
	
  }
  
  
 
  
 
	
  //listar todos
  $scope.listarOrcamentos = function(idStatus) {
	  
	 var podeConsultar=true;
	 var dataInicial = document.getElementById("dataInicial").value;
	 var dataFinal = document.getElementById("dataFinal").value; 
	  
	 
	 if (idStatus === null ||idStatus === undefined || idStatus ===''){
		  idStatus='A';
	  }

    
    if (document.getElementById("statusLib").checked) {
        if (dataInicial === null || dataInicial === undefined || dataInicial ==='' || dataFinal === null || dataFinal === undefined || dataFinal ==='') {
    	       podeConsultar=false;
	    }
    }
    	
  
    if (podeConsultar) {
	    //http://localhost:8080/spring-rest/ex/bars?id=100&second=something
		$http.get("orcamentos/listar?status="+idStatus+"&dataInicial="+dataInicial+"&dataFinal="+dataFinal).success(function(response) {
			$scope.data=response;	
		}).error(function(response) {
			console.log(response);
		});
    } else {
    	  alert("Defina todos os campos para busca!");
    }
    	
			
			
  };
  
  
//listar todos
  $scope.carregarGestores = function(idStatus) {
	  
	  if (idStatus=== undefined || idStatus === '') {
	     idStatus = "A"; 
	  }
	  	  
	$http.get("gestores/listar/"+idStatus).success(function(response) {
		$scope.dataGestores=response;	
	}).error(function(response) {
		console.log(response);
	});
			
			
  };
  
  
});

