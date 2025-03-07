package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.service;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.core.queue.config.EventService;
import br.com.branetlogistica.core.queue.model.QueueMessageListener;
import br.com.branetlogistica.ms_transferencia.config.QueueConstants;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.converter.ConfiguracaoMercadoriaConverter;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto.ConfiguracaoMercadoriaQueue;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model.ConfiguracaoMercadoria;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.repository.ConfiguracaoMercadoriaRepository;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.service.PessoaJuridicaService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfiguracaoMercadoriaService  {

	private EventService eventService;
	private MercadoriaService mercadoriaService;	
	private ConfiguracaoMercadoriaConverter converter;
	private ConfiguracaoMercadoriaRepository repository;		
	private PessoaJuridicaService pessoaJuridicaService;
	
	
	@PostConstruct
	public void init() {
		eventService.createAllDirectExchangeListener(QueueConstants.QUEUE_CONFIGURACAOMERCADORIA, QueueConstants.KEY_CONFIGURACAOMERCADORIA,
				QueueConstants.EXCHANGE_CADASTRO, centroCustoReceiver());
	}

	private QueueMessageListener<ConfiguracaoMercadoriaQueue> centroCustoReceiver() {
		return new QueueMessageListener<ConfiguracaoMercadoriaQueue>() {
			@Override
			public void queueListener(ConfiguracaoMercadoriaQueue queue) {
				update(queue);
			}
		};
	}
		
	
	public ConfiguracaoMercadoria inserir(ConfiguracaoMercadoriaQueue queue) {
		ConfiguracaoMercadoria entidade = this.converter.toEntity(queue);
		
		entidade.setFabricante(pessoaJuridicaService.selecionarPorId(queue.getFabricanteId()));
		entidade.setMercadoria(mercadoriaService.selecionarPorId(queue.getMercadoriaId()));
		return repository.save(entidade);
	}
	
	
	public ConfiguracaoMercadoria update(ConfiguracaoMercadoriaQueue queue) {
		if(!repository.existsById(queue.getId()))
			return null;
		
		ConfiguracaoMercadoria old = repository.findById(queue.getId()).get();
		if(old.getVersion() >= Long.parseLong(queue.getVersionNumber()))
			return old;
		
		ConfiguracaoMercadoria entidade = this.converter.toEntity(queue);
		
		entidade.setMercadoria(mercadoriaService.selecionarPorId(queue.getMercadoriaId()));
		old.setQuantidade(entidade.getQuantidade());
		old.setCodigoBarrasFabricante(entidade.getCodigoBarrasFabricante());
		old.setRegistroAnvisa(entidade.getRegistroAnvisa());
		old.setVersion(entidade.getVersion());
		return repository.save(old);
	}
	
	
	public ConfiguracaoMercadoria selecionarPorId(Long id) {
		if(repository.existsById(id)) 
			return repository.findById(id).get();
		
		return this.inserir(getSnapshot(id));	
	}
	
	private ConfiguracaoMercadoriaQueue getSnapshot(Long id) {
		return eventService.getSnapshotEvent(QueueConstants.KEY_CONFIGURACAOMERCADORIA.replace("CADASTRO.", ""), id, ConfiguracaoMercadoriaQueue.class);
	}
	
	

	
}
