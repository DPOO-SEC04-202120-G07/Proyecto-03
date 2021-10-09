package global.sistemas.inventario.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import global.sistemas.inventario.procesamiento.HandlerSI;


public class InterfazSI {

	private HandlerSI handlerSi = new HandlerSI();
	
	public static void main(String[] args)
	{
		
		InterfazSI interfazSi = new InterfazSI();
		while(true) {interfazSi.ejecutarAplicacion();}

	}
	
	
	public void ejecutarAplicacion() {
		
		//Cargar informacion desde la fuente persistente (Archivos CSV)
		handlerSi.commandLoadCSVDatabase();
		
		//Ingreso al sistema
		mostrarIngreso();
		
	
		while (true) {
			//Menu del sistema
			mostrarMenu();
			
			int opcionSeleccionada=Integer.parseInt(input("Ingrese la opcion deseada: "));
			
			if (opcionSeleccionada==4) {break;}
			ejecutarOpcion(opcionSeleccionada);
		}
	}
	
	
	
	public void ejecutarOpcion(int opcion){
		switch(opcion) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			default:
				System.out.println("Ingrese una opcion valida.");
				break;
		
		}
	}
	
	
	public void mostrarIngreso(){
		
		//Titulo del sistema
		String titulo_sistema = " Supermercados Nerv: Sistema SI (Inventario) ";
		String titulo_rodeado = rodearDeCaracter('#', titulo_sistema);
		System.out.println(titulo_rodeado);
		
		//Mensaje de bienvenida 
		String mensaje_bienvenida = "\nBienvenido al sistema SI (diseñado para encargados de inventario), por favor ingrese las siguientes credenciales.\n";
		System.out.println(mensaje_bienvenida);
		
		//Credencial: Nombre del cajero
		String nombre_encargado = input("Nombre: ");
		String id_encargado = input("Número de identificacion (Recuerde el prefijo E-): ");
		
		
		//Verificar si se encuentra registrado, caso contrario se registra el encargado.
		if (!handlerSi.encargadoRegistrado(id_encargado)) {
			handlerSi.registrarEncargado(nombre_encargado, id_encargado);
		}

	}
	
	public void mostrarMenu() {
		
		System.out.println("\n" + rodearDeCaracter('-', "Funcionalidades disponibles") + "\n");
		System.out.println("1. Cargar nuevo lote de productos.");
		System.out.println("2. Consultar desempe�o financiero de un producto.");
		System.out.println("3. Estado del inventario.");
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
