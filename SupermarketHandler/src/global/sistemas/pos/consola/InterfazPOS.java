package global.sistemas.pos.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import global.sistemas.pos.procesamiento.HandlerPOS;

public class InterfazPOS {
	
	private HandlerPOS handlerPOS = new HandlerPOS();
	
	public static void main(String[] args)
	{
		
		InterfazPOS interfazPos = new InterfazPOS();
		while(true) {interfazPos.ejecutarAplicacion();}

	}
	
	
	public void ejecutarAplicacion() {
		
		//Cargar informacion desde la fuente persistente (Archivos CSV)
		handlerPOS.commandLoadCSVDatabase();
		
		//Ingreso al sistema
		mostrarIngreso();
		
	
		
		//Menu del sistema
		mostrarMenu();
		int opcionSeleccionada=Integer.parseInt(input("Ingrese la opcion deseada: "));
		
	}
	

	
	
	public void mostrarIngreso(){
		
		//Titulo del sistema
		String titulo_sistema = " Supermercados Nerv: Sistema POS (Point of Service) ";
		String titulo_rodeado = rodearDeCaracter('#', titulo_sistema);
		System.out.println(titulo_rodeado);
		
		//Mensaje de bienvenida 
		String mensaje_bienvenida = "\nBienvenido al sistema POS (diseñado para cajeros), por favor ingrese las siguientes credenciales.\n";
		System.out.println(mensaje_bienvenida);
		
		//Credencial: Nombre del cajero
		String nombre_cajero = input("Nombre: ");
		String id_cajero = input("Número de identificacion (Recuerde el prefijo C-): ");
		

	}
	
	public void mostrarMenu() {
		
		System.out.println("\n" + rodearDeCaracter('-', "Funcionalidades disponibles") + "\n");
		System.out.println("1. Registrar un nuevo cliente en el sistema de puntos.");
		System.out.println("2. Registrar una compra.");
		System.out.println("3. Facturar la compra actual.");
		System.out.println("4. Salir de la aplicación.");
	}
	
	
	//Método de formato!
	public String rodearDeCaracter(char caracter, String texto) {
		String texto_rodeado = "";
		String linea_caracter = "";
		for(int i = 0; i < texto.length(); i++) {
			linea_caracter += caracter;
		}
		texto_rodeado = linea_caracter + "\n" + texto + "\n" + linea_caracter;
		return texto_rodeado;
		
	}
	
	//Método para obtener el input
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

}
