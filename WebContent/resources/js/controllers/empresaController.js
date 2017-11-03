

app.controller('empresaController', function($scope,$http, $location, $routeParams) {

  //lista os clientes
  $scope.empresa ={};

  //seleciona App no grid e joga na variavel
	$scope.selecionaEmpresa = function(empresaSelecionada){
		
		 $scope.empresa = empresaSelecionada;
	
   }
	

  $scope.salvarEmpresa = function() {
	  
	 
	  $http.post("empresas/salvar", $scope.empresa).success(function(response) {
	  	  //limpa os dados
		  $scope.empresa ={};
		  $location.path("empresalist");
		  
		  
	  }).error(function(data, status, headers, config) {
	  	  console.log("Erro : "  + status);
	  });
	
  }
  
  
  //busca dos dados do cliente na base para edicao 
  if ($routeParams.id != null && $routeParams.id!= undefined && $routeParams.id !='') {
	  
	  $http.get("empresas/buscarempresa/"+$routeParams.id).success(function(response) {
			$scope.empresa = response;
		
			//-------
			setTimeout(function () {
				
				$("#selectEstados").prop ('selectedIndex', (new Number($scope.empresa.estados.id)+1));
				
				
				$http.get("cidades/listar/" + $scope.empresa.estados.id).success(function(response) {
				     $scope.dataCidades = response;
				     setTimeout(function () {
							$("#selectCidades").prop ('selectedIndex', buscarKeyJson(response, 'id',$scope.empresa.cidades.id));
				     }, 100);	
				     
		     	}).error(function(data, status, headers, config) {
			         erro("Error: " + status);
			    }); 
		   
	
			}, 100);
			//------
		}).error(function(data, status, headers, config) {
			console.log(status);
		});
		
		
  } else {
	  $scope.empresa ={};
  }
  
 
  //edita o cadastro de app
  $scope.editarEmpresa = function(idEmpresa) {
	
	  if ($scope.empresa.id === null || $scope.empresa.id === undefined || $scope.empresa.id ==='') {
		   
		  alert("Selecione um registro para edição!");
		  
	  } else {
		  
	     $location.path("empresaedit/" + idEmpresa);
	  }

  }
	
	
  //listar todos
  $scope.listarEmpresas = function(idStatus) {
	//alert('Entrei :' + idStatus);
	  if (idStatus=== undefined || idStatus === '') {
		     idStatus = "A"; 
		  }
	  
	$http.get("empresas/listar/"+idStatus).success(function(response) {
		$scope.data=response;	
	}).error(function(response) {
		console.log(response);
	});
			
			
  };
  
  $scope.cancelarEmpresa = function(idEmpresa) {
	  
	  if ($scope.empresa.id === null || $scope.empresa.id === undefined || $scope.empresa.id ==='') {
		   
		  alert("Selecione um registro para Cancelamento!");
		  
	  } else {
		  
	  
	
	  $http.delete("empresas/deletar/"+idEmpresa).success(function(response) {
			$scope.listarEmpresas();
			alert("Empresa Desativada com sucesso!");
		}).error(function(data, status, headers, config) {
			console.log(status);
		})
		
	  }
	 
  }
  
  $scope.carregarEstados= function() {
	  $scope.dataEstados={};
	  
	  $http.get("estados/listar").success(function(response) {
			$scope.dataEstados=response;	
		}).error(function(response) {
			console.log(response);
		});
   };
  
   
   $scope.carregarContas= function() {
        idStatus = "A"; 
		$scope.dataContas={};
		
		$http.get("contas/listar/"+idStatus).success(function(response) {
		    $scope.dataContas=response;	
		}).error(function(response) {
			console.log(response);
		});
	   };
   
   
    // carrega as cidades de acordo com o estado passado por parametro
	$scope.carregarCidades = function(estado) {
		
		if (identific_nav() != 'chrome') {// executa se for diferente do chrome
			$http.get("cidades/listar/" + estado.id).success(function(response) {
				$scope.dataCidades = response;
			}).error(function(data, status, headers, config) {
				erro("Error: " + status);
			});
	  }
	};
   
   


  
});


//carregar cidades quando � navegador chrome usando jQuery
function carregarCidadesChrome(estado) {
	if (identific_nav() === 'chrome') {// executa se for chrome
		$.get("cidades/listarchrome", { idEstado : estado.value}, function(data) {
			 var json = JSON.parse(data);
			 html = '<option value="">--Selecione--</option>';
			 for (var i = 0; i < json.length; i++) {
				  html += '<option value='+json[i].id+'>'+json[i].nome+'</option>';
			 }
			 $('#selectCidades').html(html);
		});
  }
}

//identificar navegador
function identific_nav(){
    var nav = navigator.userAgent.toLowerCase();
    if(nav.indexOf("msie") != -1){
       return browser = "msie";
    }else if(nav.indexOf("opera") != -1){
    	return browser = "opera";
    }else if(nav.indexOf("mozilla") != -1){
        if(nav.indexOf("firefox") != -1){
        	return  browser = "firefox";
        }else if(nav.indexOf("firefox") != -1){
        	return browser = "mozilla";
        }else if(nav.indexOf("chrome") != -1){
        	return browser = "chrome";
        }
    }else{
    	alert("Navegador desconhecido!");
    }
}

//faz a identificao da posicao correta da cidade do registro para mostrar em edicao
function buscarKeyJson(obj, key, value)
{
    for (var i = 0; i < obj.length; i++) {
        if (obj[i][key] == value) {
            return i + 2;
        }
    }
    return null;
}
