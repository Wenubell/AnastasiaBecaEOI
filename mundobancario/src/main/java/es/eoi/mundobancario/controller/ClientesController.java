package es.eoi.mundobancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.service.ClienteService;

@RestController
public class ClientesController {
	
	@Autowired
	ClienteService service;
	
	@GetMapping("clientes")
	@ResponseBody
	public ResponseEntity<List<ClienteDto>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("clientes/{id}")
	@ResponseBody
	public ResponseEntity<ClienteDto> findClienteById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findClienteById(id));
	}
	
	@PostMapping("clientes/login")
	public ResponseEntity<ClienteDto> loginUsuario(@RequestParam String usuario, @RequestParam String pass) {
		return ResponseEntity.ok(service.loginUsuario(usuario, pass));
	}
	
	@GetMapping("clientes/{id}/cuentas")
	@ResponseBody
	public ResponseEntity<ClienteDto> findCuentasUsuario(@PathVariable Integer id) {
		return null; //ResponseEntity.ok(service.findCuentasByIdUsuario(id));
	}
	
	@PutMapping("clientes/{id}")
	public ResponseEntity<String> modifyUsuario(@RequestBody ClienteDto cliente) {
		service.updateCliente(cliente);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("clientes")
	public ResponseEntity<String> addCliente(@RequestBody ClienteDto cliente) {
		service.crearUsuario(cliente);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

}
