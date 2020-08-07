package es.eoi.mundobancario.dto;

import java.util.Date;
import java.util.List;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PrestamoDto {

	private String descripcion;
	private Date fecha;
	private Double importe;
	private int plazos;

	private List<AmortizacionDto> amortizaciones;

}
