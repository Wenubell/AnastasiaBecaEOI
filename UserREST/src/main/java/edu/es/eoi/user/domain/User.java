package edu.es.eoi.user.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	
	@JsonIgnore
	private Integer id;
	private String nombre;
	private Date fecha;
	private Boolean premium;
	private Double saldo;

}
