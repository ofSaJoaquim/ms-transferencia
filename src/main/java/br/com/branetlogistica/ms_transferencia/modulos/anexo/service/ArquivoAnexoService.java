package br.com.branetlogistica.ms_transferencia.modulos.anexo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.core.exceptions.impl.NotFoundException;
import br.com.branetlogistica.ms_transferencia.modulos.anexo.dto.ArquivoAnexoRequest;
import br.com.branetlogistica.ms_transferencia.modulos.anexo.dto.ArquivoAnexoResponse;
import br.com.branetlogistica.ms_transferencia.modulos.anexo.mapper.ArquivoAnexoMapper;
import br.com.branetlogistica.ms_transferencia.modulos.anexo.model.ArquivoAnexo;
import br.com.branetlogistica.ms_transferencia.modulos.anexo.repository.ArquivoAnexoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArquivoAnexoService {

	private ArquivoAnexoRepository repository;
	private ArquivoAnexoMapper mapper;
	
	
	public ArquivoAnexo inserir(ArquivoAnexoRequest request) {
		ArquivoAnexo entity = new ArquivoAnexo();
		entity.setFormato(request.getFormato());
		entity.setDadosCodificado(request.getDadosCodificado());
		entity.setNome(request.getNome());
		return repository.save(entity);
	}
	
	public ArquivoAnexoResponse findByIdResponse(Long id) {
		return mapper.toResponse(this.findById(id));
	}
	
	public ArquivoAnexo findById(Long id) {
		Optional<ArquivoAnexo> obj = repository.findById(id);
		return obj.orElseThrow(() -> new NotFoundException("Não foi encontrado anexo com o id = "+id));
	}
	
}
