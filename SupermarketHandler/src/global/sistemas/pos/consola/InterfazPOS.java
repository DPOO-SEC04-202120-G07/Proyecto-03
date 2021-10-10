package global.sistemas.pos.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import global.sistemas.pos.procesamiento.HandlerPOS;

public class InterfazPOS {
	
	private HandlerPOS handlerPOS = new HandlerPOS();
	private String id_cajero;
	private String nombre_cajero;
	
	public static void main(String[] args)
	{
		
		InterfazPOS interfazPos = new InterfazPOS();
		interfazPos.ejecutarAplicacion();

	}
	
	
	public void ejecutarAplicacion() {
		
		
		//Ingreso al sistema
		String cajero=mostrarIngreso();
		
	
		while (true) {
			//Menu del sistema
			mostrarMenu();
			
			int opcionSeleccionada=Integer.parseInt(input("Ingrese la opcion deseada: "));
			
			if (opcionSeleccionada==5) {break;}
			ejecutarOpcion(opcionSeleccionada, cajero);
		}
	}
	

	public void ejecutarOpcion(int opcion, String cajero){
		switch(opcion) {
			case 1:
				//Cargar informacion desde la fuente persistente (Archivos CSV)
				handlerPOS.commandLoadCSVDatabase();
				//Verificar si se encuentra registrado, caso contrario se registra el cajero.
				if (!handlerPOS.cajeroRegistrado(id_cajero)) {
					handlerPOS.registrarCajero(nombre_cajero, id_cajero);
				}
				
				break;
			case 2:
				System.out.println("Ingrese los datos del cliente a registrar a continuacion.");
				//String nombre, int edad, char sexo, String cedula, String estadoCivil, String situacionLaboral
				String nombre_cliente= input("Nombre: ");
				int edad_cliente= Integer.parseInt(input("Edad: "));
				char sexo_cliente= input("Sexo(M/F): ").toCharArray()[0];
				String cc_cliente= input("Cedula: ");
				String eCivil_cliente= input("Estado Civil: ");
				String sLaboral_cliente= input("Situacion Laboral: ");
				handlerPOS.registrarCliente(nombre_cliente, edad_cliente, sexo_cliente, cc_cliente, eCivil_cliente, sLaboral_cliente);
				System.out.println("Cliente registrado exitosamente.");
				break;
			case 3:
				char registrado = input("Se encuentra el cliente registrado? (Y/N): ").toCharArray()[0];
				String cc=null;
				if (registrado=='Y') {cc= input("Ingrese la CC del cliente registrado: ");}
				handlerPOS.registrarCompra(cajero,cc);
				while (true) {
					String producto = input("Ingrese el codigo del producto a comprar o ingrese 0 para no agregar mas productos: ");
					if (producto=="0") {break;}
					handlerPOS.agregarProducto(producto);
				}
				break;
			case 4:
				String recibo=handlerPOS.facturarCompra();
				System.out.print(recibo);
				break;
			default:
				System.out.println("Ingrese una opcion valida.");
				break;
		
		}
	}
	
	public String mostrarIngreso(){
		
		//Titulo del sistema
		String titulo_sistema = " Supermercados Nerv: Sistema POS (Point of Service) ";
		String titulo_rodeado = rodearDeCaracter('#', titulo_sistema);
		System.out.println(titulo_rodeado);
		
		//Mensaje de bienvenida 
		String mensaje_bienvenida = "\nBienvenido al sistema POS (diseñado para cajeros), por favor ingrese las siguientes credenciales.\n";
		System.out.println(mensaje_bienvenida);
		
		//Credencial: Nombre del cajero
	    this.nombre_cajero = input("Nombre: ");
		this.id_cajero = input("Número de identificacion (Recuerde el prefijo C-): ");
	
		
		return id_cajero;
		

	}
	
	public void mostrarMenu() {
		
		System.out.println("\n" + rodearDeCaracter('-', "Funcionalidades disponibles") + "\n");
		System.out.println("1. Cargar la información de la base de datos.");
		System.out.println("2. Registrar un nuevo cliente en el sistema de puntos.");
		System.out.println("3. Registrar una compra.");
		System.out.println("4. Facturar la compra actual.");
		System.out.println("5. Salir de la aplicación.");
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
