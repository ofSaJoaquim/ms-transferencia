package br.com.branetlogistica.ms_transferencia.modulos.endereco.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.branetlogistica.ms_transferencia.modulos.endereco.dto.EnderecoDTO;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.dto.EnderecoQueue;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.model.Cidade;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.model.Endereco;

@Component
public class EnderecoConverter {

	public Endereco toEntity(EnderecoQueue queue) {
		Endereco entidade = 
				Endereco.builder()
				.id(queue.getId())
				.version(Long.parseLong(queue.getVersionNumber()))
				.bairro(queue.getBairro())
				.cep(queue.getCep())
				.cidade(Cidade.builder().id(queue.getCidadeId()).build())
				.complemento(queue.getComplemento())
				.logradouro(queue.getLogradouro())
				.semNumero(queue.isSemNumero())
				.numero(queue.getNumero())
				.tipoLogradouro(queue.getTipoLogradouro())
				.build();
				
		return entidade;		
	}
	
	public List<Endereco> toEntity(List<EnderecoQueue> dtos) {
		List<Endereco> entities = new ArrayList<Endereco>();
		for(EnderecoQueue dto : dtos)
			entities.add(toEntity(dto));
		return entities;
	}
	
	
	public EnderecoDTO toDto(Endereco entity) {
		if(entity == null)
			return null;
		
		return EnderecoDTO.builder()
				.id(entity.getId())
				.bairro(entity.getBairro())
				.cep(entity.getCep())
				.bairro(entity.getBairro())
				.cidadeId(entity.getCidade().getId())
				.cidadeNome(entity.getCidade().getNome())
				.estadoId(entity.getCidade().getEstado().getId())
				.estadoNome(entity.getCidade().getEstado().getNome())
				.complemento(entity.getComplemento())
				.estadoUf(entity.getCidade().getEstado().getUf())
				.logradouro(entity.getLogradouro())
				.numero(entity.getNumero())
				.semNumero(entity.isSemNumero())
				.tipoLogradouro(entity.getTipoLogradouro())
				.build();
	}
}
