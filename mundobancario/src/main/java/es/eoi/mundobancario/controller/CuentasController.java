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

import es.eoi.mundobancario.dto.ClienteCuentasSimpleDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.PrestamoService;

@RestController
public class CuentasController {

	@Autowired
	CuentaService serviceCuentas;

	@Autowired
	ClienteService serviceClientes;
	
	@Autowired
	PrestamoService servicePrestamo;

	@GetMapping("cuentas")
	@ResponseBody
	public ResponseEntity<List<ClienteCuentasSimpleDto>> findClientesCompletos() {
		return ResponseEntity.ok(serviceClientes.findClientesCompletos());
	}

	@GetMapping("cuentas/deudoras")
	@ResponseBody
	public ResponseEntity<List<CuentaDto>> findAllDeudoras() {
		return ResponseEntity.ok(serviceCuentas.findAllDeudoras());
	}

	@GetMapping("cuentas/{id}")
	@ResponseBody
	public ResponseEntity<CuentaDto> findClienteById(@PathVariable Integer id) {
		return ResponseEntity.ok(serviceCuentas.findCuentaDtoById(id));
	}

	@PostMapping("cuentas")
	public ResponseEntity<String> addCliente(@RequestBody CuentaDto cuenta) {
		serviceCuentas.crearCuenta(cuenta);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PutMapping("cuentas/{id}")
	public ResponseEntity<String> modifyUsuario(@PathVariable Integer id, @RequestBody CuentaDto cuenta) {
		serviceCuentas.updateAliasCuenta(id, cuenta);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@GetMapping("cuentas/{id}/movimientos")
	@ResponseBody
	public ResponseEntity<List<MovimientoDto>> findMovimientosCuenta(@PathVariable Integer id) {
		return ResponseEntity.ok(serviceCuentas.findMovimientosCuentaByIdCuenta(id));
	}

	@GetMapping("cuentas/{id}/prestamos")
	@ResponseBody
	public ResponseEntity<List<PrestamoDto>> findPrestamosCuenta(@PathVariable Integer id) {
		return ResponseEntity.ok(serviceCuentas.findPrestamosByIdCuenta(id));
	}

	@GetMapping("cuentas/{id}/prestamosVivos")
	@ResponseBody
	public ResponseEntity<List<PrestamoDto>> findPrestamosVivosCuenta(@PathVariable Integer id) {
		return ResponseEntity.ok(serviceCuentas.findPrestamosVivosByIdCuenta(id));
	}
	
	@GetMapping("cuentas/{id}/prestamosAmortizados")
	@ResponseBody
	public ResponseEntity<List<PrestamoDto>> findPrestamosAmortizadosCuenta(@PathVariable Integer id) {
		return ResponseEntity.ok(serviceCuentas.findPrestamosAmortizadosByIdCuenta(id));
	}

	@PostMapping("cuentas/{id}/prestamos")
	public ResponseEntity<String> addPrestamo(@PathVariable Integer id, @RequestBody PrestamoDto prestamo) {
		Cuenta cuenta = serviceCuentas.findCuentaById(id);
		Prestamo pres = servicePrestamo.creaPrestamo(prestamo, cuenta);
		serviceCuentas.addPrestamo(id, pres);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PostMapping("cuentas/{id}/ingresos")
	public ResponseEntity<String> addIngreso(@PathVariable Integer id,  @RequestParam Double importe) {
		serviceCuentas.crearIngreso(id, importe);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PostMapping("cuentas/{id}/pagos")
	public ResponseEntity<String> addPago(@PathVariable Integer id,  @RequestParam Double importe) {
		serviceCuentas.crearPago(id, importe);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PostMapping("cuentas/ejecutarAmortizacionesDiarias")
	public ResponseEntity<String> ejecutarAmortizacionesDiarias(@RequestBody CuentaDto cuenta) {
		// service.crearCuenta(cuenta);
		return null;
	}

}
