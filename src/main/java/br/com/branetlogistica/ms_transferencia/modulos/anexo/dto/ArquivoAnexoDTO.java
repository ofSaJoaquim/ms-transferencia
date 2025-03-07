package br.com.branetlogistica.ms_transferencia.modulos.anexo.dto;

import br.com.branetlogistica.ms_transferencia.modulos.anexo.enums.ArquivoFormatoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArquivoAnexoDTO {

	private Long id;
	private String nome;	
	private ArquivoFormatoEnum formato;
	private String version;
	
	public ArquivoAnexoDTO(Long id, String nome, ArquivoFormatoEnum formato) {
        this.id = id;
        this.nome = nome;
        this.formato = formato;
    }
	
}
