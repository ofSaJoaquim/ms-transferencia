package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.service;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.core.queue.config.EventService;
import br.com.branetlogistica.core.queue.model.QueueMessageListener;
import br.com.branetlogistica.ms_transferencia.config.QueueConstants;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.converter.MercadoriaConverter;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto.MercadoriaQueue;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model.Mercadoria;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.repository.MercadoriaRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MercadoriaService  {

	
	private MercadoriaRepository repository;
	private MercadoriaConverter converter;
	private EventService eventService;
	
	@PostConstruct
	public void init() {
		eventService.createAllDirectExchangeListener(QueueConstants.QUEUE_MERCADORIA, QueueConstants.KEY_MERCADORIA,
				QueueConstants.EXCHANGE_CADASTRO, receiver());
	}

	private QueueMessageListener<MercadoriaQueue> receiver() {
		return new QueueMessageListener<MercadoriaQueue>() {

			@Override
			public void queueListener(MercadoriaQueue queue) {
				update(queue);
			}
		};
	}
	
	
	public Mercadoria inserir(MercadoriaQueue queue) {
		Mercadoria entidade = converter.toEntity(queue);
		return repository.save(entidade);
	}
	
	
	public Mercadoria update(MercadoriaQueue queue) {
		if(!repository.existsById(queue.getId()))
			return null;
				
		Mercadoria old = repository.findById(queue.getId()).get();
		if(old.getVersion() >= Long.parseLong(queue.getVersionNumber()))
			return old;
		
		
		old.setNome(queue.getNome());
		old.setExportaHorus(queue.isExportaHorus());
		old.setNumeroCATMAT(queue.getNumeroCatmat());
		old.setRegistroAnvisa(queue.getNumeroRegistroAnvisa());		
		old.setVersion(Long.parseLong(queue.getVersionNumber()));
		return repository.save(old);
	}
	
	
	public Mercadoria selecionarPorId(Long id) {
		if(repository.existsById(id)) 
			 return repository.findById(id).get();
		
		return this.inserir(getSnapshot(id));	
	}
	
	private MercadoriaQueue getSnapshot(Long id) {
		return eventService.getSnapshotEvent(QueueConstants.KEY_MERCADORIA.replace("CADASTRO.", ""), id, MercadoriaQueue.class);
	}
	
	public boolean exist(Mercadoria entidade) {
		return repository.existsById(entidade.getId());
	}
	
	
	
}
