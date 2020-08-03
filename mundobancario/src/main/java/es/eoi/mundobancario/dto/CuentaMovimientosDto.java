package es.eoi.mundobancario.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaMovimientosDto {
	
	private Integer num_cuenta;
	private String alias;
	private Double saldo;
	private ClienteSimpleDto cliente;
	
	private List<MovimientoDto> movimientos;
	private TipoMovimientoDto tipo;

}
