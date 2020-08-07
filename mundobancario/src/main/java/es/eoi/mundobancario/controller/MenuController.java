package es.eoi.mundobancario.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.CuentaCompletaDto;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.view.AppView;
import es.eoi.mundobancario.view.CuentasView;

@RestController
public class MenuController {

	@Autowired
	ClienteService serviceCliente;

	public void gestionaOpcionMenu(int opcion) throws Exception {

		switch (opcion) {

		case 1: // -Modificar información de usuario

			AppView.menu();
			break;
		case 2: // Consultar mis cuentas
			int id = pedirIdUsuario();
			List<CuentaCompletaDto> cuentas = serviceCliente.findCuentasByIdUsuario(id);
			CuentasView.imprimirCuentasCompletas(cuentas);
			AppView.menu();
			break;
		case 3: // Gestionar/Solicitar prestamos

			AppView.menu();
			break;
		case 4: // Amortizar prestamos

			AppView.menu();
			break;
		case 5: // Generación de reportes

			AppView.menu();
			break;
		default:
			System.out.println("Error en la opcion elegida");
			AppView.menu();
			break;
		}

	}

	public int pedirIdUsuario() {

		System.out.print("Introduce el id del usuario: ");

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int opcion = scanner.nextInt();

		return opcion;
	}

}
