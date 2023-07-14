package modelo;

import javax.swing.JOptionPane;

public class Temperatura implements ValidacionEntrada{
	@Override
	public String validar(String cadena) {		
		while(true) {			
			try {
				String entrada = JOptionPane.showInputDialog("Ingrese la temperatura en "+cadena);
				if(entrada != null) {	//	Entrada diferente a nulo
					double cantidad = Double.parseDouble(entrada);
					return String.valueOf(cantidad);					
				}else {	//	Entrada es nulo
					return "nulo";	//	Valor de error
				}
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor válido.");
            }
		}
	}
	
	private int celsiusAFahrenheit(String cantidad) {
		if(!cantidad.equals("nulo")) {
			double total = Double.parseDouble(cantidad) * (9.0/5.0)+32.0;
			JOptionPane.showMessageDialog(null, "Total: "+ String.format("%.2f",total) +" F°");
			
			return 1;	//	Se hizo una convercion
		}else {
			return 0;	//	Se cancelo la convercion
		}
	}
	
	private int fahrenheitACelsius(String cantidad) {
		if(!cantidad.equals("nulo")) {
			double total = (Double.parseDouble(cantidad)-32.0)*(5.0/9.0);
			JOptionPane.showMessageDialog(null, "Total: "+ String.format("%.2f",total) +" C°");
			
			return 1;	//	Se hizo una convercion
		}else {
			return 0;	//	Se cancelo la convercion
		}
	}
	
	private int celsiusAKelvin(String cantidad) {
		if(!cantidad.equals("nulo")) {
			double total = Double.parseDouble(cantidad) + 273.15;
			JOptionPane.showMessageDialog(null, "Total: "+ String.format("%.2f",total) +" K°");
			
			return 1;	//	Se hizo una convercion
		}else {
			return 0;	//	Se cancelo la convercion
		}
	}
	
	private int kelvinACelsius(String cantidad) {
		if(!cantidad.equals("nulo")) {
			double total = Double.parseDouble(cantidad) - 273.15;
			JOptionPane.showMessageDialog(null, "Total: "+ String.format("%.2f",total) +" C°");
			
			return 1;	//	Se hizo una convercion
		}else {
			return 0;	//	Se cancelo la convercion
		}
	}
	
	private int kelvinAFahrenheit(String cantidad) {
		if(!cantidad.equals("nulo")) {
			double total = (Double.parseDouble(cantidad)-273.15)*(9.0/5.0)+32.0;
			JOptionPane.showMessageDialog(null, "Total: "+ String.format("%.2f",total) +" °F");
			
			return 1;	//	Se hizo una convercion
		}else {
			return 0;	//	Se cancelo la convercion
		}
	}
	
	private int fahrenheitAKelvin(String cantidad) {
		if(!cantidad.equals("nulo")) {
			double total = (Double.parseDouble(cantidad)-32.0)*(5.0/9.0)+273.15;
			JOptionPane.showMessageDialog(null, "Total: "+ String.format("%.2f",total) +" °K");
			
			return 1;	//	Se hizo una convercion
		}else {
			return 0;	//	Se cancelo la convercion
		}
	}
	
	public void menuTemperatura() {
		//	Variables locales		
		String[] escalas = {"Celsius a Fahrenheit","Celsius a Kelvin","Fahrenheit a Celsius","Fahrenheit a Kelvin","Kelvin a Celsius","Kelvin a Fahrenheit"};
		Object seleccion = null;
		int resultado = 0;	//	Badera de control donde 1 = se realizo conversion / 0 = se cancelo
		
		//	Menu Temperatura
		do {			
			seleccion = JOptionPane.showInputDialog(null,"Selecciona una opción", "Conversor",JOptionPane.QUESTION_MESSAGE,null,escalas, escalas[0]);				
			if(seleccion == null) { 
				seleccion = "Regresar";	//	Cuando se da clic en "cancelar"
				resultado = 0;
			}
			
			switch(seleccion.toString()) {				
				case "Celsius a Fahrenheit": resultado = celsiusAFahrenheit(validar("°C"));
				break;
				case "Celsius a Kelvin": resultado = celsiusAKelvin(validar("°C"));
				break;
				case "Fahrenheit a Celsius": resultado = fahrenheitACelsius(validar("°F"));
				break;
				case "Fahrenheit a Kelvin": resultado = fahrenheitAKelvin(validar("°F"));
				break;
				case "Kelvin a Celsius": resultado = kelvinACelsius(validar("°K"));
				break;
				case "Kelvin a Fahrenheit": resultado = kelvinAFahrenheit(validar("°K"));
				break;				
				default:
				break;
			}
			
			if(resultado != 0) {
				int respuesta = JOptionPane.showConfirmDialog(null,"¿Deseas convertir otra cantidad?","Confirmación",JOptionPane.YES_NO_CANCEL_OPTION);
				
				if ((respuesta == JOptionPane.NO_OPTION || respuesta == JOptionPane.CANCEL_OPTION)) { seleccion = "Regresar"; }
			}
		} while(!seleccion.toString().equals("Regresar"));
	}	
	
}
