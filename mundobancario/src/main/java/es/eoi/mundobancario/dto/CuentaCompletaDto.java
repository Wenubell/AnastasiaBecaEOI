package es.eoi.mundobancario.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaCompletaDto extends CuentaDto {

	private List<MovimientoDto> movimientos;

	private TipoMovimientoDto tipo;

	private List<PrestamoDto> prestamos;

}
