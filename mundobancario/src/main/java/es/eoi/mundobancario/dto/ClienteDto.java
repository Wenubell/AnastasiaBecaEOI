package es.eoi.mundobancario.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {

	private String usuario;
	private String pass;
	private String nombre;
	private String email;

	/*private ClienteDto cliente;

	private List<MovimientoDto> movimientos;

	private TipoMovimientoDto tipo;

	private List<PrestamoDto> prestamos;*/

}
