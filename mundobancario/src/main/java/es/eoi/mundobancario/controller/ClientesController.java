package es.eoi.mundobancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.service.ClienteService;

@RestController
public class ClientesController {
	
	@Autowired
	ClienteService service;
	
	@GetMapping("cliente/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> findClienteById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findClienteById(id));
	}

}
