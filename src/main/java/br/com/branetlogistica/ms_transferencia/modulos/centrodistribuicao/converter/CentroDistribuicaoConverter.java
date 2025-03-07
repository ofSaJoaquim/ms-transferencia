package br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.converter;

import org.springframework.stereotype.Component;

import br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.dto.CentroDistribuicaoQueue;
import br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.model.CentroDistribuicao;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.model.Endereco;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.model.PessoaJuridica;

@Component
public class CentroDistribuicaoConverter {

	public CentroDistribuicao toEntity(CentroDistribuicaoQueue queue) {
		CentroDistribuicao entidade = 	
		CentroDistribuicao.builder()
		.id(queue.getId())
		.nome(queue.getNome())
		.codigoCnes(queue.getCnes())
		.pessoaJuridica(PessoaJuridica.builder().id(queue.getPessoaJuridicaId()).build())
		.endereco(Endereco.builder().id(queue.getEnderecoId()).build())
		.version(Long.parseLong(queue.getVersionNumber()))
		.build();		
		
		return entidade;
	}
	
}
