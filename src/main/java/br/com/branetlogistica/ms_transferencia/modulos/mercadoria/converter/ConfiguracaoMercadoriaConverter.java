package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto.ConfiguracaoMercadoriaQueue;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto.ConfiguracaoMercadoriaResponse;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model.ConfiguracaoMercadoria;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model.Mercadoria;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.model.PessoaJuridica;

@Component
public class ConfiguracaoMercadoriaConverter {

	public ConfiguracaoMercadoria toEntity(ConfiguracaoMercadoriaQueue queue) {
		ConfiguracaoMercadoria entidade = 
				ConfiguracaoMercadoria.builder()
				.mercadoria(new Mercadoria(queue.getMercadoriaId()))
				.quantidade(queue.getQuantidade())
				.codigoBarrasFabricante(queue.getCodigoBarrasFabricante())
				.registroAnvisa(queue.getRegistroAnvisa())
				.fabricante(new PessoaJuridica(queue.getFabricanteId()))
				.build();
		
		entidade.setId(queue.getId());
		entidade.setVersion(Long.parseLong(queue.getVersionNumber()));
		return entidade;		
	}
	
	public List<ConfiguracaoMercadoria> toEntity(List<ConfiguracaoMercadoriaQueue> dtos) {
		List<ConfiguracaoMercadoria> entidades = new ArrayList<ConfiguracaoMercadoria>();
		for(ConfiguracaoMercadoriaQueue dto : dtos )
			entidades.add(toEntity(dto));
		return entidades;
	}
	
	public ConfiguracaoMercadoriaResponse toResponse(ConfiguracaoMercadoria entidade) {
		ConfiguracaoMercadoriaResponse dto = ConfiguracaoMercadoriaResponse.builder()
				.id(entidade.getId())
				.codigoBarrasFabricante(entidade.getCodigoBarrasFabricante())
				.registroAnvisa(entidade.getRegistroAnvisa())
				.version(entidade.getVersion().toString())
				.build();
		
		if(entidade.getFabricante() !=null) {
			dto.setFabricanteCNPJ(entidade.getFabricante().getCnpj());
			dto.setFabricanteId(entidade.getFabricante().getId());
			dto.setFabricanteRazaoSocial(entidade.getFabricante().getRazaoSocial());
			dto.setFabricanteNomeFantasia(entidade.getFabricante().getNomeFantasia());
		}
		
		return dto;
	}
}
