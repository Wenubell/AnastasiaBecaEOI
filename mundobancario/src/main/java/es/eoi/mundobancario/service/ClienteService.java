package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.ClienteCuentasSimpleDto;
import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.CuentaDto;

public interface ClienteService {

	public ClienteDto findClienteById(Integer id);

	public List<ClienteDto> findAll();

	public ClienteDto loginUsuario(String usuario, String pass);

	public List<CuentaDto> findCuentasByIdUsuario(Integer id);

	public void crearUsuario(ClienteDto cliente);

	public void updateCliente(ClienteDto cliente);
	
	//-----------------------------------------

	public List<ClienteCuentasSimpleDto> findClientesCompletos();

}
