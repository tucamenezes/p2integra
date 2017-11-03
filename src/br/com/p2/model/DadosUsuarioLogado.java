package br.com.p2.model;

import java.io.Serializable;

public class DadosUsuarioLogado implements Serializable {

	private static final long serialVersionUID = 1L;
    
	private Usuarios usuario;
	
	private Empresas empresa;
	
	
	public DadosUsuarioLogado() {
		// TODO Auto-generated constructor stub
	}
	
	

	public DadosUsuarioLogado(Usuarios usuario, Empresas empresa) {
		super();
		this.usuario = usuario;
		this.empresa = empresa;
	}



	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Empresas getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresas empresa) {
		this.empresa = empresa;
	}
	
	
	
	
}
