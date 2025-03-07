package br.com.branetlogistica.ms_transferencia.modulos.requisicao.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.branetlogistica.core.exceptions.dto.FieldErrorDto;
import br.com.branetlogistica.core.exceptions.impl.ApiException;
import br.com.branetlogistica.core.exceptions.impl.NotFoundException;
import br.com.branetlogistica.ms_transferencia.modulos.centrocusto.service.CentroCustoService;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaAprovarRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaAutorizarRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaCadastroRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaColetaRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaInativacaoRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaRecebimentoRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaRejeitarRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.response.RequisicaoTransferenciaResponse;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.enums.RequisicaoTransferenciaStatusEnum;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.mapper.RequisicaoTransferenciaMapper;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.model.RequisicaoTransferencia;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.repository.RequisicaoTransferenciaRepository;
import br.com.branetlogistica.ms_transferencia.modulos.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequisicaoTransferenciaService {

	private RequisicaoTransferenciaMapper mapper;
	private RequisicaoTransferenciaRepository repository;
	
	
	private CentroCustoService centroCustoService;
	private UsuarioService usuarioService;
	
	
	public RequisicaoTransferenciaResponse findByIdResponse(Long id) {
		return mapper.toResponse(this.findById(id));
	}
	
	public RequisicaoTransferencia findById(Long id) {
		Optional<RequisicaoTransferencia> obj = repository.findById(id);
		return obj.orElseThrow(() -> new NotFoundException("Não foi encontrado requisição com o id = "+id));
	}
	
	
	public RequisicaoTransferenciaResponse insert(RequisicaoTransferenciaCadastroRequest request) {
		
		if(request.getCentroCustoDestinoId().equals(request.getCentroCustoOrigemId()))
			throw new ApiException(HttpStatus.BAD_REQUEST, 
					"Erro de validação",
					Arrays.asList(
							new FieldErrorDto("centroCustoOrigemId",
									"o centro de custo origem não pode ser o mesmo de destino",
									request.getCentroCustoOrigemId().toString()),
							new FieldErrorDto("centroCustoDestinoId",
									"o centro de custo destino não pode ser o mesmo de origem",
									request.getCentroCustoOrigemId().toString())
							));
		
		
		RequisicaoTransferencia entity = new RequisicaoTransferencia();
		entity.setCentrocustoOrigem(centroCustoService.selecionarPorId(request.getCentroCustoOrigemId()));
		entity.setCentrocustoDestino(centroCustoService.selecionarPorId(request.getCentroCustoDestinoId()));
		entity.setUsuarioCadastro(usuarioService.selecionarPorId(request.getUsuarioCadastroId()));
		entity.setDataHoraCadastro(LocalDateTime.now());
		entity.setObservacaoCadastro(request.getObservacaoCadastro());
		
		entity.setStatus(RequisicaoTransferenciaStatusEnum.EM_DIGITACAO);
		
		entity = repository.save(entity);
		return mapper.toResponse(entity);
		
	}
	
	public RequisicaoTransferenciaResponse autorizar(Long id, RequisicaoTransferenciaAutorizarRequest request) {
		RequisicaoTransferencia entity = this.findById(id);
	
		if(!entity.getStatus().equals(RequisicaoTransferenciaStatusEnum.EM_DIGITACAO))
			throw new ApiException(HttpStatus.BAD_REQUEST, "Somente pode autorizar uma requisição de transferência com status igual 'EM_DIGITACAO'");
	
		entity.setUsuarioAutorizador(usuarioService.selecionarPorId(request.getUsuarioAutorizadorId()));
		entity.setDataHoraAutorizado(LocalDateTime.now());
		entity.setObservacaoAutorizacao(request.getObservacaoAutorizacao());		
		entity.setStatus(RequisicaoTransferenciaStatusEnum.AUTORIZADO);
		
		entity = repository.save(entity);
		return mapper.toResponse(entity);
		
	}
	
	
	public RequisicaoTransferenciaResponse aprovar(Long id, RequisicaoTransferenciaAprovarRequest request) {
		RequisicaoTransferencia entity = this.findById(id);
	
		if(!entity.getStatus().equals(RequisicaoTransferenciaStatusEnum.AUTORIZADO))
			throw new ApiException(HttpStatus.BAD_REQUEST, "Somente pode aprovar uma requisição de transferência com status igual 'AUTORIZADO'");
	
		entity.setUsuarioAprovador(usuarioService.selecionarPorId(request.getUsuarioAprovadorId()));
		entity.setDataHoraAprovado(LocalDateTime.now());
		entity.setObservacaoAprovacao(request.getObservacaoAprovacao());		
		entity.setStatus(RequisicaoTransferenciaStatusEnum.APROVADO);
		
		entity = repository.save(entity);
		return mapper.toResponse(entity);
		
	}
	
	
	public RequisicaoTransferenciaResponse coletar(Long id, RequisicaoTransferenciaColetaRequest request) {
		RequisicaoTransferencia entity = this.findById(id);
	
		if(!entity.getStatus().equals(RequisicaoTransferenciaStatusEnum.APROVADO))
			throw new ApiException(HttpStatus.BAD_REQUEST, "Somente pode coletar uma requisição de transferência com status igual 'APROVADO'");
	
		entity.setUsuarioColeta(usuarioService.selecionarPorId(request.getUsuarioColetaId()));
		entity.setDataHoraColeta(LocalDateTime.now());
		entity.setObservacaoColeta(request.getObservacaoColeta());		
		entity.setStatus(RequisicaoTransferenciaStatusEnum.COLETADO);
		
		entity = repository.save(entity);
		return mapper.toResponse(entity);
		
	}
	
	
	public RequisicaoTransferenciaResponse receber(Long id, RequisicaoTransferenciaRecebimentoRequest request) {
		RequisicaoTransferencia entity = this.findById(id);
	
		if(!(entity.getStatus().equals(RequisicaoTransferenciaStatusEnum.COLETADO)
				|| entity.getStatus().equals(RequisicaoTransferenciaStatusEnum.APROVADO)))
			throw new ApiException(HttpStatus.BAD_REQUEST, "Somente pode receber uma requisição de transferência com status igual 'APROVADO' ou 'COLETADO'");
	
		entity.setUsuarioRecebimento(usuarioService.selecionarPorId(request.getUsuarioRecebimentoId()));
		entity.setDataHoraRecebimento(LocalDateTime.now());
		entity.setObservacaoRecebimento(request.getObservacaoRecebimento());		
		entity.setStatus(RequisicaoTransferenciaStatusEnum.RECEBIDO);
		
		entity = repository.save(entity);
		return mapper.toResponse(entity);
		
	}
	
	
	public RequisicaoTransferenciaResponse inativar(Long id, RequisicaoTransferenciaInativacaoRequest request) {
		RequisicaoTransferencia entity = this.findById(id);
	
		if(!(entity.getStatus().equals(RequisicaoTransferenciaStatusEnum.EM_DIGITACAO)))
			throw new ApiException(HttpStatus.BAD_REQUEST, "Somente pode inativar uma requisição de transferência com status igual 'EM_DIGITACAO'");
	
		entity.setUsuarioInativador(usuarioService.selecionarPorId(request.getUsuarioInativadorId()));
		entity.setDataHoraInativado(LocalDateTime.now());
		entity.setJustificativaInativacao(request.getJustificativaInativacao());		
		entity.setStatus(RequisicaoTransferenciaStatusEnum.INATIVADO);
		entity.setDisabled(true);
		
		entity = repository.save(entity);
		return mapper.toResponse(entity);
		
	}
	
	
	public RequisicaoTransferenciaResponse rejeitar(Long id, RequisicaoTransferenciaRejeitarRequest request) {
		RequisicaoTransferencia entity = this.findById(id);
	
		if(entity.getStatus().equals(RequisicaoTransferenciaStatusEnum.EM_DIGITACAO) 
			||	entity.getStatus().equals(RequisicaoTransferenciaStatusEnum.FINALIZADO))
			throw new ApiException(HttpStatus.BAD_REQUEST, "Não pode rejeitar uma requisição com status 'EM_DIGITACAO','RECEBIDO' ou 'FINALIZADO' ");
	
		entity.setUsuarioRejeicao(usuarioService.selecionarPorId(request.getUsuarioRejeicaoId()));
		entity.setDataHoraRejeitado(LocalDateTime.now());
		entity.setJustificativaRejeicao(request.getJustificativaRejeicao());		
		entity.setStatus(RequisicaoTransferenciaStatusEnum.REJEITADO);
		entity.setDisabled(true);
		
		entity = repository.save(entity);
		return mapper.toResponse(entity);
		
	}
	
	
}
