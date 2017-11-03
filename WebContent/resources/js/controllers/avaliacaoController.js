

app.controller('avaliacaoController', function($scope,$http, $location, $routeParams,$filter) {

  //lista os clientes
  $scope.avaliacao ={};
  $scope.avaliacaoImagem ={};
  $scope.validou = false;
  $scope.edicao = false;
  

  
  //seleciona App no grid e joga na variavel
	$scope.selecionaAvaliacao = function(avaliacaoSelecionado){
		
		 $scope.avaliacao = avaliacaoSelecionado;
	
    }
	
	
	
	
    $scope.validaCliente = function(numCpf){
    
    	
    	if (numCpf != null && numCpf!= undefined && numCpf!='') {
    		
    		 $http.get("avaliacoes/buscarcliente/"+numCpf).success(function(response) {
    				$scope.clienteVeiculo = response;
    				console.log($scope.clienteVeiculo);
    				console.log($scope.clienteVeiculo["0"].nome);
    				
    			 	if ($scope.clienteVeiculo["0"].id != null && $scope.clienteVeiculo["0"].id != undefined && $scope.clienteVeiculo["0"].id !='') {
    			 		
    			 		$scope.avaliacao.cliente= $scope.clienteVeiculo["0"];
    			 		$scope.nomeCliente = $scope.avaliacao.cliente.nome;
        				$scope.validou = true;
        				$scope.edicao = true;
    			 	} else {
    			 		alert('Cliente não encontrado com o cpf selecionado!');
    			 	}
    			
    			}).error(function(data, status, headers, config) {
    				console.log(status);
    				alert('Erro na busca dos dados do Cliente');
    			});
    		 
    	 } else {
    		 alert('Digite o cpf do cliente!');
    	 }
    		 
    	     
	
    }
	
    
    
    
    $scope.formatarData = function(dataStr) {
    	
     	var myDate = new Date(dataStr);
    	    return myDate;
    	   
  	}

  $scope.salvarAvaliacao = function() {
	  
	  
	  $scope.avaliacao.dataAvaliacao = $scope.formatarData($scope.avaliacao.dataAvaliacao);
	  $http.post("avaliacoes/salvar", $scope.avaliacao).success(function(response) {
	  	  //limpa os dados
		  resposta = response;
		  $scope.avaliacao ={};
		  
		  $location.path("avaliacaolist");
  
	  }).error(function(data, status, headers, config) {
	  	  console.log("Erro : "  + status);
	  });
	  
	  
	
  }
  


  //busca dos dados do cliente na base para edicao 
  if ($routeParams.id != null && $routeParams.id!= undefined && $routeParams.id !='') {
	  
	  $http.get("avaliacoes/buscaravaliacao/"+$routeParams.id).success(function(response) {
			$scope.avaliacao = response;
			$scope.validou = true;
			$scope.edicao = true;
			$scope.nomeCliente = $scope.avaliacao.cliente.nome;
			$scope.cpf = $scope.avaliacao.cliente.cpf;
			console.log($scope.cpf);
		
		}).error(function(data, status, headers, config) {
			console.log(status);
		});
		
		
  } else {
	  
	  $scope.avaliacao ={};
  }
  
 
  //edita o cadastro de app
  $scope.editarAvaliacao = function(idAvaliacao) {
	
	  if ($scope.avaliacao.id === null || $scope.avaliacao.id === undefined || $scope.avaliacao.id ==='') {
		   
		  alert("Selecione um registro para edição!");
		  
	  } else {
		  
	     $location.path("avaliacaoedit/" + idAvaliacao);
	  }

  }
	
	
  //listar todos
  $scope.listarAvaliacoes = function(idStatus) {
	  
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
		$http.get("avaliacoes/listar?status="+idStatus+"&dataInicial="+dataInicial+"&dataFinal="+dataFinal).success(function(response) {
			$scope.data=response;	
		}).error(function(response) {
			console.log(response);
		});
    } else {
    	  alert("Defina todos os campos para busca!");
    }
    	
			
			
  };
  
  $scope.cancelarAvaliacao = function(idAvaliacao) {
	  
	  if ($scope.avaliacao.id === null || $scope.avaliacao.id === undefined || $scope.avaliacao.id ==='') {
		   
		  alert("Selecione um registro para Cancelamento!");
		  
	  } else {
		  
	  
	
	  $http.delete("avaliacoes/deletar/"+idAvaliacao).success(function(response) {
			$scope.listarAvaliacoes();
			alert("Avaliação Cancelada com sucesso!");
		}).error(function(data, status, headers, config) {
			console.log(status);
		})
		
	  }
	 
  }
  
 $scope.liberarAvaliacao = function(idAvaliacao) {
	  
	  if ($scope.avaliacao.status === 'L') {
		   
		  alert("Avaliação já liberada!");
		  
	  } else {

	  $http.get("avaliacoes/liberar/"+idAvaliacao).success(function(response) {
			$scope.listarAvaliacoes();
			alert("Avaliação liberada com sucesso!");
			
		}).error(function(data, status, headers, config) {
			console.log(status);
		})
		
	  }
	 
 }
  
  
  //listar todos
  $scope.carregarEmpresas = function() {
	 
    idStatus = "A"; 
	$http.get("empresas/listar/"+idStatus).success(function(response) {
		$scope.dataEmpresas=response;
		console.log($scope.dataEmpresas);
	}).error(function(response) {
		console.log(response);
	});
		
  };
  
  //listar todos
  $scope.carregarVendedores = function() {
	 
    idStatus = "A"; 
	$http.get("vendedores/listar/"+idStatus).success(function(response) {
		$scope.dataVendedores=response;	
	}).error(function(response) {
		console.log(response);
	});
		
  };
  
  //listar todos
  $scope.carregarModelos = function() {
	 
    idStatus = "A"; 
	$http.get("modelos/listar/"+idStatus).success(function(response) {
		$scope.dataModelos=response;	
	}).error(function(response) {
		console.log(response);
	});
		
  };
  
  
//listar todos
  $scope.listarAvaliacoesImagens = function() {
	  
	  $http.get("avaliacoes/buscaravaliacao/"+ $routeParams.idAvaliacao).success(function(response) {
			$scope.avaliacao = response;
			
			//busca dos dados do cliente na base para edicao 
			  if ($scope.avaliacao.id != null && $scope.avaliacao.id!= undefined && $scope.avaliacao.id !='') {
	 
					$http.get("avaliacoesimagens/listar/"+ $scope.avaliacao.id).success(function(response) {
						$scope.dataImagens=response;	
					}).error(function(response) {
						console.log(response);
					});
					
			  } else {
				  
				  $scope.dataImagens={};
				  
			  }  
		
			  
		}).error(function(data, status, headers, config) {
			console.log(status);
		});
	  
	
	  
	  
	
		
  };
  
  
  $scope.salvarImagem = function() {
	  
	  if ($scope.descricao != null && $scope.descricao!= undefined && $scope.descricao !='') {
		 
		  //limpa o scopo para podet definir os novos campos
		  $scope.avaliacaoImagem ={};		  
		  $scope.avaliacaoImagem.imagem = document.getElementById("imagemCliente").getAttribute("src");
		 
		  if ($scope.avaliacaoImagem.imagem != null && $scope.avaliacaoImagem.imagem != undefined && $scope.avaliacaoImagem.imagem !='') {
			  
			  $scope.avaliacaoImagem.descricao = $scope.descricao;
			  $scope.avaliacaoImagem.avaliacao = $scope.avaliacao;
			  $scope.avaliacaoImagem.avaliacao.dataAvaliacao = $scope.formatarData($scope.avaliacaoImagem.avaliacao.dataAvaliacao);
			  
			  $http.post("avaliacoesimagens/salvar", $scope.avaliacaoImagem).success(function(response) {
				  
			  	  //limpa os dados
				  resposta = response;
				  document.getElementById("imagemCliente").src='';
				  $scope.avaliacaoImagem ={};
				  $scope.descricao="";
				  $scope.listarAvaliacoesImagens();
				  //$location.path("avaliacaolist");
		  
			  }).error(function(data, status, headers, config) {
			  	  console.log("Erro : "  + status);
			  });  
		  } else {
			  alert ("Insira uma imagem antes de gravar!");
		  }
  
	  } else {
		  alert ("Insira uma descrição para imagem da avaliação!");
	  }
	
  }
  
   //seleciona App no grid e joga na variavel
	$scope.selecionaAvaliacaoImagem = function(avaliacaoImagemSelecionado){
		
		 $scope.avaliacaoImagem = avaliacaoImagemSelecionado;
	
  } 

 $scope.excluirAvaliacaoImagem = function(idAvaliacaoImagem) {
	  
	  if ($scope.avaliacaoImagem.id === null || $scope.avaliacaoImagem.id === undefined || $scope.avaliacaoImagem.id ==='') {
		   
		  alert("Selecione uma imagem para Exclusão!");
		  
	  } else {
		  
	  
	
	  $http.delete("avaliacoesimagens/deletar/"+idAvaliacaoImagem).success(function(response) {
			$scope.listarAvaliacoesImagens();
			
		}).error(function(data, status, headers, config) {
			console.log(status);
		})
		
	  }
	 
  }
  


});


function visualisarImagem(){
	var count = document.querySelectorAll('img').length;
	var preview = document.querySelectorAll('img').item(count-1);
	var file = document.querySelector('input[type=file]').files[0];
	var reader = new FileReader();
	
	reader.onloadend = function () {
		preview.src = reader.result;
	}
	
	if (file){
		reader.readAsDataURL(file);
	} else {
		preview.src = "";
	}
}





