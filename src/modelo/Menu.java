package modelo;

import javax.swing.JOptionPane;

public class Menu {
	public static void main(String[] args) {
		//	Variables locales
		String[] opciones = {"Moneda","Temperatura","Distancia","Finalizar"};						
		Object seleccion = null;
		
		Moneda moneda = new Moneda();
		Temperatura temperatura = new Temperatura();
		Distancia distancia = new Distancia();
		
		//Menu principal
		do {
			seleccion = JOptionPane.showInputDialog(null,"Selecciona una opción", "Conversor",JOptionPane.QUESTION_MESSAGE,null,opciones, opciones[0]);
			if(seleccion == null) seleccion="Finalizar";
			
			switch(seleccion.toString()) {
				case "Moneda":	moneda.menuMoneda();
				break;
				case "Temperatura":	temperatura.menuTemperatura();
				break;
				case "Distancia":	distancia.menuDistancia();
				break;
				default: JOptionPane.showMessageDialog(null, "Programa terminado", "Conversor", 1);
				break;
			}
		} while(!seleccion.toString().equals("Finalizar"));
	}
}
