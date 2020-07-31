package es.eoi.mundobancario.service;

import java.util.HashMap;
import java.util.List;

import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClienteSimpleDto;
import es.eoi.mundobancario.dto.CuentaDto;

public interface ClienteService {

	public ClienteSimpleDto findClienteById(Integer id);

	public List<ClienteSimpleDto> findAll();

	public ClienteSimpleDto loginUsuario(String usuario, String pass);

	public List<CuentaDto> findCuentasByIdUsuario(Integer id);

	public void crearUsuario(ClienteDto cliente);

	public void updateCliente(ClienteDto cliente);
	
	//-----------------------------------------

	public HashMap<ClienteSimpleDto, List<CuentaDto>> findClientesCompletos();

}
