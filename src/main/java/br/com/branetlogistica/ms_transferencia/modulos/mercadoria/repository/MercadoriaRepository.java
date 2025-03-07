package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.repository;	

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model.Mercadoria;

public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long> {

}
