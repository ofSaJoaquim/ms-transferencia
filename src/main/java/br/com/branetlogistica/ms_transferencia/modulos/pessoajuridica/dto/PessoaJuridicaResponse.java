package br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.dto;

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
public class PessoaJuridicaResponse {

	private Long id;
	private String razaoSocial;
	private String nomeFantasia;
	private String cnpj;

}
