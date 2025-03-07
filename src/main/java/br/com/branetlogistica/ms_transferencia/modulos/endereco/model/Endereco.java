package br.com.branetlogistica.ms_transferencia.modulos.endereco.model;

import br.com.branetlogistica.core.interfaces.IEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
public class Endereco  implements IEntity {

	public Endereco(Long id) {
		this.id = id;
	}
	
	@Include
	@Id
	private Long id;
	
	@Column(name = "disabled")
	private boolean disabled;
			
	@Column(name = "version_number", nullable = false)
	private Long version;
	
	@Column(name = "tipoLogradouro")
	private String tipoLogradouro;
	
	@Column(name = "logradouro")
	private String logradouro;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "complemento")
	private String complemento;
	
	@Column(name = "cep")
	private String cep;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id", nullable = false ,referencedColumnName = "id" )
	private Cidade cidade;
	
	@Column(name = "sem_numero")
	private boolean semNumero;

	
	
	
	
}
