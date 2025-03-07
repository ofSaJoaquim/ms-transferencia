package br.com.branetlogistica.ms_transferencia.modulos.requisicao.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaAprovarRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaAutorizarRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaCadastroRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaColetaRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaInativacaoRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaRecebimentoRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.dto.request.RequisicaoTransferenciaRejeitarRequest;
import br.com.branetlogistica.ms_transferencia.modulos.requisicao.service.RequisicaoTransferenciaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/requisicao")
@AllArgsConstructor
public class RequisicaoTransferenciaController {

	private RequisicaoTransferenciaService service;
	
	@PostMapping(headers = "X-API-VERSION=1")		
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> insertV1(@Valid @RequestBody RequisicaoTransferenciaCadastroRequest request) {		
		return new ResponseEntity<>(service.insert(request), HttpStatus.CREATED);
	}
	
	@PutMapping(headers = "X-API-VERSION=1", path = "/{id}/autorizar")		
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> autorizarV1(@Valid @RequestBody RequisicaoTransferenciaAutorizarRequest request, @PathVariable("id")Long id) {		
		return new ResponseEntity<>(service.autorizar(id,request), HttpStatus.OK);
	}
	
	@PutMapping(headers = "X-API-VERSION=1", path = "/{id}/aprovar")		
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> aprovarV1(@Valid @RequestBody RequisicaoTransferenciaAprovarRequest request, @PathVariable("id")Long id) {		
		return new ResponseEntity<>(service.aprovar(id,request), HttpStatus.OK);
	}
	
	@PutMapping(headers = "X-API-VERSION=1", path = "/{id}/coletar")		
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> coletarV1(@Valid @RequestBody RequisicaoTransferenciaColetaRequest request, @PathVariable("id")Long id) {		
		return new ResponseEntity<>(service.coletar(id,request), HttpStatus.OK);
	}
	
	@PutMapping(headers = "X-API-VERSION=1", path = "/{id}/receber")		
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> receberV1(@Valid @RequestBody RequisicaoTransferenciaRecebimentoRequest request, @PathVariable("id")Long id) {		
		return new ResponseEntity<>(service.receber(id,request), HttpStatus.OK);
	}
	
	@PutMapping(headers = "X-API-VERSION=1", path = "/{id}/inativar")		
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> inativarV1(@Valid @RequestBody RequisicaoTransferenciaInativacaoRequest request, @PathVariable("id")Long id) {		
		return new ResponseEntity<>(service.inativar(id,request), HttpStatus.OK);
	}
	
	@PutMapping(headers = "X-API-VERSION=1", path = "/{id}/rejeitar")		
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> rejeitarV1(@Valid @RequestBody RequisicaoTransferenciaRejeitarRequest request, @PathVariable("id")Long id) {		
		return new ResponseEntity<>(service.rejeitar(id,request), HttpStatus.OK);
	}
	
	
}
