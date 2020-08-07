package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.ClienteCuentasMovimientosDto;
import es.eoi.mundobancario.dto.ClienteCuentasSimpleDto;
import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClientePassDto;
import es.eoi.mundobancario.dto.CuentaCompletaDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.repository.ClienteRepository;
import es.eoi.mundobancario.util.Util;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository repository;

	public Cliente findClienteByUsuario(String usuario) {
		return repository.findClienteByUsuario(usuario);
	}

	public ClienteDto findClienteById(Integer id) {
		return Util.convertToClienteDto(repository.findById(id).get());
	}

	public List<ClienteDto> findAll() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.addAll(repository.findAll());
		List<ClienteDto> clientesDto = new ArrayList<ClienteDto>();
		for (Cliente cliente : clientes) {
			clientesDto.add(Util.convertToClienteDto(cliente));
		}
		return clientesDto;
	}

	public ClienteDto loginUsuario(String usuario, String pass) {
		Cliente cli = repository.findClienteByUsuario(usuario);
		if (cli.getPass().equals(pass)) {
			return Util.convertToClienteDto(cli);
		}
		return null;
	}

	public List<CuentaCompletaDto> findCuentasByIdUsuario(Integer id) {

		List<Cuenta> cuentas = repository.findById(id).get().getCuantas();
		List<CuentaCompletaDto> cuentasDto = new ArrayList<CuentaCompletaDto>();

		for (Cuenta cuenta : cuentas) {
			cuentasDto.add(Util.convertToCuentaCompletaDto(cuenta));
		}

		return cuentasDto;
	}

	@Override
	public void updateCliente(Integer id, ClientePassDto cliente) {
		Cliente cli = repository.findById(id).get();

		if (cli.getUsuario().equals(cliente.getUsuario()) && cli.getPass().equals(cliente.getPass())) {
			cli.setEmail(cliente.getEmail());
			repository.save(cli);
		}
	}

	public List<ClienteCuentasSimpleDto> findClientesCompletos() {

		List<Cliente> clientes = repository.findAll();
		List<ClienteCuentasSimpleDto> todos = new ArrayList<ClienteCuentasSimpleDto>();

		for (Cliente cliente : clientes) {
			todos.add(Util.convertToClienteCuentasSimple(cliente));
		}

		return todos;
	}

	public boolean crearUsuario(ClientePassDto dto) {
		Cliente u = repository.findClienteByUsuario(dto.getUsuario());
		if( u == null) {
			repository.save(Util.convertToCliente(dto));
			return true;
		}
		return false;
	}

	@Override
	public ClienteCuentasMovimientosDto findClienteCuentasMovimientos(Integer id) {
		Cliente cli = repository.findById(id).get();
		ClienteCuentasMovimientosDto clienteDto = Util.convertToClienteCuentasMovimientosDto(cli);
		return clienteDto;
	}

}
