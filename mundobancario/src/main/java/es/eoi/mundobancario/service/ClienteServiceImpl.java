
package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClienteSimpleDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.repository.ClienteRepository;
import es.eoi.mundobancario.util.Util;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository repository;

	public ClienteSimpleDto findClienteById(Integer id) {
		return Util.convertToClienteSimpleDto(repository.findById(id).get());
	}

	public List<ClienteSimpleDto> findAll() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.addAll(repository.findAll());
		List<ClienteSimpleDto> clientesDto = new ArrayList<ClienteSimpleDto>();
		for (Cliente cliente : clientes) {
			clientesDto.add(Util.convertToClienteSimpleDto(cliente));
		}
		return clientesDto;
	}

	public ClienteSimpleDto loginUsuario(String usuario, String pass) {
		Cliente cli = repository.findClienteByUsuario(usuario);
		if (cli.getPass().equals(pass)) {
			return Util.convertToClienteSimpleDto(cli);
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

	public void crearUsuario(ClienteDto dto) {
		repository.save(Util.convertToCliente(dto));
	}

	public void updateCliente(ClienteDto dto) {
		Cliente cli = repository.findClienteByUsuario(dto.getUsuario());
		if (cli.getPass().equals(dto.getPass())) {
			cli.setEmail(dto.getEmail());
			repository.save(cli);
		}
	}

	public HashMap<ClienteSimpleDto, List<CuentaDto>> findClientesCompletos() {

		List<Cliente> clientes = repository.findAll();
		List<CuentaDto> cuentasDto = new ArrayList<CuentaDto>();
		HashMap<ClienteSimpleDto, List<CuentaDto>> todos = new HashMap<ClienteSimpleDto, List<CuentaDto>>();

		for (Cliente cliente : clientes) {

			for (Cuenta cuenta : cliente.getCuantas()) {
				cuentasDto.add(Util.convertToCuentaDto(cuenta));
			}

			todos.put(Util.convertToClienteSimpleDto(cliente), cuentasDto);
			cuentasDto.clear();
		}

		return todos;
	}

}



















