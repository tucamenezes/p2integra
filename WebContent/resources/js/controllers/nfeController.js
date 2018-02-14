

app.controller('nfeController', function($scope,$http, $location, $routeParams) {

  //lista os clientes
  $scope.nfe ={};

  //seleciona App no grid e joga na variavel
	$scope.selecionaNfe = function(nfeSelecionada){
		
		 $scope.nfe = nfeSelecionada;
	
   }
	

  //listar todos
  $scope.listarNfe = function(idFornecedor, idEmpresa) {
	  
	  //alert(idFornecedor);
	 var podeConsultar=true;
	 var dataInicial = document.getElementById("dataInicial").value;
	 var dataFinal = document.getElementById("dataFinal").value; 
	 
	  
	 
	 if (idFornecedor === null ||idFornecedor === undefined || idFornecedor ===''){
		 idFornecedor='0';
	  }
	 
	 if (idEmpresa === null ||idEmpresa === undefined || idEmpresa ===''){
		 idEmpresa='0';
	  }
    	
	 if (dataInicial === null ||dataInicial === undefined || dataInicial ===''){
		 podeConsultar=false;
	  }
	 
	 if (dataFinal === null ||dataFinal === undefined || dataFinal ===''){
		 podeConsultar=false;
	  }
  
    if (podeConsultar) {
	    //http://localhost:8080/spring-rest/ex/bars?id=100&second=something
		$http.get("nfe/listar?idFornecedor="+idFornecedor+"&dataInicial="+dataInicial+"&dataFinal="+dataFinal+"&idEmpresa="+idEmpresa).success(function(response) {
			$scope.data=response;
			console.log($scope.data)
		}).error(function(response) {
			console.log(response);
		});
    } else {
    	  alert("Defina todos os campos para busca!");
    }
    	
			
			
  };
  
  
  //listar todos
  $scope.carregarFornecedores = function() {
	   
		$http.get("nfe/listarFornecedores").success(function(response) {
			$scope.dataFornecedores=response;	
		}).error(function(response) {
			console.log(response);
		});
		
  };
  
  
  //listar todos
  $scope.carregarEmpresas = function() {
	   
		$http.get("empresas/listarempresas").success(function(response) {
			$scope.dataEmpresas=response;	
		}).error(function(response) {
			console.log(response);
		});
		
  };
  
  
});

//listar documento pdf
function downloadNFE() {
	
	 var listaDownload = [];
	 var id = 0;
	 var listaNfe = document.getElementsByName("nfes");
	 
	 for (var i = 0; i < listaNfe.length; i++){
          if (listaNfe[i].checked) {
        	     id=listaNfe[i].value;
             listaDownload.push(id);
          }
	 }
	 // alert("nfe/download?idnfe="+idNfe);
	   console.log(listaDownload);
	  
	    //document.location.href="nfe/downloadxml?idNfe=1";	
	   
	 document.location.href="nfe/downloadxml?idNfe="+listaDownload;	
 
  	
			
			
};

