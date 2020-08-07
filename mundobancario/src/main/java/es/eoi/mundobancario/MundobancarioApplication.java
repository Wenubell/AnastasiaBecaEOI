package es.eoi.mundobancario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.eoi.mundobancario.view.AppView;

@SpringBootApplication
public class MundobancarioApplication {
	

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MundobancarioApplication.class, args);
		
		AppView.menu();
	}
	
	

}
