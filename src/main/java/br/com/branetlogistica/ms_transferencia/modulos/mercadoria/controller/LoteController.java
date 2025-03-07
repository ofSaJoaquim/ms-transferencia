package br.com.branetlogistica.ms_transferencia.modulos.mercadoria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.branetlogistica.core.security.annotations.SecurityAnnotationValidation;
import br.com.branetlogistica.ms_transferencia.modulos.mercadoria.service.LoteService;
import jakarta.ws.rs.core.MediaType;

@RestController
@RequestMapping("/lote")
public class LoteController {
	
	@Autowired
	private LoteService service;

	@GetMapping(headers = "X-API-VERSION=1", value = "/lotes_marca_com_entrada/{id}", produces = {MediaType.APPLICATION_JSON})	
	@SecurityAnnotationValidation(validCoastCenter = false, validUser = true)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.findByCentroDistribuicaoId(id));
	}
	
	
}
