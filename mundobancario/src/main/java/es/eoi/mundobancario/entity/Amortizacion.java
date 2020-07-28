package es.eoi.mundobancario.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "AMORTIZACIONES")
public class Amortizacion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Date fecha;
	@Column
	private Double importe;
	
	@ManyToOne
	@JoinColumn(name="ID_PRESTAMO", referencedColumnName="id")
	private Prestamo prestamo;
	

}
