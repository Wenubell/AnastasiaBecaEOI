package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaDto {

	private Integer num_cuenta;
	private String alias;
	private Double saldo;
	
	private ClienteDto cliente;

}
