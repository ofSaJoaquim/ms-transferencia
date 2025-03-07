package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto;

import br.com.branetlogistica.core.interfaces.IQueueDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoMercadoriaQueue implements IQueueDto {

	private Long id;
	private String versionNumber;
	
	private Long marcaId;
	private Long mercadoriaId;		
	private Long tipoEmbalagemId;
	private Integer quantidade;
	private String codigoBarrasFabricante;
	private String registroAnvisa;
	private Long fabricanteId;
	
}
