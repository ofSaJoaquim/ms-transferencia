package br.com.branetlogistica.ms_transferencia.modulos.requisicao.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaIntegracaoCadastroRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.service.RequisicaoTransferenciaIntegracaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/integracao/requisicao")
@AllArgsConstructor
public class RequisicaoTransferenciaIntegracaoController {

	private RequisicaoTransferenciaIntegracaoService service;
	
	@PostMapping(headers = "X-API-VERSION=1")		
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> insertV1(@Valid @RequestBody RequisicaoTransferenciaIntegracaoCadastroRequest request) {		
		return new ResponseEntity<>(service.insert(request), HttpStatus.CREATED);
	}
	
	
	
}
