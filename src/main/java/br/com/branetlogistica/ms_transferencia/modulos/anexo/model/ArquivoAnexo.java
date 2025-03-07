package br.com.branetlogistica.ms_transferencia.modulos.anexo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import br.com.branetlogistica.core.interfaces.IAuditoryEntity;
import br.com.branetlogistica.core.interfaces.IEntity;
import br.com.branetlogistica.core.log.auditory.service.AuditListener;
import br.com.branetlogistica.ms_transferencia.modulos.anexo.enums.ArquivoFormatoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "arquivo_anexo")
@EntityListeners(AuditListener.class)
public class ArquivoAnexo implements IEntity, IAuditoryEntity  {
	
	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "disabled")
	private boolean disabled;
	
	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private Long createdBy;

	@LastModifiedBy
	@Column(name = "updated_by",  updatable = true, insertable = false)
	private Long updatedBy;
	
	@LastModifiedDate
	@Column(name = "updated_at",  updatable = true, insertable = false)
	private LocalDateTime updatedAt;
		
	@Version
	@Column(name = "version_number", nullable = false)
	private Long version;
	
	@Lob
	@Column(name ="dados_codificado", nullable = false)
	private String dadosCodificado;
	
	@Column(name ="nome", nullable = false)
	private String nome;
	
	@Column(name ="formato", nullable = false)
	private ArquivoFormatoEnum formato;

}
	
	
			
