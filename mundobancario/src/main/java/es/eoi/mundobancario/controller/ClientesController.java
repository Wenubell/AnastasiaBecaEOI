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
import es.eoi.mundobancario.dto.ClienteSimpleDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.service.ClienteService;

@RestController
public class ClientesController {
	
	@Autowired
	ClienteService serviceCliente;
	
	@GetMapping("clientes")
	@ResponseBody
	public ResponseEntity<List<ClienteSimpleDto>> findAll() {
		return ResponseEntity.ok(serviceCliente.findAll());
	}
	
	@GetMapping("clientes/{id}")
	@ResponseBody
	public ResponseEntity<ClienteSimpleDto> findClienteById(@PathVariable Integer id) {
		return ResponseEntity.ok(serviceCliente.findClienteById(id));
	}
	
	@PostMapping("clientes/login")
	public ResponseEntity<ClienteSimpleDto> loginUsuario(@RequestParam String usuario, @RequestParam String pass) {
		return ResponseEntity.ok(serviceCliente.loginUsuario(usuario, pass));
	}
	
	@GetMapping("clientes/{id}/cuentas")
	@ResponseBody
	public ResponseEntity<List<CuentaDto>> findCuentasUsuario(@PathVariable Integer id) {
		return ResponseEntity.ok(serviceCliente.findCuentasByIdUsuario(id));
		
	}
	
	@PutMapping("clientes/{id}")
	public ResponseEntity<String> modifyUsuario(@RequestBody ClienteDto cliente) {
		serviceCliente.updateCliente(cliente);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("clientes")
	public ResponseEntity<String> addCliente(@RequestBody ClienteDto cliente) {
		serviceCliente.crearUsuario(cliente);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

}
