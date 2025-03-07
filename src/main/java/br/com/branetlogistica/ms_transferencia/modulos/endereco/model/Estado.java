package br.com.branetlogistica.ms_transferencia.modulos.endereco.model;

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
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estado")
public class Estado implements IEntity {

	@Include
	@Id
	private Long id;
	
	@Column(name = "disabled")
	private boolean disabled;
			
	@Column(name = "version_number", nullable = false)
	private Long version;
	
	@Column(name = "uf")
	private String uf;
	
	@Column(name="nome")
	private String nome;	
	
	@Column(name="cd_ibge")
	private Long ibge;

	
	
}
