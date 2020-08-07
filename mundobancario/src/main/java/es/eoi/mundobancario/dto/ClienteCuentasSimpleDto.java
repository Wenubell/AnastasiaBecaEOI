package es.eoi.mundobancario.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteCuentasSimpleDto extends ClienteDto {
	
	private List<CuentaSolaDto> cuantas;
}
