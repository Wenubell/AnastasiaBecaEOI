package es.eoi.mundobancario.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;

import es.eoi.mundobancario.dto.ClienteCuentasMovimientosDto;
import es.eoi.mundobancario.dto.PrestamoClienteDto;
import es.eoi.mundobancario.dto.PrestamoCuentaClienteDto;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.PrestamoService;
import es.eoi.mundobancario.util.ToPDF;

@RestController
public class ReportsController {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	PrestamoService prestamoService;
	
	@GetMapping("reports/clientes/{id}")
	@ResponseBody
	public ResponseEntity<ClienteCuentasMovimientosDto> findClienteCuentasCompletas(@PathVariable Integer id) {
		return ResponseEntity.ok(clienteService.findClienteCuentasMovimientos(id));
	}
	
	@PostMapping("reports/clientes/{id}")
	public ResponseEntity<String> pdfClienteCompleto(@PathVariable Integer id) throws FileNotFoundException, DocumentException {
		ClienteCuentasMovimientosDto clienteCuentasMovimientos = clienteService.findClienteCuentasMovimientos(id);
		ToPDF.convertReportsCliente(clienteCuentasMovimientos, id);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@GetMapping("reports/prestamos/{id}")
	@ResponseBody
	public ResponseEntity<PrestamoClienteDto> findClientePrestamosCompleto(@PathVariable Integer id) {
		return ResponseEntity.ok(prestamoService.findPrestamoCliente(id));
	}
	
	@PostMapping("reports/prestamos/{id}")
	public ResponseEntity<String> pdfClientePrestamosCompleto(@PathVariable Integer id) throws FileNotFoundException, DocumentException {
		ToPDF.convertReportsPrestamo(prestamoService.findPrestamoCliente(id), id);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@GetMapping("reports/prestamosVivos")
	@ResponseBody
	public ResponseEntity<List<PrestamoCuentaClienteDto>> findAllPrestamosVivos() {
		return ResponseEntity.ok(prestamoService.findAllPrestamosVivos());
	}
	
	@GetMapping("reports/prestamosAmortizados")
	@ResponseBody
	public ResponseEntity<List<PrestamoCuentaClienteDto>> findAllPrestamosAmortizados() {
		return ResponseEntity.ok(prestamoService.findAllPrestamosAmortizados());
	}

}
