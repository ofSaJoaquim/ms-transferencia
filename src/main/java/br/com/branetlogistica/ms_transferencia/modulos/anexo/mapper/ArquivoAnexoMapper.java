package br.com.branetlogistica.ms_transferencia.modulos.anexo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.branetlogistica.ms_transferencia.modulos.anexo.dto.ArquivoAnexoDTO;
import br.com.branetlogistica.ms_transferencia.modulos.anexo.dto.ArquivoAnexoResponse;
import br.com.branetlogistica.ms_transferencia.modulos.anexo.model.ArquivoAnexo;

@Component
public class ArquivoAnexoMapper {

	
	public ArquivoAnexoResponse toResponse(ArquivoAnexo entity) {
		if(entity == null)
			return null;
		return ArquivoAnexoResponse.builder()
		.id(entity.getId())
		.version(entity.getVersion().toString())
		.dadosCodificado(entity.getDadosCodificado())
		.formato(entity.getFormato())
		.nome(entity.getNome())
		.build();
	}
	
	public ArquivoAnexoDTO toDTO(ArquivoAnexo entity) {
		if(entity == null)
			return null;
		return ArquivoAnexoDTO.builder()
		.id(entity.getId())
		.version(entity.getVersion().toString())		
		.formato(entity.getFormato())
		.nome(entity.getNome())
		.build();
	}
	
	public List<ArquivoAnexoDTO> toDTO(List<ArquivoAnexo> entities) {
		if(entities == null)
			return null;
		
		if(entities.isEmpty())
			return new ArrayList<ArquivoAnexoDTO>();
		
		return entities.stream().map(entity -> toDTO(entity)).toList();
	}
	
	
}
