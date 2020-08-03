package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.repository.MovimientoRepository;
import es.eoi.mundobancario.util.Util;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	@Autowired
	MovimientoRepository repository;

	/*@Override
	public List<MovimientoDto> findMovimientosCuentaByIdCuenta(Integer idCuenta) {
		List<Movimiento> movimientos = repository.findMovimientoByIdCuenta(idCuenta);
		List<MovimientoDto> movDto = new ArrayList<MovimientoDto>();
		for (Movimiento movimiento : movimientos) {
			movDto.add(Util.ConvertToMovimientoDto(movimiento));
		}
		return movDto;
	}*/

}
