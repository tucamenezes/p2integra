package br.com.p2.controller;




import br.com.p2.dao.DaoInterface;
import br.com.p2.dao.DaoInterfaceImplements;
import br.com.p2.model.AcessosUsuarios;




public class AcessoUsuarioController extends DaoInterfaceImplements<AcessosUsuarios> implements DaoInterface<AcessosUsuarios> {

	public AcessoUsuarioController(Class<AcessosUsuarios> persistenceClass) {
		
		super(persistenceClass);
		
	}
	
	public void salvarApp(AcessosUsuarios userAcess) throws Exception {

			  super.salvarAtualizar(userAcess);

     }
	
}
