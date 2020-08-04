package es.eoi.mundobancario.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PRESTAMOS")
public class Prestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String descripcion;
	@Column
	private Date fecha;
	@Column
	private Double importe;
	@Column
	private int plazos;

	@ManyToOne
	@JoinColumn(name = "ID_CUENTA", referencedColumnName = "num_cuenta")
	private Cuenta cuenta;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prestamo")
	private List<Amortizacion> amortizaciones;

}
