package co.com.ejercicio.spring.introduccion.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class ControllerArduino {
	
	/*
	 * sudo chmod 777 /dev/ttyACM0
	 * Para controlar el puerto si vemos que no se abre
	 */
	
	static List<String> medida = new ArrayList<>();
	
	static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
	
	static SerialPortEventListener listener = new SerialPortEventListener() {
		
		@Override
		public void serialEvent(SerialPortEvent serialPortEvent) {
			
			try {
				
				if(ino.isMessageAvailable()) {
					//System.out.println(Arrays.asList(Split(ino.printMessage())));
					medida = new ArrayList<>(Arrays.asList(Split(ino.printMessage())));
					
				}
				
			} catch (SerialPortException e) {
				Logger.getLogger(ControllerArduino.class.getName()).log(Level.SEVERE, null , e);
			} catch (ArduinoException e) {
				Logger.getLogger(ControllerArduino.class.getName()).log(Level.SEVERE, null , e);
			}
			
		}

		private String[] Split(String printMessage) {
			return printMessage.split(",");
		}
	};

	public List<String> getMedida() {
		return medida;
	}

	public ControllerArduino(String puerto, int velocidad) {
		
		try {
			ino.arduinoRX(puerto, velocidad, listener);
			
		} catch (ArduinoException | SerialPortException e) {
			Logger.getLogger(ControllerArduino.class.getName()).log(Level.SEVERE, null , e);
		}
		
	}
	
	/*public static void main(String[] args) {
		try {
			ino.arduinoRX("/dev/ttyACM0", 9600, listener);
			
		} catch (ArduinoException | SerialPortException e) {
			Logger.getLogger(ControllerArduino.class.getName()).log(Level.SEVERE, null , e);
		}
	}	*/
	
}
