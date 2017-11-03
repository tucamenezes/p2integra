package br.com.p2.model.avaliacao;

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
@Table(name="avaliacoes_imagens") //indica o nome da tablea
public class AvaliacaoImagens implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@ManyToOne()
	@JoinColumn(name="id_avaliacao",referencedColumnName="id")
	private Avaliacao avaliacao;
	
	@Column(name="descricao", length=50, nullable=false)
	private String descricao;
	
	@Column(name="imagem", columnDefinition="text")
	private String imagem;
	
	public AvaliacaoImagens() {
		
	}

	public AvaliacaoImagens(Long id, Avaliacao avaliacao, String descricao, String imagem) {
		super();
		this.id = id;
		this.avaliacao = avaliacao;
		this.descricao = descricao;
		this.imagem = imagem;
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
		AvaliacaoImagens other = (AvaliacaoImagens) obj;
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

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
