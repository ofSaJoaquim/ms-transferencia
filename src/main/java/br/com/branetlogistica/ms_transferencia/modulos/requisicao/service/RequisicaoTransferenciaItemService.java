package br.com.branetlogistica.ms_transferencia.modulos.requisicao.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.service.MercadoriaService;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.service.PessoaJuridicaService;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaItemCadastroRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.response.RequisicaoTransferenciaItemResponse;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.enums.RequisicaoTransferenciaItemStatusEnum;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.mapper.RequisicaoTransferenciaItemMapper;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.model.RequisicaoTransferenciaItem;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.repository.RequisicaoTransferenciaItemRepository;
import br.com.branetlogistica.ms_transferencia.modulos.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequisicaoTransferenciaItemService {

	private RequisicaoTransferenciaItemMapper mapper;
	private RequisicaoTransferenciaItemRepository repository;
	
	
	private UsuarioService usuarioService;
	private MercadoriaService mercadoriaService;
	private PessoaJuridicaService pessoaJuridicaService;
	private RequisicaoTransferenciaService requisicaoTransferenciaService;
	
	
	public RequisicaoTransferenciaItemResponse insert(RequisicaoTransferenciaItemCadastroRequest request) {
		
		RequisicaoTransferenciaItem entity = new RequisicaoTransferenciaItem();
		entity.setRequisicaoTransferencia(requisicaoTransferenciaService.findById(request.getRequisicaoTransferenciaId()));
		entity.setMercadoria(mercadoriaService.selecionarPorId(request.getMercadoriaId()));
		entity.setUsuarioCadastro(usuarioService.selecionarPorId(request.getUsuarioCadastroId()));
		entity.setStatus(RequisicaoTransferenciaItemStatusEnum.CADASTRADO);
		entity.setDataHoraCadastro(LocalDateTime.now());
		entity.setLote(request.getLote());
		entity.setQuantidade(request.getQuantidade());
		entity.setQuantidadeRecebida(0);
	
		if(request.getFabricanteId()!=null)
			entity.setFabricante(pessoaJuridicaService.selecionarPorId(request.getFabricanteId()));
		
		entity = repository.save(entity);
		return mapper.toResponse(entity);
		
		
	}
	
}
