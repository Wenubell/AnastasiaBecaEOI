package es.eoi.mundobancario.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteCuentasSimpleDto extends ClienteDto {

	private List<CuentaDto> cuantas;
}
