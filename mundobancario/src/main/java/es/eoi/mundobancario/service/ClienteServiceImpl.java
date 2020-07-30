package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	ClienteRepository repository;
	
	public ClienteDto findClienteById(Integer id) {
		return convertToDto(repository.findById(id).get());
	}
	
	public List<ClienteDto> findAll() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.addAll(repository.findAll());
		List<ClienteDto> clientesDto = new ArrayList<ClienteDto>();
		for (Cliente cliente : clientes) {
			clientesDto.add(convertToDto(cliente));
		}
		return clientesDto;
	}
	
	public ClienteDto loginUsuario(String usuario, String pass) {
		Cliente cli = repository.findClienteByUsuario(usuario);
		if(cli.getPass().equals(pass)) {
			return convertToDto(cli);
		}
		return null;
	}
	
	public void crearUsuario(ClienteDto dto) {
		repository.save(convertToCliente(dto));
	}
	
	public void updateCliente(ClienteDto dto) {
		Cliente cli = repository.findClienteByUsuario(dto.getUsuario());
		if(cli.getPass().equals(dto.getPass())) {
			cli.setEmail(dto.getEmail());
			repository.save(cli);
		}
	}
	
	public Object findClienteCompleto() {
		return null;
	}
	
	private ClienteDto convertToDto(Cliente cliente) {
		ClienteDto dto = new ClienteDto();
		dto.setEmail(cliente.getEmail());
		dto.setNombre(cliente.getNombre());
		dto.setPass(cliente.getPass());
		dto.setUsuario(cliente.getUsuario());
		return dto;
	}
	
	private Cliente convertToCliente(ClienteDto dto) {
		Cliente cliente = new Cliente();
		cliente.setEmail(dto.getEmail());
		cliente.setNombre(dto.getNombre());
		cliente.setPass(dto.getPass());
		cliente.setUsuario(dto.getUsuario());
		return cliente;
	}

}
