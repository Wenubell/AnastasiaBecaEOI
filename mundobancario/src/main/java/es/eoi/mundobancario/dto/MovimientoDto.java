package es.eoi.mundobancario.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovimientoDto {

	private String descripcion;
	private Date fecha;
	private Double importe;

	private TipoMovimientoDto tipoMovimiento;

}
