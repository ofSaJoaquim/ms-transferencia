package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model;

import br.com.branetlogistica.core.interfaces.IEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "mercadoria")
public class Mercadoria implements IEntity  {
	
	public Mercadoria(Long id) {
		this.id = id;
	}
	
	@Include
	@Id
	private Long id;
	
	@Column(name = "disabled")
	private boolean disabled;
			
	@Column(name = "version_number", nullable = false)
	private Long version;

	@Column(name="nome", columnDefinition="text")
	private String nome;
	
	@Column(name="codigoCliente", length = 1024)
	private String codigoCliente;
	
	@Column(name="registro_anvisa")
	private String registroAnvisa;
	
	@Column(name="exporta_horus")
	private boolean exportaHorus;
	
	@Column(name="numero_catmat")
	private String numeroCATMAT;
	

	
}
