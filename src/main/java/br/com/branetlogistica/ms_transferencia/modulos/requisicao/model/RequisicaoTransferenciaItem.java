package br.com.branetlogistica.ms_transferencia.modulos.requisicao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import br.com.branetlogistica.core.interfaces.IAuditoryEntity;
import br.com.branetlogistica.core.interfaces.IEntity;
import br.com.branetlogistica.core.log.auditory.service.AuditListener;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model.Mercadoria;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.model.PessoaJuridica;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.enums.RequisicaoTransferenciaItemStatusEnum;
import br.com.branetlogistica.ms_transferencia.modulos.usuario.model.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requisicao_transferencia_item")
@EntityListeners(AuditListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RequisicaoTransferenciaItem implements IEntity, IAuditoryEntity {

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "disabled", nullable = false)
	private boolean disabled;
	
	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private Long createdBy;
	
	@LastModifiedDate
	@Column(name = "updated_at",  updatable = true, insertable = false)
	private LocalDateTime updatedAt;
	
	@LastModifiedBy
	@Column(name = "updated_by",  updatable = true, insertable = false)
	private Long updatedBy;
		
	@Version
	@Column(name = "version_number", nullable = false)
	private Long version;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private RequisicaoTransferenciaItemStatusEnum status;
	
	@ManyToOne
	@JoinColumn(name = "requisicao_transferencia_id", referencedColumnName = "id", nullable = false )	
	private RequisicaoTransferencia requisicaoTransferencia;
		
	
	@ManyToOne
	@JoinColumn(name = "mercadoria_id", referencedColumnName = "id", nullable = false )	
	private Mercadoria mercadoria;
	
	@ManyToOne
	@JoinColumn(name = "fabricante_id", referencedColumnName = "id")		
	private PessoaJuridica fabricante;
	
	
	@Column(name="lote", nullable = false, length = 1024)
	private String lote;
	
	@Column(name="validade")
	private LocalDate validade;	
	
	@Column(name="quantidade", nullable = false )
	private Integer quantidade;
	
	@Column(name="quantidade_recebida", nullable = false )
	private Integer quantidadeRecebida;
	
	
	@ManyToOne
	@JoinColumn(name = "usuario_cadastro_id", referencedColumnName = "id", nullable = false)	
	private Usuario usuarioCadastro;
	
	@Column(name="data_hora_cadastro", nullable = false )
	private LocalDateTime dataHoraCadastro;
	
	@ManyToOne
	@JoinColumn(name = "usuario_rejeicao_id", referencedColumnName = "id")	
	private Usuario usuarioRejeicao;
	
	@Column(name="data_hora_rejeitado")
	private LocalDateTime dataHoraRejeitado;
	
	@Column(name="justificativa_rejeicao", length = 1024)
	private String justificativaRejeicao;
		
	
	
}
