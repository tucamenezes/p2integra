package br.com.p2.model.veiculos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.p2.model.Contas;

@Entity
@Table(name="cliente_veiculo") //indica o nome da tablea
public class ClienteVeiculo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_conta",referencedColumnName="id")
	private Contas conta	;
	
	@Column(name="nome", length=50, nullable=false)
	private String nome;
	
	@Column(name="cpf")
	private Long cpf;
	
	@Column(name="telefone", length=30, nullable=false)
	private String telefone;
	
	@Column(name="email", length=30, nullable=false)
	private String email;
	
	
	@Column(name="status", length=2)	
	private String status;
	
	public ClienteVeiculo() {
		// TODO Auto-generated constructor stub
	}

	

	public ClienteVeiculo(Long id,Contas conta, String nome, Long cpf, String telefone, String email, String status) {
		super();
		this.id = id;
		this.conta = conta;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.status = status;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteVeiculo other = (ClienteVeiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}


    public Contas getConta() {
		return conta;
	}
    
    public void setConta(Contas conta) {
		this.conta = conta;
	}
    
	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}
	
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}


	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
	
	

}
