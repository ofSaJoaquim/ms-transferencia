package br.com.branetlogistica.ms_transferencia.modulos.centrocusto.converter;

import org.springframework.stereotype.Component;

import br.com.branetlogistica.ms_transferencia.modulos.centrocusto.dto.CentroCustoDTO;
import br.com.branetlogistica.ms_transferencia.modulos.centrocusto.dto.CentroCustoQueue;
import br.com.branetlogistica.ms_transferencia.modulos.centrocusto.model.CentroCusto;
import br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.model.CentroDistribuicao;

@Component
public class CentroCustoConverter {

	public CentroCusto toEntity(CentroCustoQueue queue) {
		return
				CentroCusto.builder()
				.id(queue.getId())
				.version(Long.parseLong(queue.getVersionNumber()))
				.nome(queue.getNome())
				.usaEstoque(queue.getUsaEstoque())
				.centroDistribuicao(CentroDistribuicao.builder().id(queue.getCentroDistribuicaoId()).build())
				.version(Long.valueOf(queue.getVersionNumber()))
				.build();
				
	
	}
	
	public CentroCustoDTO toResponse(CentroCusto entidade) {
		return CentroCustoDTO.builder()
				.id(entidade.getId())
				.nome(entidade.getNome())
				.unidadeId(entidade.getCentroDistribuicao().getId())
				.unidadeNome(entidade.getCentroDistribuicao().getNome())
				.unidadeCnes(entidade.getCentroDistribuicao().getCodigoCnes())
				.build();
	}
	
}
