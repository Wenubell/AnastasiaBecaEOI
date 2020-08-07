package es.eoi.mundobancario.view;

import java.text.SimpleDateFormat;
import java.util.List;

import es.eoi.mundobancario.dto.CuentaCompletaDto;
import es.eoi.mundobancario.dto.MovimientoDto;

public class CuentasView {

	public static void imprimirCuentasCompletas(List<CuentaCompletaDto> cuentas) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		for (CuentaCompletaDto c : cuentas) {
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - ");
			System.out.println("Numero de cuenta: " + c.getNum_cuenta());
			System.out.println("Alias: " + c.getAlias());
			System.out.println("Saldo: " + c.getSaldo().toString());
			System.out.println("\t - - - ");
			System.out.println("Movimientos:");
			
			for (MovimientoDto m : c.getMovimientos()) {
				System.out.println("|---\t\t---|");
				System.out.println("Descripción: " + m.getDescripcion());
				System.out.println("Fecha: " + sdf.format(m.getFecha()));
				System.out.println("Importe: " + m.getImporte().toString());
				System.out.println("Tipo movimiento: " + m.getTipoMovimiento().getTipo());
				
				System.out.println("|---\t\t---|");
			}
			
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		}

	}

}
