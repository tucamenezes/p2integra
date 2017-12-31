

app.controller('propostaController', function($scope,$http, $location, $routeParams) {

  //lista 
  $scope.proposta ={};

  //seleciona no grid e joga na variavel
	$scope.selecionaProposta = function(propostaSelecionada){
		
		 $scope.proposta = propostaSelecionada;
	
   }
	

	
  $scope.liberarProposta = function() {
	  
	  if ($scope.proposta.id === null || $scope.proposta.id === undefined || $scope.proposta.id ==='') {
		   
		  alert("Selecione um registro!");
		  
	  }  else {
	  
	 
		  $http.post("propostas/liberar/" + $scope.proposta.id).success(function(response) {
		  	  //limpa os dados
			  $scope.proposta ={};
			  $location.path("propostaslist");
			  alert("Proposta Liberado com sucesso!");
			  $scope.listarPropostas();
			  
			  
		  }).error(function(data, status, headers, config) {
		  	  console.log("Erro : "  + status);
		  });
	  
	  }
	
  }
  
  
 
  
 
	
  //listar todos
  $scope.listarPropostas = function(idStatus) {
	  
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
	   
		$http.get("propostas/listar?status="+idStatus+"&dataInicial="+dataInicial+"&dataFinal="+dataFinal).success(function(response) {
			$scope.data=response;	
		}).error(function(response) {
			console.log(response);
		});
    } else {
    	  alert("Defina todos os campos para busca!");
    }
    	
			
			
  };
  
  
//listar todos
  $scope.carregarVendedores = function(idStatus) {
	  
	  if (idStatus=== undefined || idStatus === '') {
	     idStatus = "A"; 
	  }
	  	  
	$http.get("vendedores/listar/"+idStatus).success(function(response) {
		$scope.dataVendedores=response;	
	}).error(function(response) {
		console.log(response);
	});
			
			
  };
  
  
});

//listar documento pdf
function imprimirAuditoriaProposta() {
	
	
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
	  
	    document.location.href="imprimirAuditoria/desconto?status="+idStatus+"&dataInicial="+dataInicial+"&dataFinal="+dataFinal;	
  } else {
  	  alert("Defina todos os campos para busca!");
  }
  	
			
			
};

