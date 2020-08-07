package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.ClienteCuentasMovimientosDto;
import es.eoi.mundobancario.dto.ClienteCuentasSimpleDto;
import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClientePassDto;
import es.eoi.mundobancario.dto.CuentaCompletaDto;
import es.eoi.mundobancario.entity.Cliente;

public interface ClienteService {
	
	public Cliente findClienteByUsuario(String usuario);

	// ----------------- //
	// ClienteController //
	// ----------------- //

	/**
	 * @param id
	 * @return
	 */
	public ClienteDto findClienteById(Integer id);

	/**
	 * @return
	 */
	public List<ClienteDto> findAll();

	/**
	 * @param usuario
	 * @param pass
	 * @return
	 */
	public ClienteDto loginUsuario(String usuario, String pass);

	/**
	 * @param id
	 * @return
	 */
	public List<CuentaCompletaDto> findCuentasByIdUsuario(Integer id);

	/**
	 * @param id
	 * @param cliente
	 */
	public void updateCliente(Integer id, ClientePassDto cliente);

	/**
	 * @return
	 */
	public List<ClienteCuentasSimpleDto> findClientesCompletos();

	/**
	 * @param cliente
	 */
	public boolean crearUsuario(ClientePassDto cliente);

	// ------------------ //
	// ResportsController //
	// ------------------ //

	public ClienteCuentasMovimientosDto findClienteCuentasMovimientos(Integer id);

}
