package co.com.ejercicio.spring.introduccion.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ejercicio.spring.introduccion.controllers.ControllerArduino;

@RestController
public class JavaController {
	
	private static ControllerArduino medicion = new ControllerArduino("/dev/ttyACM0", 9600);
	
	@RequestMapping(value = "/datos", method = RequestMethod.GET)
	public static String mostrarTemperaturaYHumedad() {
		
		return "Humedad: " + getMedicion().getMedida().get(0) + ", Temperatura: " + getMedicion().getMedida().get(1);
		
	}

	/**
	 * @return the medicion
	 */
	public static ControllerArduino getMedicion() {
		return medicion;
	}

	
	
}
