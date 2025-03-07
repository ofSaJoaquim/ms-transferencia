package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model;

import java.time.LocalDate;

import br.com.branetlogistica.core.interfaces.IEntity;
import br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.model.CentroDistribuicao;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lote")
public class Lote implements IEntity {

	@Include
	@Id
	private Long id;
	
	@Column(name = "disabled")
	private boolean disabled;
			
	@Column(name = "version_number", nullable = false)
	private Long version;
	
	@ManyToOne
	@JoinColumn(name = "mercadoria_id", nullable = false ,referencedColumnName = "id" )
	private Mercadoria mercadoria;
	
	@Column(name="valor_unitario")
	private Double valorUnitario;
	
	@ManyToOne
//	@JoinColumn(name = "fornecedor_id", nullable = false ,referencedColumnName = "id")
	@JoinColumn(name = "fornecedor_id", nullable = true ,referencedColumnName = "id") // TODO: O FORNECEDOR PODE SER NULO MESMO?!
	private PessoaJuridica fornecedor;
	
	@ManyToOne
	@JoinColumn(name = "centro_distribuicao_id", nullable = false ,referencedColumnName = "id")
	private CentroDistribuicao centroDistribuicao;
	
	@ManyToOne
	@JoinColumn(name = "configuracao_mercadoria_id", nullable = false ,referencedColumnName = "id" )
	private ConfiguracaoMercadoria configuracaoMercadoria;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="validade")
	private LocalDate validade;
	
	@Column(name="id_codigo_barras")
	private Long idCodigoBarras;
		
	@Column(name="id_lote_marca")
	private Long idLoteMarca;
	
	@Column(name="possue_entrada")
	private boolean possueEntrada;

	
}
