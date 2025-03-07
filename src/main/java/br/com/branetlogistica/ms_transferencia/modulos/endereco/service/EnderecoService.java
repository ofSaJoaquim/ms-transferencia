package br.com.branetlogistica.ms_transferencia.modulos.endereco.service;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.core.queue.config.EventService;
import br.com.branetlogistica.core.queue.model.QueueMessageListener;
import br.com.branetlogistica.ms_transferencia.config.QueueConstants;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.converter.EnderecoConverter;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.dto.EnderecoQueue;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.model. Endereco;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.repository. EnderecoRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnderecoService  {
	
	private EventService eventService;
	private CidadeService cidadeService;
	private EnderecoConverter converter;
	private EnderecoRepository repository;
		
	@PostConstruct
	public void init() {
		eventService.createAllDirectExchangeListener(
				QueueConstants.QUEUE_ENDERECO, 
				QueueConstants.KEY_ENDERECO,
				QueueConstants.EXCHANGE_CADASTRO, receiver());
	}

	private QueueMessageListener<EnderecoQueue> receiver() {
		return new QueueMessageListener<EnderecoQueue>() {
			@Override
			public void queueListener(EnderecoQueue queue) {
				update(queue);
			}
			
			@Override
			public Class<EnderecoQueue> getGenericType() {
	            return EnderecoQueue.class;
	        }
		};
	}
	

	
	public  Endereco inserir(EnderecoQueue queue) {
		Endereco entidade = converter.toEntity(queue);		
		entidade.setCidade(cidadeService.selecionarPorId(queue.getCidadeId()));
		return repository.save(entidade);
	}
	
	
	public Endereco update(EnderecoQueue queue) {
		if(!repository.existsById(queue.getId())) 
			return null;
				
		Endereco old = repository.findById(queue.getId()).get();
		if(old.getVersion() >= Long.parseLong(queue.getVersionNumber()))
			return old;		
		old.setCidade(cidadeService.selecionarPorId(queue.getCidadeId()));
		old.setVersion(Long.parseLong(queue.getVersionNumber()));
		return repository.save(old);
	}
	
	
	public Endereco selecionarPorId(Long id) {
		if(repository.existsById(id)) 
			 return repository.findById(id).get();
		
		return this.inserir(getSnapshot(id));	
	}
	
	public boolean exist( Endereco entidade) {
		return repository.existsById(entidade.getId());
	}
	
	
	
	private EnderecoQueue getSnapshot(Long id) {
		return eventService.getSnapshotEvent(QueueConstants.KEY_ENDERECO.replace("CADASTRO.", ""), id, EnderecoQueue.class);
	}

	
	
}
