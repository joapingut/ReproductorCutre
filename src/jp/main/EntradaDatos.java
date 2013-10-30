package jp.main;

import java.io.*;

import javax.swing.*;

public class EntradaDatos{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Este metodo capta una cadena del teclado
	public static String incadena (){
	InputStreamReader entrada =new InputStreamReader (System.in);
	BufferedReader LectorDeBuffer = new BufferedReader (entrada);
	String entradaS = null;
	try {
		entradaS = LectorDeBuffer.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

	return entradaS;
	}
	
	//Este metodo capta un numero del teclado (int)
	public static int innumero (){
		Integer result = null;
		for(int i=0;i<1;i++){
			InputStreamReader entrada =new InputStreamReader (System.in);
			BufferedReader LectorDeBuffer = new BufferedReader (entrada);
			String entradaS = null;
		try {
			entradaS = LectorDeBuffer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		try{ 
			result = Integer.parseInt(entradaS);
		} catch(NumberFormatException e){
			i=-1;}
		}
		return result;
		}
	
	//Este metodo capta un caracter del teclado, si se escribe una cadena solo toma valido el primer caracter
	public static char incaracter (){
		InputStreamReader entrada =new InputStreamReader (System.in);
		BufferedReader LectorDeBuffer = new BufferedReader (entrada);
		String entradaS = null;
		try {
			entradaS = LectorDeBuffer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

		return entradaS.charAt(0);
		}
	


}