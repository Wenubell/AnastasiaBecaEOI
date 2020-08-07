package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteDto {

	private String usuario;
	private String nombre;
	private String email;

}
