package br.com.p2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="contas_app") //indica o nome da tablea
public class ContasApps implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	
	@ManyToOne()
	@JoinColumn(name="id_conta",referencedColumnName="id")
	private Contas conta;
	
	@ManyToOne()
	@JoinColumn(name="id_app",referencedColumnName="id")
	private App app;
	
	
	
	public ContasApps() {
		// TODO Auto-generated constructor stub
	}



	public ContasApps(Long id, Contas conta, App app) {
		super();
		this.id = id;
		this.conta = conta;
		this.app = app;
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
		ContasApps other = (ContasApps) obj;
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



	public App getApp() {
		return app;
	}



	public void setApp(App app) {
		this.app = app;
	}
	
	
	
	

}
