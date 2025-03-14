package br.com.branetlogistica.ms_transferencia.modulos.requisicao.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.core.exceptions.dto.FieldErrorDto;
import br.com.branetlogistica.core.exceptions.impl.CustomValidationException;
import br.com.branetlogistica.core.exceptions.utils.ExceptionUtil;
import br.com.branetlogistica.core.util.CnpjCpfUtil;
import br.com.branetlogistica.core.util.DateTimeUtil;
import br.com.branetlogistica.core.util.Util;
import br.com.branetlogistica.ms_transferencia.modulos.centrocusto.service.CentroCustoService;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.service.MercadoriaService;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.service.PessoaJuridicaService;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaIntegracaoCadastroRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaIntegracaoItemCadastroRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.response.RequisicaoTransferenciaItemResponse;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.response.RequisicaoTransferenciaResponse;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.enums.RequisicaoTransferenciaItemStatusEnum;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.enums.RequisicaoTransferenciaStatusEnum;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.mapper.RequisicaoTransferenciaItemMapper;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.mapper.RequisicaoTransferenciaMapper;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.model.RequisicaoTransferencia;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.model.RequisicaoTransferenciaItem;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.repository.RequisicaoTransferenciaItemRepository;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.repository.RequisicaoTransferenciaRepository;
import br.com.branetlogistica.ms_transferencia.modulos.usuario.service.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequisicaoTransferenciaIntegracaoService {

	private UsuarioService usuarioService;
	private CentroCustoService centroService;	
	private MercadoriaService mercadoriaService;
	private PessoaJuridicaService pessoaJuridicaService;
	
	
	private RequisicaoTransferenciaRepository repository;
	private RequisicaoTransferenciaItemRepository itemRepository;
	
	
	private RequisicaoTransferenciaMapper mapper;
	private RequisicaoTransferenciaItemMapper itemMapper;
	
	
		
	private void validarCadastroRequest(RequisicaoTransferenciaIntegracaoCadastroRequest request) {
		List<FieldErrorDto> errors = new ArrayList<FieldErrorDto>();
		
		if(request.getCentroCustoDestinoId() == null)
			ExceptionUtil.addErro("centroCustoDestinoId", request.getCentroCustoDestinoId(),"não pode ser nulo" , errors);
		if(request.getCentroCustoDestinoId() == null ||request.getCentroCustoDestinoId() < 1)
			ExceptionUtil.addErro("centroCustoDestinoId", request.getCentroCustoDestinoId(),"não pode ser menor que 1" , errors);
		
		if(request.getCentroCustoOrigemId() == null)
			ExceptionUtil.addErro("centroCustoOrigemId", request.getCentroCustoOrigemId(),"não pode ser nulo" , errors);
		if(request.getCentroCustoOrigemId() == null || request.getCentroCustoDestinoId() < 1)
			ExceptionUtil.addErro("centroCustoOrigemId", request.getCentroCustoOrigemId(),"não pode ser menor que 1" , errors);
		
		if((request.getCentroCustoDestinoId() == null && request.getCentroCustoOrigemId() == null) 
			 ||	(request.getCentroCustoDestinoId() != null && 
				request.getCentroCustoDestinoId().equals(request.getCentroCustoOrigemId()))) {
			ExceptionUtil.addErro("centroCustoOrigemId", request.getCentroCustoOrigemId(),"não pode ser igual ao centroCustoDestinoId" , errors);
			ExceptionUtil.addErro("centroCustoDestinoId", request.getCentroCustoDestinoId(),"não pode ser igual ao centroCustoOrigemId" , errors);
		}
			
		
		if( (request.getUsuarioCadastroId() == null && request.getUsuarioCadastroCpf() == null) || 
			(request.getUsuarioCadastroId() != null && request.getUsuarioCadastroCpf() != null)) {
			ExceptionUtil.addErro("usuarioCadastroId", request.getUsuarioCadastroId(),"deve ser informado o usuarioCadastroId ou usuarioCadastroCpf" , errors);
			ExceptionUtil.addErro("usuarioCadastroCpf", request.getUsuarioCadastroCpf(),"deve ser informado o usuarioCadastroId ou usuarioCadastroCpf" , errors);
		}else if(request.getUsuarioCadastroId() != null && request.getUsuarioCadastroId() < 1)
			ExceptionUtil.addErro("usuarioCadastroId", request.getUsuarioCadastroId(),"não pode ser menor que 1" , errors);
		else if(!Util.isEmpty(request.getUsuarioCadastroCpf()) &&  !CnpjCpfUtil.isCPF(request.getUsuarioCadastroCpf()))	
			ExceptionUtil.addErro("usuarioCadastroCpf", request.getUsuarioCadastroCpf(),"cpf inválido" , errors);
		
		if(Util.isEmpty(request.getItens()))
			ExceptionUtil.addErro("itens", request.getItens(),"não pode ser nulo e deve ter ao meno 1 item",errors);
		else {
			for(int i = 0; i < request.getItens().size(); i ++) {
				RequisicaoTransferenciaIntegracaoItemCadastroRequest item = request.getItens().get(i);
				
				if(item.getMercadoriaId() == null)
					ExceptionUtil.addErro("item["+i+"].mercadoriaId", item.getMercadoriaId(),"não pode ser nulo" , errors);
				
				if(!Util.isValidId(item.getMercadoriaId()))
					ExceptionUtil.addErro("item["+i+"].mercadoriaId", item.getMercadoriaId(),"não pode ser menor que 1" , errors);
				
				if( (item.getFabricanteId() == null && item.getFabricanteCnpj() == null) || 
					(item.getFabricanteId() != null && item.getFabricanteCnpj() != null)) {
					ExceptionUtil.addErro("fabricanteId["+i+"]", item.getFabricanteId(),"deve ser informado o fabricanteId ou fabricanteCnpj" , errors);
					ExceptionUtil.addErro("fabricanteCnpj["+i+"]", request.getUsuarioCadastroCpf(),"deve ser informado o fabricanteCnpj ou fabricanteId" , errors);
				}else if(request.getUsuarioCadastroId() != null && request.getUsuarioCadastroId() < 1)
					ExceptionUtil.addErro("fabricanteId["+i+"]", item.getFabricanteId(),"não pode ser menor que 1" , errors);
				else if(item.getFabricanteCnpj() != null && !CnpjCpfUtil.isCNPJ(item.getFabricanteCnpj()))	
					ExceptionUtil.addErro("fabricanteCnpj["+i+"]", item.getFabricanteCnpj(),"cnpj inválido" , errors);
				
				if(Util.isEmpty(item.getLote()))
					ExceptionUtil.addErro("item["+i+"].lote", item.getLote(),"não pode ser nulo ou vazio" , errors);
				
				if(!Util.isEmpty(item.getValidade()) && !DateTimeUtil.isValidISODate(item.getValidade()))
					ExceptionUtil.addErro("item["+i+"].validade", item.getValidade(),"deve ter o formato ISO8601 yyyy-MM-dd" , errors);
				
				if(item.getQuantidade() == null)
					ExceptionUtil.addErro("item["+i+"].quantidade", item.getQuantidade(),"não pode ser nulo" , errors);
				
				if(item.getQuantidade() == null || item.getQuantidade() < 1)
					ExceptionUtil.addErro("item["+i+"].quantidade", item.getQuantidade(),"não pode ser menor que 1" , errors);
				
				
				
				
				
			}
				
		}
		
		
		if(!Util.isEmpty(errors))
			throw new CustomValidationException(errors);
		
	}
	
	@Transactional
	public RequisicaoTransferenciaResponse insert(RequisicaoTransferenciaIntegracaoCadastroRequest request) {
		this.validarCadastroRequest(request);
		
		RequisicaoTransferencia entity = new RequisicaoTransferencia();
		entity.setCentrocustoDestino(centroService.selecionarPorId(request.getCentroCustoDestinoId()));
		entity.setCentrocustoOrigem(centroService.selecionarPorId(request.getCentroCustoOrigemId()));
		entity.setStatus(RequisicaoTransferenciaStatusEnum.AUTORIZADO);
		entity.setObservacaoCadastro(request.getObservacaoCadastro());
		
		entity.setDataHoraCadastro(LocalDateTime.now());
		
		if(Util.isValidId(request.getUsuarioCadastroId()))
			entity.setUsuarioCadastro(usuarioService.selecionarPorId(request.getUsuarioCadastroId()));
		
		
		entity = repository.save(entity);
		
		List<RequisicaoTransferenciaItem> itens = new ArrayList<RequisicaoTransferenciaItem>();
		
		for(RequisicaoTransferenciaIntegracaoItemCadastroRequest itemRequest : request.getItens()) {
			
			RequisicaoTransferenciaItem item = new RequisicaoTransferenciaItem();
			item.setRequisicaoTransferencia(entity);
			item.setDataHoraCadastro(LocalDateTime.now());
			item.setUsuarioCadastro(entity.getUsuarioCadastro());
			item.setStatus(RequisicaoTransferenciaItemStatusEnum.CADASTRADO);
			item.setMercadoria(mercadoriaService.selecionarPorId(itemRequest.getMercadoriaId()));
			item.setLote(itemRequest.getLote());
			item.setQuantidade(itemRequest.getQuantidade());
			item.setQuantidadeRecebida(0);
			if(Util.isValidId(itemRequest.getFabricanteId()))
				item.setFabricante(pessoaJuridicaService.selecionarPorId(itemRequest.getFabricanteId()));
			
						
			if(item.getValidade() != null)
				item.setValidade(LocalDate.parse(itemRequest.getValidade()));
			
			itens.add(item);
			
		}
		
		
		
		itens = itemRepository.saveAll(itens);
		
		
		RequisicaoTransferenciaResponse response = mapper.toResponse(entity);
		List<RequisicaoTransferenciaItemResponse> dtos = itemMapper.toResponse(itens);
	
		response.setItens(dtos);
		
		return response;
		
	}
	
	
}
