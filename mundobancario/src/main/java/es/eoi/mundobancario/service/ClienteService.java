package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.ClienteCuentasDto;
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

	public List<ClienteCuentasDto> findClientesCompletos();

}
