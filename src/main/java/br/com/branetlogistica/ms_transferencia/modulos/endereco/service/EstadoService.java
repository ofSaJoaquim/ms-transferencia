package br.com.branetlogistica.ms_transferencia.modulos.endereco.service;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.core.queue.config.EventService;
import br.com.branetlogistica.core.queue.model.QueueMessageListener;
import br.com.branetlogistica.ms_transferencia.config.QueueConstants;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.converter.EstadoConverter;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.dto.EstadoQueue;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.model.Estado;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.repository.EstadoRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EstadoService  {

	private EventService eventService;
	private EstadoConverter converter;
	private EstadoRepository repository;
	
	
	@PostConstruct
	public void init() {
		eventService.createAllDirectExchangeListener(
				QueueConstants.QUEUE_ESTADO, 
				QueueConstants.KEY_ESTADO,
				QueueConstants.EXCHANGE_CADASTRO, receiver());
	}

	private QueueMessageListener<EstadoQueue> receiver() {
		return new QueueMessageListener<EstadoQueue>() {
			@Override
			public void queueListener(EstadoQueue queue) {
				update(queue);
			};
			
			@Override
			public Class<EstadoQueue> getGenericType() {
	            return EstadoQueue.class;
	        }
			
		};
	}
		
	
	public Estado inserir(EstadoQueue queue) {
		Estado entidade = this.converter.toEntity(queue);
		return repository.save(entidade);
	}
		
	public Estado update(EstadoQueue queue) {
		if(!repository.existsById(queue.getId()))
			return null;
		
		Estado old =  repository.findById(queue.getId()).get();
		if(old.getVersion() >= Long.parseLong(queue.getVersionNumber()))
			return old;
		
		old.setNome(queue.getNome());
		old.setUf(queue.getUf());
		old.setVersion(Long.parseLong(queue.getVersionNumber()));
		return repository.save(old);
	}
		
	public Estado selecionarPorId(Long id) {
		if(repository.existsById(id)) 
			 return repository.findById(id).get();
		
		return this.inserir(getSnapshot(id));	
	}
	
	public boolean exist(Estado entidade) {
		return repository.existsById(entidade.getId());
	}
	
	private EstadoQueue getSnapshot(Long id) {
		return eventService.getSnapshotEvent(QueueConstants.KEY_ESTADO.replace("CADASTRO.", ""), id, EstadoQueue.class);
	}
}
