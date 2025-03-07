package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.branetlogistica.core.util.DateTimeUtil;
import br.com.branetlogistica.core.util.Util;
import br.com.branetlogistica.ms_transferencia.modulos.centrodistribuicao.model.CentroDistribuicao;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto.LoteQueue;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.dto.LoteResponse;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model.ConfiguracaoMercadoria;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model.Lote;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.model.Mercadoria;
import br.com.branetlogistica.ms_transferencia.modulos.pessoajuridica.model.PessoaJuridica;

@Component
public class LoteConverter {

	public Lote toEntity(LoteQueue queue) {
		Lote entidade = 
				Lote.builder()
				.nome(queue.getNome())
//				.validade(queue.getValidade()!=null?LocalDate.parse(queue.getValidade(),DateTimeFormatter.ISO_DATE_TIME):null)
				.validade(this.getDataValidade(queue.getValidade()))
				.mercadoria(new Mercadoria(queue.getMercadoriaId()))
				.idCodigoBarras(queue.getIdCodigoBarras())
				.configuracaoMercadoria(new ConfiguracaoMercadoria(queue.getConfiguracaoMercadoriaId()))
				.valorUnitario(queue.getValorUnitario())
				.idLoteMarca(queue.getIdLoteMarca())
				.centroDistribuicao(new CentroDistribuicao(queue.getCentroDistribuicaoId()))
				.fornecedor(Util.isValidId(queue.getFornecedorId())?new PessoaJuridica(queue.getFornecedorId()):null)
				.build();
		
		entidade.setId(queue.getId());
		entidade.setVersion(Long.parseLong(queue.getVersionNumber()));
		return entidade;		
	}
	
	public List<Lote> toEntity(List<LoteQueue> dtos) {
		List<Lote> entities = new ArrayList<Lote>();
		for(LoteQueue dto : dtos)
			entities.add(toEntity(dto));
		return entities;
	}
	
	private LocalDate getDataValidade(String validade) {
		if (Util.isValidaString(validade)) {
			String[] dataValidade = validade.split("-");
			if (dataValidade.length >= 1) {
				String anoTexto = dataValidade[0];
				int ano = Integer.parseInt(anoTexto);
				if(ano < 2100) {
					return LocalDate.parse(validade,DateTimeFormatter.ISO_DATE);
				}
			}
			System.out.println("Data de validade inválida: " + validade);
		}
		return null;
	}
	
	public LoteResponse toResponse(Lote entidade) {
		return LoteResponse.builder()
				.id(entidade.getId())
				.idCodigoBarras(entidade.getIdCodigoBarras())
				.nome(entidade.getNome())
				.validade(DateTimeUtil.toISO8601(entidade.getValidade()))
				.version(entidade.getVersion().toString())
				.build();
	}
	
}
