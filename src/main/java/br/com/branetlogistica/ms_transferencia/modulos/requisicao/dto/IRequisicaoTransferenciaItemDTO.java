package br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto;

public interface IRequisicaoTransferenciaItemDTO {

	void setId(Long id);
	void setStatus(String status);
	void setLote(String lote);
	void setValidade(String validade);	
	void setQuantidade(Integer quantidade);
	void setQuantidadeRecebida(Integer quantidadeRecebida);
	
	
}
