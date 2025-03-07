package br.com.branetlogistica.ms_transferencia.modulos.endereco.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.branetlogistica.ms_transferencia.modulos.endereco.dto.CidadeQueue;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.model.Cidade;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.model.Estado;

@Component
public class CidadeConverter {

	public Cidade toEntity(CidadeQueue queue) {
		Cidade entidade = 
				Cidade.builder()
				.id(queue.getId())
				.version(Long.parseLong(queue.getVersionNumber()))
				.nome(queue.getNome())
				.estado(Estado.builder().id(queue.getEstadoId()).build())
				.ibge(queue.getIbge())
				.build();
		
	
		return entidade;		
	}
	
	public List<Cidade> toEntity(List<CidadeQueue> dtos) {
		List<Cidade> entities = new ArrayList<Cidade>();
		for(CidadeQueue dto : dtos)
			entities.add(toEntity(dto));
		return entities;
		
		
	}
	
	
}
