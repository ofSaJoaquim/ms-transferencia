package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.core.queue.config.EventService;
import br.com.branetlogistica.core.queue.model.QueueMessageListener;
import br.com.branetlogistica.ms_transferencia.config.QueueConstants;
import br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.service.CentroDistribuicaoService;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.converter.LoteConverter;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto.LoteQueue;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model.Lote;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.repository.LoteRepository;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.service.PessoaJuridicaService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoteService  {

	
	private LoteRepository repository;	
	private MercadoriaService mercadoriaService;	
	private ConfiguracaoMercadoriaService configuracaoMercadoriaService;
	private CentroDistribuicaoService centroDistribuicaoService;
	private PessoaJuridicaService pessoaJuridicaService;
	private LoteConverter converter;
	private EventService eventService;
	
	@PostConstruct
	public void init() {
		eventService.createAllDirectExchangeListener(QueueConstants.QUEUE_LOTE, QueueConstants.KEY_LOTE,
				QueueConstants.EXCHANGE_CADASTRO, receiver());
	}

	private QueueMessageListener<LoteQueue> receiver() {
		return new QueueMessageListener<LoteQueue>() {
			@Override
			public void queueListener(LoteQueue queue) {
				update(queue);
			}
		};
	}
	
	
	public Lote inserir(LoteQueue queue) {
		Lote entidade = converter.toEntity(queue);
		
		entidade.setPossueEntrada(false);
		entidade.setMercadoria(mercadoriaService.selecionarPorId(queue.getMercadoriaId()));
		entidade.setConfiguracaoMercadoria(configuracaoMercadoriaService.selecionarPorId(queue.getConfiguracaoMercadoriaId()));
		entidade.setCentroDistribuicao(centroDistribuicaoService.selecionarPorId(queue.getCentroDistribuicaoId()));
		if(queue.getFornecedorId() !=null)
			entidade.setFornecedor(pessoaJuridicaService.selecionarPorId(queue.getFornecedorId()));
		return repository.save(entidade);
	}
	
	
	public Lote update(LoteQueue queue) {
		if(!repository.existsById(queue.getId()))
			return null;
		
		Lote old = repository.findById(queue.getId()).get();
		if(old.getVersion() >= Long.parseLong(queue.getVersionNumber()))
			return old;
		
		Lote entidade = converter.toEntity(queue);		
		old.setCentroDistribuicao(centroDistribuicaoService.selecionarPorId(queue.getCentroDistribuicaoId()));	
		old.setMercadoria(mercadoriaService.selecionarPorId(queue.getMercadoriaId()));
		old.setValidade(entidade.getValidade());
		old.setIdCodigoBarras(entidade.getIdCodigoBarras());
		old.setNome(entidade.getNome());
		old.setVersion(entidade.getVersion());
		old.setIdLoteMarca(entidade.getIdLoteMarca());
		
		if(queue.getFornecedorId() != null)
			old.setFornecedor(pessoaJuridicaService.selecionarPorId(queue.getFornecedorId()));
		
		return repository.save(old);
	}
	
	
	public Lote selecionarPorId(Long id) {
		if(repository.existsById(id)) 
			 return repository.findById(id).get();
		
		return this.inserir(getSnapshot(id));	
	}
	
	private LoteQueue getSnapshot(Long id) {
		return eventService.getSnapshotEvent(QueueConstants.KEY_LOTE.replace("CADASTRO.", ""), id, LoteQueue.class);
	}
	
	public boolean exist(Lote entidade) {
		return repository.existsById(entidade.getId());
	}
	
	public List<Long> findByCentroDistribuicaoId(Long centroDistribuicaoId){
		return repository.findByCentroDistribuicaoId(centroDistribuicaoId,true);
	}
	
	
	
}
