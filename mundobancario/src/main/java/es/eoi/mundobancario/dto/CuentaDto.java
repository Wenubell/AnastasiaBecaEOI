package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaDto {

	private String alias;
	private Double saldo;

	private ClienteDto cliente;

	/*private List<MovimientoDto> movimientos;

	private TipoMovimientoDto tipo;

	private List<PrestamoDto> prestamos;*/

}
