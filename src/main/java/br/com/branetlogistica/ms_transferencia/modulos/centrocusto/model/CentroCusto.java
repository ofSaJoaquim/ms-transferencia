package br.com.branetlogistica.ms_transferencia.modulos.centrocusto.model;

import br.com.branetlogistica.core.interfaces.IEntity;
import br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.model.CentroDistribuicao;
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
@Table(name = "centro_custo")
public class CentroCusto implements IEntity {
	
	@Include
	@Id
	private Long id;
	
	@Column(name = "disabled")
	private boolean disabled;
			
	@Column(name = "version_number", nullable = false)
	private Long version;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "usa_estoque")
	private Boolean usaEstoque;
	
	@ManyToOne
	@JoinColumn(name = "centro_distribuicao_id", nullable = false ,referencedColumnName = "id" )
	private CentroDistribuicao centroDistribuicao;
	
	
	
	
	
}
