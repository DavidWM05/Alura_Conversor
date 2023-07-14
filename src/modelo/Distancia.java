package modelo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

public class Distancia implements ValidacionEntrada{
	private Map<String, Double> unidades;	
	
	public Distancia() {
		unidades = new HashMap<>();				
		
		//	milla
		unidades.put("Millas a Kilometros", 1.60934);		//	milla a kilometro
		unidades.put("Millas a Metros", 1609.34);			//	milla a metro
		unidades.put("Millas a Yardas",1760.0);				//	milla a yarda
		unidades.put("Millas a Pies",5280.0);				//	milla a pie
		unidades.put("Millas a Años-Luz",1.7011e-13);		//	milla a año luz
		
		//	kilometro
		unidades.put("Kilometros a Millas", 0.621371);		//	kilometro a milla
		unidades.put("Kilometros a Metros", 1000.0);		//	kilometro a metro
		unidades.put("Kilometros a Yardas", 1093.61);		//	kilometro a yarda
		unidades.put("Kilometros a Pies", 3280.84);			//	kilometro a pie
		unidades.put("Kilometros a Años-Luz", 1.057e-13);	//	kilometro a año luz
		
		//	metro
		unidades.put("Metros a Millas", 0.000621371);	//	metro a milla
		unidades.put("Metros a Kilometros", 0.001);		//	metro a kilometro
		unidades.put("Metros a Yardas", 1.09361);		//	metro a yarda
		unidades.put("Metros a Pies", 3.28084);		//	metro a pie
		unidades.put("Metros a Años-Luz", 1.057e-16);	//	metro a año luz
		
		//	yarda
		unidades.put("Yardas a Millas", 0.000568182);	//	yarda a milla
		unidades.put("Yardas a Kilometros", 0.0009144);	//	yarda a kilometro
		unidades.put("Yardas a Metros", 0.9144);		//	yarda a metro
		unidades.put("Yardas a Pies", 3.0);			//	yarda a pie
		unidades.put("Yardas a Años-Luz", 9.6652e-17);	//	yarda a año luz
		
		//	Pie
		unidades.put("Pies a Millas", 0.000189394);	//	pie a milla
		unidades.put("Pies a Kilometros", 0.0003048);	//	pie a kilometro
		unidades.put("Pies a Metros", 0.3048);		//	pie a metro
		unidades.put("Pies a Yardas", 0.333333);	//	pie a yarda
		unidades.put("Pies a Años-Luz", 3.2217e-17);	//	pie a año luz
		
		//	Año luz
		unidades.put("Años-Luz a Millas", 5.879e+12);	//	año luz a milla
		unidades.put("Años-Luz a Kilometros", 9.461e+12);	//	año luz a kilometro
		unidades.put("Años-Luz a Metros", 9.461e+15);	//	año luz a metro
		unidades.put("Años-Luz a Yardas", 1.035e+16);	//	año luz a yarda
		unidades.put("Años-Luz a Pies", 3.104e+16);	//	año luz a pie
	}
	
	@Override
	public String validar(String cadena) {		
		while(true) {			
			try {
				String entrada = JOptionPane.showInputDialog("Ingrese la distancia en "+cadena);
				if(entrada != null) {	//	Entrada diferente a nulo
					double cantidad = Double.parseDouble(entrada);
					
					if(cantidad > 0.0)	return String.valueOf(cantidad);
					else JOptionPane.showMessageDialog(null, "Ingresa un numero mayor a 0");
				}else {	//	Entrada es nulo
					return "nulo";	//	Valor de error
				}
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor válido.");
            }
		}
	}

	private int convertir(String medida,String cantidad,String medidaR) {
		if(!cantidad.equals("nulo")) {
			System.out.println(unidades.get(medida));
			double total = Double.parseDouble(cantidad) * unidades.get(medida);
			JOptionPane.showMessageDialog(null, "Total: "+ String.format("%.6f", total) +" " + medidaR);
			
			return 1;	//	Se hizo una convercion
		}else {
			return 0;	//	Se cancelo la convercion
		}
	}
	
	public void menuDistancia() {
		//	Variables locales		
		Set<String> llaves = unidades.keySet();
		String[] opciones = llaves.toArray(new String[0]);
		Arrays.sort(opciones);
		//String[] escalas = {"Milla a Kilometro","Milla a Metro","Kilometro a Milla","Kilometro a Metro","Metro a Milla","Metro a Kilometro"};
			
		Object seleccion = null;
		int resultado = 0;	//	Badera de control donde 1 = se realizo conversion / 0 = se cancelo
		
		//	Menu Temperatura
		do {			
			seleccion = JOptionPane.showInputDialog(null,"Selecciona una opción", "Conversor",JOptionPane.QUESTION_MESSAGE,null,opciones, opciones[0]);				
			if(seleccion == null) { 
				seleccion = "Regresar";	//	Cuando se da clic en "cancelar"
				resultado = 0;
			}else {
				String opcion = seleccion.toString();				
				String[] partes = opcion.split(" ");
				resultado = convertir(opcion,validar(partes[0]),partes[2]);
			}
			
			
			
			/*
			switch(seleccion.toString()) {				
				case "Milla a Kilometro": 	resultado = convertir("miAkm",validar("mi"),"km");
				break;
				case "Milla a Metro": 		resultado = convertir("miAm",validar("mi"),"m");
				break;
				case "Kilometro a Milla": 	resultado = convertir("kmAmi",validar("km"),"mi");
				break;
				case "Kilometro a Metro": 	resultado = convertir("kmAm",validar("km"),"m");
				break;
				case "Metro a Milla": 		resultado = convertir("mAmi",validar("m"),"mi");
				break;
				case "Metro a Kilometro": 	resultado = convertir("mAkm",validar("m"),"km");
				break;				
				default:
				break;
			}*/
			
			if(resultado != 0) {
				int respuesta = JOptionPane.showConfirmDialog(null,"¿Deseas convertir otra cantidad?","Confirmación",JOptionPane.YES_NO_CANCEL_OPTION);
				
				if ((respuesta == JOptionPane.NO_OPTION || respuesta == JOptionPane.CANCEL_OPTION)) { seleccion = "Regresar"; }
			}
		} while(!seleccion.toString().equals("Regresar"));
	}
}
