package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model;

import br.com.branetlogistica.core.interfaces.IEntity;
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
@Table(name = "configuracao_mercadoria")
public class ConfiguracaoMercadoria implements IEntity {

	public ConfiguracaoMercadoria(Long id) {
		this.id = id;
	}
	
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
	
	@ManyToOne
	@JoinColumn(name = "fabricante_id", nullable = false , referencedColumnName = "id" )
	private PessoaJuridica fabricante;
		
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@Column(name = "codigo_barras_fabricante")
	private String codigoBarrasFabricante;
	
	@Column(name = "registro_avisa")
	private String registroAnvisa;
	

	
}
