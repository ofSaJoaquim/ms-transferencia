package br.com.branetlogistica.ms_transferencia.modulos.centrocusto.service;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.core.queue.config.EventService;
import br.com.branetlogistica.core.queue.model.QueueMessageListener;
import br.com.branetlogistica.ms_transferencia.config.QueueConstants;
import br.com.branetlogistica.ms_transferencia.modulos.centrocusto.converter.CentroCustoConverter;
import br.com.branetlogistica.ms_transferencia.modulos.centrocusto.dto.CentroCustoQueue;
import br.com.branetlogistica.ms_transferencia.modulos.centrocusto.model.CentroCusto;
import br.com.branetlogistica.ms_transferencia.modulos.centrocusto.repository.CentroCustoRepository;
import br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.service.CentroDistribuicaoService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CentroCustoService {

	private EventService eventService;
	private CentroCustoConverter converter;
	private CentroCustoRepository repository;	
	private CentroDistribuicaoService centroDistribuicaoService;
			
	
	@PostConstruct
	public void init() {
		eventService.createAllDirectExchangeListener(QueueConstants.QUEUE_CENTROCUSTO, QueueConstants.KEY_CENTROCUSTO,
				QueueConstants.EXCHANGE_CADASTRO, centroCustoReceiver());
	}

	private QueueMessageListener<CentroCustoQueue> centroCustoReceiver() {
		return new QueueMessageListener<CentroCustoQueue>() {
			@Override
			public void queueListener(CentroCustoQueue queue) {
				update(queue);
			}
			
			@Override
			public Class<CentroCustoQueue> getGenericType() {
	            return CentroCustoQueue.class;
	        }
		};
	}

			
	public CentroCusto inserir(CentroCustoQueue queue) {
		CentroCusto entidade= converter.toEntity(queue);
		entidade.setCentroDistribuicao(centroDistribuicaoService.selecionarPorId(entidade.getCentroDistribuicao().getId()));
		return repository.save(entidade);
	}
	
	
	public CentroCusto update(CentroCustoQueue queue) {
		if(!repository.existsById(queue.getId())) 
			return null;
		
		CentroCusto old =  repository.findById(queue.getId()).get();
		if(old.getVersion().equals(Long.parseLong(queue.getVersionNumber())))
			return old;
				
		old.setNome(queue.getNome());
		old.setVersion(Long.parseLong(queue.getVersionNumber()));
		old.setCentroDistribuicao(centroDistribuicaoService.selecionarPorId(queue.getCentroDistribuicaoId()));
		return repository.save(old);
	}
	
	
	public CentroCusto selecionarPorId(Long id) {
		if(repository.existsById(id)) 
			 return repository.findById(id).get();
		
		return this.inserir(this.getSnapshot(id));	
	}
	
	public boolean exist(CentroCusto entidade) {
		return repository.existsById(entidade.getId());
	}
	
	private CentroCustoQueue getSnapshot(Long id) {
		return eventService.getSnapshotEvent("centro_custo", id, CentroCustoQueue.class);
	}
	
}
