package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaSolaDto {
	private Integer num_cuenta;
	private String alias;
	private Double saldo;
}
