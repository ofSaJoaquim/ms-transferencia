package br.com.branetlogistica.ms_transferencia.modulos.requisicao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequisicaoTransferenciaStatusEnum {

	EM_DIGITACAO("Em digitação"), 
	AUTORIZADO("Autorizado"),
	REJEITADO("Rejeitado"),
	APROVADO("Aprovado"),
	COLETADO("Coletado"),
	RECEBIDO("Recebido"),
	INATIVADO("Inativado"),
	FINALIZADO("Finalizado");
	
	private String label;
	
}
