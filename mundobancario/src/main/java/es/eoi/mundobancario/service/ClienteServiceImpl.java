
package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.ClienteCuentasSimpleDto;
import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClientePassDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.repository.ClienteRepository;
import es.eoi.mundobancario.util.Util;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository repository;

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

	public List<CuentaDto> findCuentasByIdUsuario(Integer id) {

		List<Cuenta> cuentas = repository.findById(id).get().getCuantas();
		List<CuentaDto> cuentasDto = new ArrayList<CuentaDto>();

		for (Cuenta cuenta : cuentas) {
			cuentasDto.add(Util.convertToCuentaDto(cuenta));
		}

		return cuentasDto;
	}

	public void crearUsuario(ClientePassDto dto) {
		repository.save(Util.convertToCliente(dto));
	}

	public void updateCliente(ClientePassDto dto) {
		Cliente cli = repository.findClienteByUsuario(dto.getUsuario());
		if (cli.getPass().equals(dto.getPass())) {
			cli.setEmail(dto.getEmail());
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

	@Override
	public void crearUsuario(ClienteDto cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCliente(ClienteDto cliente) {
		// TODO Auto-generated method stub
		
	}

}
