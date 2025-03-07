package br.com.branetlogistica.ms_transferencia.modulos.requisicao.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import br.com.branetlogistica.core.interfaces.IAuditoryEntity;
import br.com.branetlogistica.core.interfaces.IEntity;
import br.com.branetlogistica.core.log.auditory.service.AuditListener;
import br.com.branetlogistica.ms_transferencia.modulos.centrocusto.model.CentroCusto;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.enums.RequisicaoTransferenciaStatusEnum;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode.Include;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requisicao_transferencia")
@EntityListeners(AuditListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RequisicaoTransferencia implements IEntity, IAuditoryEntity {

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
	private RequisicaoTransferenciaStatusEnum status;
	
	@ManyToOne
	@JoinColumn(name = "centrocusto_origem_id", referencedColumnName = "id", nullable = false )
	private CentroCusto centrocustoOrigem;
	
	@ManyToOne
	@JoinColumn(name = "centrocusto_destino_id", referencedColumnName = "id", nullable = false )
	private CentroCusto centrocustoDestino;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cadastro_id", referencedColumnName = "id", nullable = false )
	private Usuario usuarioCadastro;
	
	@Column(name = "data_hora_cadastro", nullable = false)
	private LocalDateTime dataHoraCadastro;
	
	@Column(name = "observacao_cadastro", length = 1024)
	private String observacaoCadastro;
	
	@ManyToOne
	@JoinColumn(name = "usuario_autorizador_id", referencedColumnName = "id" )
	private Usuario usuarioAutorizador;
	
	@Column(name = "data_hora_autorizado")
	private LocalDateTime dataHoraAutorizado;
	
	@Column(name = "observacao_autorizacao", length = 1024)
	private String observacaoAutorizacao;
			
	@ManyToOne
	@JoinColumn(name = "usuario_aprovador_id", referencedColumnName = "id" )
	private Usuario usuarioAprovador;
	
	@Column(name = "data_hora_aprovado")
	private LocalDateTime dataHoraAprovado;
	
	
	@Column(name = "observacao_aprovacao", length = 1024)
	private String observacaoAprovacao;
		
	@ManyToOne
	@JoinColumn(name = "usuario_inativador_id", referencedColumnName = "id" )	
	private Usuario usuarioInativador;
	
	@Column(name = "data_hora_inativado")
	private LocalDateTime dataHoraInativado;
	
	
	@Column(name = "justificativa_inativacao", length = 1024)
	private String justificativaInativacao;
	
	@ManyToOne
	@JoinColumn(name = "usuario_coleta_id", referencedColumnName = "id" )	
	private Usuario usuarioColeta;
	
	@Column(name = "data_hora_coleta")
	private LocalDateTime dataHoraColeta;
	
	@Column(name = "observacao_coleta", length = 1024)
	private String observacaoColeta;
	
	@ManyToOne
	@JoinColumn(name = "usuario_recebimento_id", referencedColumnName = "id" )	
	private Usuario usuarioRecebimento;
	
	@Column(name = "data_hora_recebimento")
	private LocalDateTime dataHoraRecebimento;
	
	@Column(name = "observacao_recebimento", length = 1024)
	private String observacaoRecebimento;
	
	@Column(name = "data_hora_finalizado")
	private LocalDateTime dataHoraFinalizado;
	
	
	@ManyToOne
	@JoinColumn(name = "usuario_rejeicao_id", referencedColumnName = "id" )	
	private Usuario usuarioRejeicao;
	
	@Column(name = "data_hora_rejeitado")
	private LocalDateTime dataHoraRejeitado;	
	
	@Column(name = "justificativa_rejeicao", length = 1024)
	private String justificativaRejeicao;
	
}
