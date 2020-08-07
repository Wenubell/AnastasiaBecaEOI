package es.eoi.mundobancario.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteCuentasMovimientosDto extends ClienteDto {
	
	private List<CuentaMovimientosDto> cuantas;
}
