package br.com.branetlogistica.ms_transferencia.modulos.endereco.model;

import br.com.branetlogistica.core.interfaces.IEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Table(name = "cidade")
public class Cidade implements IEntity {

	@Include
	@Id
	private Long id;
	
	@Column(name = "disabled")
	private boolean disabled;
			
	@Column(name = "version_number", nullable = false)
	private Long version;
	
	@Column(name= "nome")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "estado_id", nullable = false ,referencedColumnName = "id" )
	private Estado estado;
	
	@Column(name = "cd_ibge")
	private Long ibge;
	
	
	@Transient	
	public Long getCodigoIbgeSemDigito() {
		if(this.ibge == null)
			return null;
		String parse = ibge.toString();
		if(parse.length()>6)
			return Long.valueOf(parse.substring(0, 6));
		return ibge;
	}
	
}
