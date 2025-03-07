package br.com.branetlogistica.ms_transferencia.modulos.anexo.enums;

import lombok.Getter;

@Getter
public enum ArquivoFormatoEnum {
	
	PDF("pdf","application/pdf",TipoArquivoEnum.ARQUIVO),
	PRN("prn","application/txt",TipoArquivoEnum.ARQUIVO),
	BTW("btw","applciation/txt",TipoArquivoEnum.ARQUIVO),	
	NLBL("nlbl","applciation/txt",TipoArquivoEnum.ARQUIVO),
	PNG("png","image/png",TipoArquivoEnum.ARQUIVO),
	JPG("jpg","image/jpg",TipoArquivoEnum.ARQUIVO),
	JPEG("jpeg","image/jpg",TipoArquivoEnum.ARQUIVO);
	
	private String formato;
	private String aplicacao;
	private TipoArquivoEnum tipoArquivo;
	
	private ArquivoFormatoEnum(String formato, String aplicacao, TipoArquivoEnum tipoArquivo) {
		this.formato = formato;
		this.aplicacao = aplicacao;
		this.tipoArquivo = tipoArquivo;
	}
	
	
}