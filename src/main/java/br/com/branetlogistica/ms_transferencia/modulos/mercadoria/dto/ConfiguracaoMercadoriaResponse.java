package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto;

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
public class ConfiguracaoMercadoriaResponse {

	private Long id;
	private String version;
	
	private String registroAnvisa;
	private String codigoBarrasFabricante;
	private Long fabricanteId;
	private String fabricanteRazaoSocial;
	private String fabricanteCNPJ;
	private String fabricanteNomeFantasia;
	
}
