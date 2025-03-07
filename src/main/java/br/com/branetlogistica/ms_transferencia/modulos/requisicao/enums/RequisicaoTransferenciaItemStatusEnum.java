package br.com.branetlogistica.ms_transferencia.modulos.requisicao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequisicaoTransferenciaItemStatusEnum {

	CADASTRADO("Cadastrado"),
	INATIVADO("Inativado"),
	EM_RECEBIMENTO("Em Recebimento"),
	FINALIZADO("Finalizado");
	
	private String label;
	
}
