package br.com.branetlogistica.ms_transferencia.modulos.requisicao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.branetlogistica.ms_transferencia.modulos.requisicao.model.RequisicaoTransferenciaItem;

public interface RequisicaoTransferenciaItemRepository extends JpaRepository<RequisicaoTransferenciaItem,Long> {

}
