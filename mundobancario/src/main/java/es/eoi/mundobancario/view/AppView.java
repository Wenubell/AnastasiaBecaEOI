package es.eoi.mundobancario.view;

import java.util.Scanner;

import es.eoi.mundobancario.controller.MenuController;

public class AppView {
	
	static MenuController controllerMenu;
	
	public static void menu() throws Exception {
		
		System.out.println("**********************************************************************");
		System.out.println("\t¡¡¡¡¡Bienvenido a tu Aplicación de Banca Flipante!!!!!");
		System.out.println("**********************************************************************");
		
		System.out.println("¿Qué operación quieres realizar?");
		System.out.println("1.-Modificar información de usuario");
		System.out.println("2.-Consultar mis cuentas");
		System.out.println("3.-Gestionar/Solicitar prestamos");
		System.out.println("4.-Amortizar prestamos");
		System.out.println("5.-Generación de reportes");
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int opcion = scanner.nextInt();
		System.out.println("Opción seleccionada: " + opcion);
		//controllerMenu.gestionaOpcionMenu(opcion);
		System.out.println("--->Sin implementar<---");
		
	}
}
