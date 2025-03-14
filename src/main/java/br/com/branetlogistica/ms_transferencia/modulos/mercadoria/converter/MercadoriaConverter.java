package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto.MercadoriaQueue;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto.MercadoriaResponse;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model.Mercadoria;

@Component
public class MercadoriaConverter {

	public Mercadoria toEntity(MercadoriaQueue queue) {
		Mercadoria entidade = 
				Mercadoria.builder()
				.nome(queue.getNome())
				.exportaHorus(queue.isExportaHorus())
				.numeroCATMAT(queue.getNumeroCatmat())
				.registroAnvisa(queue.getNumeroRegistroAnvisa())			
				.build();
		
		entidade.setId(queue.getId());
		entidade.setVersion(Long.parseLong(queue.getVersionNumber()));
		return entidade;		
	}
	
	public List<Mercadoria> toEntity(List<MercadoriaQueue> dtos) {
		List<Mercadoria> entities = new ArrayList<Mercadoria>();
		for(MercadoriaQueue dto : dtos)
			entities.add(toEntity(dto));
		return entities;
	}
	
	public MercadoriaResponse toResponse(Mercadoria entidade) {
		return MercadoriaResponse.builder()
				.id(entidade.getId())				
				.nome(entidade.getNome())				
				.codigoCliente(entidade.getCodigoCliente())
				.build();
	}
	
}
