package br.com.branetlogistica.ms_transferencia.modulos.endereco.service;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.core.queue.config.EventService;
import br.com.branetlogistica.core.queue.model.QueueMessageListener;
import br.com.branetlogistica.ms_transferencia.config.QueueConstants;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.converter.CidadeConverter;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.dto.CidadeQueue;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.model.Cidade;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.repository.CidadeRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CidadeService  {

	
	private CidadeRepository repository;
	private EstadoService estadoService;		
	private CidadeConverter converter;	
	private EventService eventService;
	
	@PostConstruct
	public void init() {
		eventService.createAllDirectExchangeListener(
				QueueConstants.QUEUE_CIDADE, 
				QueueConstants.KEY_CIDADE,
				QueueConstants.EXCHANGE_CADASTRO, receiver());
	}

	private QueueMessageListener<CidadeQueue> receiver() {
		return new QueueMessageListener<CidadeQueue>() {

			@Override
			public void queueListener(CidadeQueue queue) {
				update(queue);
			}
			
			@Override
			public Class<CidadeQueue> getGenericType() {
	            return CidadeQueue.class;
	        }
		};
	}
	
	
	public Cidade inserir(CidadeQueue queue) {
		Cidade entidade = converter.toEntity(queue);		
		entidade.setEstado(estadoService.selecionarPorId(queue.getEstadoId()));
		return repository.save(entidade);
	}
	
	
	public Cidade update(CidadeQueue queue) {
		if(!repository.existsById(queue.getId()))
			return null;
		
		Cidade old = repository.findById(queue.getId()).get();
		if(old.getVersion() >= Long.parseLong(queue.getVersionNumber()))
			return old;
		
		old.setNome(queue.getNome());
		old.setVersion(Long.parseLong(queue.getVersionNumber()));
		old.setEstado(estadoService.selecionarPorId(queue.getEstadoId()));
		return repository.save(old);
	}
	
	
	public Cidade selecionarPorId(Long id) {
		if(repository.existsById(id)) 
			return repository.findById(id).get();
		
		return this.inserir(getSnapshot(id));	
	}
	
	public boolean exist(Cidade entidade) {
		return repository.existsById(entidade.getId());
	}
	
	private CidadeQueue getSnapshot(Long id) {
		return eventService.getSnapshotEvent(QueueConstants.KEY_CIDADE.replace("CADASTRO.", ""), id, CidadeQueue.class);
	}
		
}
