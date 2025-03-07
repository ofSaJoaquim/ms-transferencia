package br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.model;

import br.com.branetlogistica.core.interfaces.IEntity;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.model.Endereco;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.model.PessoaJuridica;
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
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "centro_distribuicao")
public class CentroDistribuicao implements IEntity {
	
	public CentroDistribuicao(Long id) {
		this.id = id;
	}
	
	@Include
	@Id
	private Long id;
	
	@Column(name = "disabled")
	private boolean disabled;
			
	@Column(name = "version_number", nullable = false)
	private Long version;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "codigo_cnes")
	private String codigoCnes;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_juridica_id", nullable = false ,referencedColumnName = "id" )
	private PessoaJuridica pessoaJuridica;
	
	@ManyToOne
	@JoinColumn(name = "endereco_id", referencedColumnName = "id" )
	private Endereco endereco;
	
}
