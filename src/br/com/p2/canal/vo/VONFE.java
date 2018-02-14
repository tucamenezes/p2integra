package br.com.p2.canal.vo;

import java.io.Serializable;

public class VONFE implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idNFE;
	
	private Long idNFEExterno;
	
	//private Long idTransacao;

	public VONFE(Long idNFE, Long idNFEExterno, Long idTransacao) {
	
		this.idNFE = idNFE;
		this.idNFEExterno = idNFEExterno;
		//this.idTransacao = idTransacao;
	}

	
	
	public Long getIdNFE() {
		return idNFE;
	}

	public void setIdNFE(Long idNFE) {
		this.idNFE = idNFE;
	}

	public Long getIdNFEExterno() {
		return idNFEExterno;
	}

	public void setIdNFEExterno(Long idNFEExterno) {
		this.idNFEExterno = idNFEExterno;
	}


	
	

}
