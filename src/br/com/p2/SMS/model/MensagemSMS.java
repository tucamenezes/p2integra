package br.com.p2.SMS.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.p2.model.Contas;


//@Entity
public class MensagemSMS implements Serializable {
   
 private int id;
 private String celular;
	/*
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_conta",referencedColumnName="id")
	private Contas conta	;
	
	@Column(name="descricao", length=20, nullable=false)  
	private String descricao;
	
	@Column(name="celular", length=20, nullable=false)  
	private String celular;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_recebimento")  
	private String dataRecebimento;
	
	@Column(name="id_original_sms", length=50)  
	private String idOriginalSMS; 
	*/
	
	
	
	
	

	
	
	
	
}
