package br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.model;

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
@Table(name = "pessoa_juridica")
public class PessoaJuridica implements IEntity {

	public PessoaJuridica(Long id) {
		this.id = id;
	}
	
	@Include
	@Id
	private Long id;
	
	@Column(name = "disabled")
	private boolean disabled;
			
	@Column(name = "version_number", nullable = false)
	private Long version;
	
	@Column(name="razao_social")
	private String razaoSocial;
	
	@Column(name="nome_fantasia")
	private String nomeFantasia;
	
	@Column(name="cnpj")
	private String cnpj;

		
}
