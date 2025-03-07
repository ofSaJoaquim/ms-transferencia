package br.com.branetlogistica.ms_transferencia.modulos.anexo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.branetlogistica.ms_transferencia.modulos.anexo.service.ArquivoAnexoService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/arquivo_anexo")
@AllArgsConstructor
public class ArquivoAnexoController {

	private ArquivoAnexoService service;

	@GetMapping(path = "/{id}", headers = "X-API-VERSION=1")
	// @PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.findByIdResponse(id), HttpStatus.OK);
	}
	
}