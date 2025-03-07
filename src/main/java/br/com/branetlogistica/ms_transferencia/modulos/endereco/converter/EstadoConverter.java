package br.com.branetlogistica.ms_transferencia.modulos.endereco.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.branetlogistica.ms_transferencia.modulos.endereco.dto.EstadoQueue;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.model.Estado;

@Component
public class EstadoConverter {

	public Estado toEntity(EstadoQueue queue) {
		Estado entidade = 
				Estado.builder()
				.id(queue.getId())
				.version(Long.parseLong(queue.getVersionNumber()))
				.nome(queue.getNome())
				.uf(queue.getUf())
				.ibge(queue.getIbge())
				.build();
		
		
		return entidade;		
	}
	
	
	public List<Estado> toEntity(List<EstadoQueue> dtos) {
		List<Estado> entities = new ArrayList<Estado>();
		for(EstadoQueue dto : dtos)
			entities.add(toEntity(dto));
		return entities;
	}
	
	
}
