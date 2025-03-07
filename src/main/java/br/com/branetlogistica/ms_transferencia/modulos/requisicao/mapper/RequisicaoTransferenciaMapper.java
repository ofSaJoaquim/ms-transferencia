package br.com.branetlogistica.ms_transferencia.modulos.requisicao.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import br.com.branetlogistica.ms_transferencia.modulos.centrocusto.converter.CentroCustoConverter;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.model.DadosCadastro;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.model.RequisicaoTransferenciaDTO;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.response.RequisicaoTransferenciaResponse;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.model.RequisicaoTransferencia;
import br.com.branetlogistica.ms_transferencia.modulos.usuario.converter.UsuarioConverter;
import br.com.branetlogistica.ms_transferencia.modulos.usuario.model.Usuario;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RequisicaoTransferenciaMapper {

	private UsuarioConverter usuarioConverter;
	private CentroCustoConverter centroCustoConverter;

	public RequisicaoTransferenciaDTO toDTO(RequisicaoTransferencia entity) {
		if (entity == null)
			return null;

		RequisicaoTransferenciaDTO dto = new RequisicaoTransferenciaDTO();
		dto.setId(entity.getId());
		dto.setStatus(entity.getStatus().toString());

		if (entity.getDataHoraFinalizado() != null)
			dto.setDataHoraFinalizado(entity.getDataHoraFinalizado().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		return dto;

	}

	public DadosCadastro toDadosCadastro(Usuario usuario, LocalDateTime dataHora, String observacao) {
		if (usuario == null || dataHora == null)
			return null;

		DadosCadastro dto = new DadosCadastro();
		dto.setUsuario(this.usuarioConverter.toDto(usuario));
		dto.setDataHora(dataHora.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		if (observacao != null && !observacao.isBlank())
			dto.setObservacao(observacao);

		return dto;
	}

	public RequisicaoTransferenciaResponse toResponse(RequisicaoTransferencia entity) {
		if (entity == null)
			return null;

		RequisicaoTransferenciaResponse response = new RequisicaoTransferenciaResponse();
		
		response.setId(entity.getId());
		response.setStatus(entity.getStatus().toString());

		if (entity.getDataHoraFinalizado() != null)
			response.setDataHoraFinalizado(entity.getDataHoraFinalizado().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		
		response.setCentroCustoOrigem(centroCustoConverter.toResponse(entity.getCentrocustoOrigem()));
		response.setCentroCustoDestino(centroCustoConverter.toResponse(entity.getCentrocustoDestino()));
		response.setDadosCadastro(this.toDadosCadastro(entity.getUsuarioCadastro(), entity.getDataHoraCadastro(),
				entity.getObservacaoCadastro()));
		response.setDadosAutorizacao(this.toDadosCadastro(entity.getUsuarioAutorizador(),
				entity.getDataHoraAutorizado(), entity.getObservacaoAutorizacao()));
		response.setDadosAprovacao(this.toDadosCadastro(entity.getUsuarioAprovador(), entity.getDataHoraAprovado(),
				entity.getObservacaoAprovacao()));
		response.setDadosColeta(this.toDadosCadastro(entity.getUsuarioColeta(), entity.getDataHoraColeta(),
				entity.getObservacaoColeta()));
		response.setDadosRecebimento(this.toDadosCadastro(entity.getUsuarioRecebimento(),
				entity.getDataHoraRecebimento(), entity.getObservacaoRecebimento()));
		response.setDadosInativacao(this.toDadosCadastro(entity.getUsuarioInativador(), entity.getDataHoraInativado(),
				entity.getJustificativaInativacao()));
		response.setDadosRejeicao(this.toDadosCadastro(entity.getUsuarioRejeicao(), entity.getDataHoraRejeitado(), 
				entity.getJustificativaRejeicao()));
		return response;

	}

}
