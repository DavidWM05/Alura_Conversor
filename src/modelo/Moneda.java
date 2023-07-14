package modelo;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class Moneda implements ValidacionEntrada{
	private Map<String, Double> nacAext;	//	MNX a Extranjera
	private Map<String, Double> extAnac;	//	Extranjera a MNX
	
	//	=>	Constructor	<=
	public Moneda() {
		nacAext = new HashMap<>();
		extAnac = new HashMap<>();
		
		nacAext.put("USD", 0.058);	//	Peso a Dolar
		extAnac.put("USD", 17.16);	//	Dolar a Peso
		
		nacAext.put("EUR", 0.053);	//	Peso a Euro
		extAnac.put("EUR", 18.8);	//	Euro a Peso
		
		nacAext.put("GBP", 0.046);	//	Peso a Libra esterlina
		extAnac.put("GBP", 21.98);	//	Libra esterlina a Peso
		
		nacAext.put("JPY", 8.32);	//	Peso a Yen Japones
		extAnac.put("JPY", 0.12);	//	Yen Japones a Peso		
		
		nacAext.put("KRW", 76.15);	//	Peso a Won sur-coreano
		extAnac.put("KRW", 0.013);	//	Won sur-coreano a Peso
	}	
	
	@Override
	public String toString() {
		String contenido = "";
		
		for(String divisa : nacAext.keySet()) {
			double valor = nacAext.get(divisa);
			
			contenido+=divisa+" = "+String.valueOf(valor)+" ";
		}
		return contenido;
	}
	
	@Override
	public String validar(String cadena) {		
		while(true) {
			try {
				String entrada = JOptionPane.showInputDialog("Ingrese la cantidad de "+cadena);
				if(entrada != null) {	//	Entrada diferente a nulo
					double cantidad = Double.parseDouble(entrada);
					if(cantidad >= 0.0) return String.valueOf(cantidad);
					else JOptionPane.showMessageDialog(null, "Ingresa un numero mayor a 0");
				}else {	//	Entrada es nulo
					return "nulo";	//	Valor de error
				}
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor válido.");
            }
		}
	}
	
	public int nacionalAextranjera(String pais,String cantidad) {
		if(!cantidad.equals("nulo")) {
			double valor = nacAext.get(pais);
			double total = valor*Double.parseDouble(cantidad);		
			JOptionPane.showMessageDialog(null, "Total: "+ String.format("%.2f",total) +" "+pais);
			
			return 1;	//	Se hizo una convercion
		}else {
			return 0;	//	Se cancelo la convercion
		}
	}
	
	public int extranjeraAnacional(String pais,String cantidad) {
		if(!cantidad.equals("nulo")) {
			double valor = extAnac.get(pais);
			double total = valor*Double.parseDouble(cantidad);		
			JOptionPane.showMessageDialog(null, "Total: "+ String.format("%.2f",total) +" MXN");
			
			return 1;	//	Se hizo una convercion
		}else {
			return 0;	//	Se cancelo la convercion
		}
	}
	
	public void menuMoneda() {
		//	Variables locales		
		String[] monedaOpc = {"De Peso a Dolar","De Dolar a Peso","De Peso a Euro","De Euro a Peso","De Peso a Libra Esterlina","De Libra Esterlina a Peso","De Peso a Yen Japones","De Yen Japones a Peso","De Peso a Won Sur-Coreano","De Won Sur-Coreano a Peso"};
		Object seleccion = null;
		int resultado = 0;	//	Badera de control donde 1 = se realizo conversion / 0 = se cancelo
			
		//	Menu Moneda
		do {			
			seleccion = JOptionPane.showInputDialog(null,"Selecciona una opción", "Conversor",JOptionPane.QUESTION_MESSAGE,null,monedaOpc, monedaOpc[0]);				
			if(seleccion == null) { 
				seleccion = "Regresar";	//	Cuando se da clic en "cancelar"
				resultado = 0;
			}
			
			//alejandro ahuado
			
			switch(seleccion.toString()) {				
				case "De Peso a Dolar":	resultado = nacionalAextranjera("USD",validar("MXN"));
				break;
				case "De Dolar a Peso":	resultado = extranjeraAnacional("USD",validar("USD"));
				break;
				case "De Peso a Euro": resultado = nacionalAextranjera("EUR",validar("MXN"));
				break;
				case "De Euro a Peso": resultado = extranjeraAnacional("EUR",validar("EUR"));
				break;
				case "De Peso a Libra Esterlina": resultado = nacionalAextranjera("GBP",validar("MXN"));
				break;
				case "De Libra Esterlina a Peso": resultado = extranjeraAnacional("GBP",validar("GBP"));
				break;
				case "De Peso a Yen Japones": resultado = nacionalAextranjera("JPY",validar("MXN"));
				break;
				case "De Yen Japones a Peso": resultado = extranjeraAnacional("JPY",validar("JPY"));
				break;
				case "De Peso a Won Sur-Coreano": resultado = nacionalAextranjera("KRW",validar("MXN"));
				break;
				case "De Won Sur-Coreano a Peso": resultado = extranjeraAnacional("KRW",validar("KRW"));
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
