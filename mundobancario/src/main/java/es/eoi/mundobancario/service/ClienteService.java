package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.ClienteDto;


public interface ClienteService {
	
	
	public ClienteDto findClienteById(Integer id);
	
	public List<ClienteDto> findAll();

	public ClienteDto loginUsuario(String usuario, String pass);

	public void crearUsuario(ClienteDto cliente);

	public void updateCliente(ClienteDto cliente);
}
