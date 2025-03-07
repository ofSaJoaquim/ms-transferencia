package br.com.branetlogistica.ms_transferencia.modulos.anexo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoArquivoEnum {

	DOCUMENTO("Documento"),
	IMAGEM("Imagem"),
	ARQUIVO("Arquivo");
	
	private String label;
	
	
	
}
