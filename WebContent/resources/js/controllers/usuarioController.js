

app.controller('usuarioController', function($scope,$http, $location, $routeParams) {

  //lista os clientes
  $scope.usuario ={};


  //seleciona App no grid e joga na variavel
	$scope.selecionaUsuario = function(usuarioSelecionado){
		
		 $scope.usuario = usuarioSelecionado;
	
   }
	

  $scope.salvarUsuario = function() {
	  
	  if ($scope.usuario.password != $scope.passwordValidation) {
		  alert("Password não confere!");
         
	  } else {
	  
		//limpa o scopo para podet definir os novos campos
		$scope.usuario.imagem ='';		  
		$scope.usuario.imagem = document.getElementById("imagemCliente").getAttribute("src");
		  
	  $http.post("usuarios/salvar", $scope.usuario).success(function(response) {
	  	  //limpa os dados
		  resposta = response;
		  if (resposta==="0") {
			  $scope.usuario ={};
		      $location.path("usuariolist");
		  } else if(resposta==="1") {
			  alert("Username já definido para outro usuário, registro não salvo!")
		  }
		  
		  
	  }).error(function(data, status, headers, config) {
	  	  console.log("Erro : "  + status);
	  });
	  
	  }
	
  }
  


  //busca dos dados do cliente na base para edicao 
  if ($routeParams.id != null && $routeParams.id!= undefined && $routeParams.id !='') {
	  
	  $http.get("usuarios/buscarusuario/"+$routeParams.id).success(function(response) {
			$scope.usuario = response;
			document.getElementById("imagemCliente").src=$scope.usuario.imagem;
		
		}).error(function(data, status, headers, config) {
			console.log(status);
		});
		
		
  } else {
	  
	  $scope.usuario ={};
  }
  
 
  //edita o cadastro de app
  $scope.editarUsuario = function(idUsuario) {
	
	  if ($scope.usuario.id === null || $scope.usuario.id === undefined || $scope.usuario.id ==='') {
		   
		  alert("Selecione um registro para edição!");
		  
	  } else {
		  
	     $location.path("usuarioedit/" + idUsuario);
	    
	  }

  }
	
	
  //listar todos
  $scope.listarUsuarios = function(idStatus) {
	  
	  if (idStatus === null ||idStatus === undefined || idStatus ===''){
		  idStatus='A';
	  }
	  
	$http.get("usuarios/listar/"+idStatus).success(function(response) {
		$scope.data=response;	
	}).error(function(response) {
		console.log(response);
	});
			
			
  };
  
  $scope.cancelarUsuario = function(idUsuario) {
	  
	  if ($scope.usuario.id === null || $scope.usuario.id === undefined || $scope.usuario.id ==='') {
		   
		  alert("Selecione um registro para Cancelamento!");
		  
	  } else {
		  
	  
	
	  $http.delete("usuarios/deletar/"+idUsuario).success(function(response) {
			$scope.listarUsuarios();
			alert("Usuario Desativada com sucesso!");
		}).error(function(data, status, headers, config) {
			console.log(status);
		})
		
	  }
	 
  }
  
  
  //listar todos
  $scope.carregarContas = function(idStatus) {
	  if (idStatus=== undefined || idStatus === '') {
		     idStatus = "A"; 
		  }
	$http.get("contas/listar/"+idStatus).success(function(response) {
		$scope.dataContas=response;	
	}).error(function(response) {
		console.log(response);
	});
		
  };
  
  $scope.validarLogin = function(login) {
	  
       $http.get("usuarios/consultar/"+login).success(function(response) {
	         $scope.loginExiste=response;	
	    
     	}).error(function(data, status, headers, config) {
		console.log(status);
     	});
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



