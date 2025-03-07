package br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.IRequisicaoTransferenciaItemDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class RequisicaoTransferenciaItemDTO implements IRequisicaoTransferenciaItemDTO {

	private Long id;
	private String status;
	private String lote;
	private String validade;	
	private Integer quantidade;
	private Integer quantidadeRecebida;
	
}
