

app.controller('indexController', function($scope,$http, $location, $routeParams) {

  //lista os clientes
  $scope.opcoes ={};

 
	
  //listar todos
  $scope.buscarAcesso = function() {

	$http.get("appconta/buscarapps").success(function(response) {
		$scope.opcoes=response;	
	}).error(function(response) {
		console.log(response);
	});
			
			
  };
  
  //listar todos
  $scope.buscarUsuarioLogado = function() {

	$http.get("appconta/buscaruserlogado").success(function(response) {
		$scope.userLogado=response;	
		 if ($scope.userLogado.imagem != null && $scope.userLogado.imagem!= undefined && $scope.userLogado.imagem !='') {
		     document.getElementById("imageCicle").src=$scope.userLogado.imagem;
		     document.getElementById("imageUserLogado").src=$scope.userLogado.imagem;
		 }
	}).error(function(response) {
		console.log(response);
	});
			
			
  };
  
  
  $scope.verificarAcesso = function (idAcesso) {
	  liberarAcesso= false;
	  
	  for(i in $scope.opcoes) {
	  
	       if ($scope.opcoes[i].id===idAcesso) {
	    	       liberarAcesso= true;
	       }
	  }
	  
	  return liberarAcesso;
	  
  }
  
  
  $scope.getNumeroPropostas = function() {
		 
		$http.get("propostas/qtdePendente").success(function(response) {
			$scope.dataQtdePropostaPendente=response;	
		}).error(function(response) {
			console.log(response);
		});
		
	  };
	  
  $scope.getNumeroOrcamentos = function() {
			 
			$http.get("orcamentos/qtdePendente").success(function(response) {
				$scope.dataQtdeOrcamentoPendente=response;	
			}).error(function(response) {
				console.log(response);
			});
			
   };
   
   $scope.getNumeroAvaliacoes = function() {
		 
		$http.get("avaliacoes/qtdePendente").success(function(response) {
			$scope.dataQtdeAvaliacaoPendente=response;	
		}).error(function(response) {
			console.log(response);
		});
				
		
		
	  };
	  
	  $scope.getDadosQtdeServicos = function() {
			 
		  $scope.getNumeroAvaliacoes();
		  $scope.getNumeroOrcamentos();
		  $scope.getNumeroPropostas();
			
			
      };
	  


});

