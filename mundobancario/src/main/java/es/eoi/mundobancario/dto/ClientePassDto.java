package es.eoi.mundobancario.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientePassDto extends ClienteDto {
	private String pass;
}