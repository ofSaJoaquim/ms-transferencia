package br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.service;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.core.queue.config.EventService;
import br.com.branetlogistica.core.queue.model.QueueMessageListener;
import br.com.branetlogistica.ms_transferencia.config.QueueConstants;
import br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.converter.CentroDistribuicaoConverter;
import br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.dto.CentroDistribuicaoQueue;
import br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.model.CentroDistribuicao;
import br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.repository.CentroDistribuicaoRepository;
import br.com.branetlogistica.ms_transferencia.modulos.endereco.service.EnderecoService;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.service.PessoaJuridicaService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CentroDistribuicaoService {

	private EventService eventService;
	private EnderecoService enderecoService;	
	private CentroDistribuicaoConverter converter;
	private CentroDistribuicaoRepository repository;	
	private PessoaJuridicaService pessoaJuridicaService;
	
		
	
	@PostConstruct
	public void init() {
		eventService.createAllDirectExchangeListener(QueueConstants.QUEUE_UNIDADE, QueueConstants.KEY_UNIDADE, QueueConstants.EXCHANGE_CADASTRO, receiver());
	}

	private QueueMessageListener<CentroDistribuicaoQueue> receiver() {
		return new QueueMessageListener<CentroDistribuicaoQueue>() {
			@Override
			public void queueListener(CentroDistribuicaoQueue queue) {
				update(queue);
			}
			
			@Override
			public Class<CentroDistribuicaoQueue> getGenericType() {
	            return CentroDistribuicaoQueue.class;
	        }
		};
	}


	public CentroDistribuicao inserir(CentroDistribuicaoQueue queue) {
		CentroDistribuicao entidade = converter.toEntity(queue);		
		entidade.setPessoaJuridica(pessoaJuridicaService.selecionarPorId(queue.getPessoaJuridicaId()));
		
		if(queue.getEnderecoId() != null)
			entidade.setEndereco(enderecoService.selecionarPorId(queue.getEnderecoId()));
		return repository.save(entidade);
	}
	
	
	public CentroDistribuicao update(CentroDistribuicaoQueue queue) {
		if(!repository.existsById(queue.getId()))
			return null;
		
		CentroDistribuicao old = repository.findById(queue.getId()).get();
		if(old.getVersion() >= Long.parseLong(queue.getVersionNumber()))
			return old;		
		
		old.setNome(queue.getNome());
		old.setVersion(Long.parseLong(queue.getVersionNumber()));
		old.setCodigoCnes(queue.getCnes());
		old.setPessoaJuridica(pessoaJuridicaService.selecionarPorId(queue.getPessoaJuridicaId()));
		if(queue.getEnderecoId() != null)
			old.setEndereco(enderecoService.selecionarPorId(queue.getEnderecoId()));
				
		return repository.save(old);
	}
	
	
	public CentroDistribuicao selecionarPorId(Long id) {
		if(repository.existsById(id)) 
			 return repository.findById(id).get();
		
		return this.inserir(getSnapshot(id));	
	
	}
	
	public boolean exist(CentroDistribuicao entidade) {
		return repository.existsById(entidade.getId());
	}
	
	private CentroDistribuicaoQueue getSnapshot(Long id) {
		return eventService.getSnapshotEvent(QueueConstants.KEY_UNIDADE.replace("CADASTRO.", ""), id, CentroDistribuicaoQueue.class);
	}
}
