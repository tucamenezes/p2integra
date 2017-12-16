

app.controller('orcamentoController', function($scope,$http, $location, $routeParams) {

  //lista os clientes
  $scope.orcamento ={};

  //seleciona App no grid e joga na variavel
	$scope.selecionaOrcamento = function(orcamentoSelecionada){
		
		 $scope.orcamento = orcamentoSelecionada;
	
   }
	

	
  $scope.liberarOrcamento = function() {
	  
	  if ($scope.orcamento.id === null || $scope.orcamento.id === undefined || $scope.orcamento.id ==='') {
		   
		  alert("Selecione um registro para edição!");
		  
	  }  else {
	  
	 
		  $http.post("orcamentos/liberar/" + $scope.orcamento.id).success(function(response) {
		  	  //limpa os dados
			  $scope.orcamento ={};
			  $location.path("orcamentolist");
			  alert("Orçamento Liberado com sucesso!");
			  $scope.listarOrcamentos();
			  
			  
		  }).error(function(data, status, headers, config) {
		  	  console.log("Erro : "  + status);
		  });
	  
	  }
	
  }
  
  
 
  
 
	
  //listar todos
  $scope.listarOrcamentos = function(idStatus) {
	  
	 var podeConsultar=true;
	 var dataInicial = document.getElementById("dataInicial").value;
	 var dataFinal = document.getElementById("dataFinal").value; 
	 
	  
	 
	 if (idStatus === null ||idStatus === undefined || idStatus ===''){
		  idStatus='A';
	  }
	 
	 if (document.getElementById("statusLib").checked && document.getElementById("filtarPeriodo").checked) {
		 idStatus = 'L' 
	 }
    
    if (document.getElementById("filtarPeriodo").checked) {
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

//listar documento pdf
function imprimirOrcamento() {
	
	
	  var podeConsultar=true;
	  var dataInicial = document.getElementById("dataInicial").value;
	  var dataFinal = document.getElementById("dataFinal").value; 
	  var idStatus ;
	 
	  
	  if (document.getElementById("statusAvaliacao").checked){
		  idStatus='L';
	  } else {
		  idStatus='A'
	  }

 
      if (dataInicial === null || dataInicial === undefined || dataInicial ==='' || dataFinal === null || dataFinal === undefined || dataFinal ==='') {
  	       podeConsultar= false;
	    }
  	

  if (podeConsultar) {
	 // alert("imprimirAuditoria/orcamento?status="+idStatus+"&dataInicial="+dataInicial+"&dataFinal="+dataFinal);
	  
	    document.location.href="imprimirAuditoria/orcamento?status="+idStatus+"&dataInicial="+dataInicial+"&dataFinal="+dataFinal;	
  } else {
  	  alert("Defina todos os campos para busca!");
  }
  	
			
			
};

