package br.com.branetlogistica.ms_transferencia.modulos.requisicao.mapper;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.converter.MercadoriaConverter;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.converter.PessoaJuridicaConverter;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.IRequisicaoTransferenciaItemDTO;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.model.RequisicaoTransferenciaItemDTO;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.response.RequisicaoTransferenciaItemResponse;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.model.RequisicaoTransferenciaItem;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequisicaoTransferenciaItemMapper {

	private MercadoriaConverter mercadoriaConverter;	
	private PessoaJuridicaConverter pessoaJuridicaConverter;
	private RequisicaoTransferenciaMapper requisicaoTransferenciaMapper;
	
	
	public RequisicaoTransferenciaItemDTO toDTO(RequisicaoTransferenciaItem entity ) {
		if(entity == null)
			return null;
		
		return (RequisicaoTransferenciaItemDTO)this.toDTO(entity, new RequisicaoTransferenciaItemDTO());
		
	}
	
	private IRequisicaoTransferenciaItemDTO toDTO(RequisicaoTransferenciaItem entity, IRequisicaoTransferenciaItemDTO dto ) {
		
		dto.setId(entity.getId());
		dto.setStatus(entity.getStatus().toString());
		
		dto.setLote(entity.getLote());
		if(entity.getValidade()!=null)
			dto.setValidade(entity.getValidade().format(DateTimeFormatter.ISO_LOCAL_DATE));
		
		dto.setQuantidade(entity.getQuantidade());
		dto.setQuantidadeRecebida(entity.getQuantidadeRecebida());
		
		return dto;
	}
	
	
	public RequisicaoTransferenciaItemResponse toResponse(RequisicaoTransferenciaItem entity) {
		if(entity == null)
			return null;
		
		RequisicaoTransferenciaItemResponse response = (RequisicaoTransferenciaItemResponse)this.toDTO(entity, new RequisicaoTransferenciaItemResponse());
		response.setRequisicaoTransferencia(this.requisicaoTransferenciaMapper.toDTO(entity.getRequisicaoTransferencia()));
		response.setMercadoria(mercadoriaConverter.toResponse(entity.getMercadoria()));
		response.setFabricante(pessoaJuridicaConverter.toResponse(entity.getFabricante()));
		
		response.setDadosCadastro(requisicaoTransferenciaMapper.toDadosCadastro(entity.getUsuarioCadastro(), entity.getDataHoraCadastro(), null));
		response.setDadosRejeicao(requisicaoTransferenciaMapper.toDadosCadastro(entity.getUsuarioRejeicao(), entity.getDataHoraRejeitado(), entity.getJustificativaRejeicao()));
		
		return response;
		
	}
	
}
