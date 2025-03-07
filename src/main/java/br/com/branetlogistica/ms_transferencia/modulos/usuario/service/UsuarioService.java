package br.com.branetlogistica.ms_transferencia.modulos.usuario.service;

import org.springframework.stereotype.Service;

import br.com.branetlogistica.core.queue.config.EventService;
import br.com.branetlogistica.core.queue.model.QueueMessageListener;
import br.com.branetlogistica.ms_transferencia.config.QueueConstants;
import br.com.branetlogistica.ms_transferencia.modulos.usuario.converter.UsuarioConverter;
import br.com.branetlogistica.ms_transferencia.modulos.usuario.dto.UsuarioQueue;
import br.com.branetlogistica.ms_transferencia.modulos.usuario.model.Usuario;
import br.com.branetlogistica.ms_transferencia.modulos.usuario.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

	private EventService eventService;
	private UsuarioConverter converter;
	private UsuarioRepository repository;
	
	@PostConstruct
	public void init() {
		eventService.createAllDirectExchangeListener(QueueConstants.QUEUE_USUARIO, QueueConstants.KEY_USUARIO,
				QueueConstants.EXCHANGE_CADASTRO, centroCustoReceiver());
	}

	private QueueMessageListener<UsuarioQueue> centroCustoReceiver() {
		return new QueueMessageListener<UsuarioQueue>() {

			@Override
			public void queueListener(UsuarioQueue queue) {
				update(queue);
			}
			
			@Override
			public Class<UsuarioQueue> getGenericType() {
	            return UsuarioQueue.class;
	        }
		};
	}
	
	
	public Usuario inserir(UsuarioQueue queue) {	
		Usuario entidade = converter.toEntity(queue);
		return repository.save(entidade);
	}
	
	public Usuario update(UsuarioQueue queue) {
		if(!repository.existsById(queue.getId()))
			return null;
				
		Usuario old =  repository.findById(queue.getId()).get();
		if(old.getVersion() >= Long.parseLong(queue.getVersionNumber()))
			return old;
		old.setLogin(queue.getLogin());
		old.setEmail(queue.getEmail());
		old.setVersion(Long.parseLong(queue.getVersionNumber()));		
		return repository.save(old);
	}
	
	
	public Usuario selecionarPorId(Long id) {
		if(repository.existsById(id)) 
			 return repository.findById(id).get();
		
		return this.inserir(getSnapshot(id));	
	}
	
	private UsuarioQueue getSnapshot(Long id) {
		return eventService.getSnapshotEvent(QueueConstants.KEY_USUARIO.replace("CADASTRO.", ""), id, UsuarioQueue.class);
	}
	
	public boolean exist(Usuario entidade) {
		return repository.existsById(entidade.getId());
	}
	
}
