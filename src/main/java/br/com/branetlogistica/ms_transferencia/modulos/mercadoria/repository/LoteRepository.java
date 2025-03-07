package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model.Lote;

public interface LoteRepository extends JpaRepository<Lote, Long> {

	@Query("select obj.idLoteMarca from Lote obj where obj.centroDistribuicao.id = ?1 and obj.possueEntrada = ?2 ")
	public List<Long> findByCentroDistribuicaoId(Long centroDistribuicaoId, boolean possueEntrada );
	
	public List<Lote> findByIdIn(List<Long>ids);
	
}
