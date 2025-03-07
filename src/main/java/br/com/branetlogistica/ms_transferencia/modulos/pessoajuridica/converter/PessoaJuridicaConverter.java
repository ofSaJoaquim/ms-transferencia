package br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.dto.PessoaJuridicaQueue;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.dto.PessoaJuridicaResponse;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.model.PessoaJuridica;

@Component
public class PessoaJuridicaConverter {

	public PessoaJuridica toEntity(PessoaJuridicaQueue queue) {
		return PessoaJuridica.builder()
				.id(queue.getId())
				.disabled(false)
				.version(Long.parseLong(queue.getVersionNumber()))
				.razaoSocial(queue.getRazaoSocial())
				.nomeFantasia(queue.getNomeFantasia())
				.cnpj(queue.getCnpj()).build();

	}
	
	public List<PessoaJuridica> toEntity(List<PessoaJuridicaQueue> dtos) {
		 List<PessoaJuridica> entidades = new ArrayList<PessoaJuridica>();
		 for(PessoaJuridicaQueue dto : dtos)
			 entidades.add(this.toEntity(dto));
		 return entidades;
	}
	
	public PessoaJuridicaResponse toResponse(PessoaJuridica entidade) {
		if(entidade == null)
			return null;
		
		return PessoaJuridicaResponse.builder()
				.id(entidade.getId())
				.cnpj(entidade.getCnpj())
				.razaoSocial(entidade.getRazaoSocial())
				.nomeFantasia(entidade.getNomeFantasia())				
				.build();
	}
	
	
}
