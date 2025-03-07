package br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.service;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.core.queue.config.EventService;
import br.com.branetlogistica.core.queue.model.QueueMessageListener;
import br.com.branetlogistica.ms_transferencia.config.QueueConstants;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.converter.PessoaJuridicaConverter;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.dto.PessoaJuridicaQueue;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.model.PessoaJuridica;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.repository.PessoaJuridicaRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaJuridicaService  {

	private EventService eventService;
	private PessoaJuridicaConverter converter;
	private PessoaJuridicaRepository repository;
	
	
	@PostConstruct
	public void init() {
		eventService.createAllDirectExchangeListener(QueueConstants.QUEUE_PESSOAJURIDICA, QueueConstants.KEY_PESSOAJURIDICA,
				QueueConstants.EXCHANGE_CADASTRO, centroCustoReceiver());
	}

	private QueueMessageListener<PessoaJuridicaQueue> centroCustoReceiver() {
		return QueueMessageListener.create(PessoaJuridicaQueue.class,	
		 new QueueMessageListener<PessoaJuridicaQueue>() {
			@Override
			public void queueListener(PessoaJuridicaQueue queue) {
				update(queue);
			}
			
			@Override
			public Class<PessoaJuridicaQueue> getGenericType() {
	            return PessoaJuridicaQueue.class;
	        }
		});
	}
	
	public PessoaJuridica inserir(PessoaJuridicaQueue queue) {
		PessoaJuridica entidade  = converter.toEntity(queue);
		return repository.save(entidade);
	}
	
	
	public PessoaJuridica update(PessoaJuridicaQueue queue) {
		if(!repository.existsById(queue.getId()))
			return null;
		
		PessoaJuridica old = repository.findById(queue.getId()).get();
		if(old.getVersion() >= Long.parseLong(queue.getVersionNumber()))
			return old;		
		
		old.setNomeFantasia(queue.getNomeFantasia());
		old.setRazaoSocial(queue.getRazaoSocial());
		old.setVersion(Long.parseLong(queue.getVersionNumber()));
		return repository.save(old);
	}
	
	
	public PessoaJuridica selecionarPorId(Long id) {
		if(repository.existsById(id)) 
			 repository.findById(id).get();
		
		return this.inserir(getSnapshot(id));	
	}
	
	private PessoaJuridicaQueue getSnapshot(Long id) {
		return eventService.getSnapshotEvent(QueueConstants.KEY_PESSOAJURIDICA.replace("CADASTRO.", ""), id, PessoaJuridicaQueue.class);
	}
	
	public boolean exist(PessoaJuridica entidade) {
		return repository.existsById(entidade.getId());
	}
	
	
	
	
	
}
