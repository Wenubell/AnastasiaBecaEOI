package es.eoi.mundobancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.PrestamoService;

@RestController
public class ReportsController {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	PrestamoService prestamoService;
	
	@GetMapping("reports/clientes/{id}")
	@ResponseBody
	public ResponseEntity<List<ClienteDto>> findClienteCompleto(@PathVariable Integer id) {
		return null;//ResponseEntity.ok(clienteService.findClienteCompleto());
	}
	
	@PostMapping("reports/clientes/{id}")
	public ResponseEntity<String> pdfClienteCompleto(@RequestBody CuentaDto cuenta) {
		//service.crearCuenta(cuenta);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@GetMapping("reports/prestamos/{id}")
	@ResponseBody
	public ResponseEntity<List<ClienteDto>> findClientePrestamosCompleto(@PathVariable Integer id) {
		return null;//ResponseEntity.ok(clienteService.findClienteCompleto());
	}
	
	@PostMapping("reports/prestamos/{id}")
	public ResponseEntity<String> pdfClientePrestamosCompleto(@RequestBody CuentaDto cuenta) {
		//service.crearCuenta(cuenta);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@GetMapping("reports/prestamosVivos")
	@ResponseBody
	public ResponseEntity<List<ClienteDto>> findPrestamosVivos(@PathVariable Integer id) {
		return null;//ResponseEntity.ok(clienteService.findClienteCompleto());
	}
	
	@GetMapping("reports/prestamosAmortizados")
	@ResponseBody
	public ResponseEntity<List<ClienteDto>> findPrestamosAmortizados(@PathVariable Integer id) {
		return null;//ResponseEntity.ok(clienteService.findClienteCompleto());
	}

}
