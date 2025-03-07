package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto;

import br.com.branetlogistica.core.interfaces.IQueueDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoteQueue implements IQueueDto {

	private Long id;
	private String versionNumber;
	
	private Long mercadoriaId;
	private Long centroDistribuicaoId;
	private Long fornecedorId;
	private Long configuracaoMercadoriaId;
	private String nome;
	private String validade;	
	private Long idCodigoBarras;
	private Double valorUnitario;
	private Long idLoteMarca;
}
