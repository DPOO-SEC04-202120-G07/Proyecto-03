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
			
			if (opcionSeleccionada==5) {break;}
			ejecutarOpcion(opcionSeleccionada);
		}
	}
	
	
	
	public void ejecutarOpcion(int opcion){
		switch(opcion) {
			case 1:
				
				String idLoteNuevo = input("Ingrese el nombre del archivo de lotes que desea cargar: ");
				handlerSi.cargarLote(idLoteNuevo);
				System.out.println("Su nuevo lote ha sido cargado con éxito.");
				
				break;
			case 2:
				String idProducto = input("Ingrese el codigo que identifica al producto que desea consultar (recuerde el prefijo P- si no se trata de un codigo de barras): ");
				double[] desempenoProducto = handlerSi.consultarDesempenoProducto(idProducto);
				System.out.println("\nEl total de productos perdidos fue de: " + desempenoProducto[0]);
				System.out.println("El total de productos vendidos fue de: "+ desempenoProducto[1]);
				System.out.println("La perdida económica es de: "+ desempenoProducto[2] + "$");
				System.out.println("La ganancia es de: " + + desempenoProducto[3] + "$");
				break;
			case 3:
				
				int productosEnInventario = handlerSi.cantidadProductosInventario();
				System.out.println("Actualmente existen " + productosEnInventario +" productos en el inventario.");
				
				System.out.println("Desea realizar una consulta sobre alguno de estos?");
				System.out.println("1) Sí");
				System.out.println("2) No");
				String seleccion = input("\n");
				if (Integer.parseInt(seleccion) == 1){
					String idProductoInteres = input("\nIngrese el código que identifica al producto que desea consultar: ");
					Object[] infoProducto = handlerSi.consultarInfoProducto(idProductoInteres);
					
					String formatoInfoProducto = "";
					formatoInfoProducto += "Nombre: " + infoProducto[0];
					formatoInfoProducto += "\nMarca: " + infoProducto[1];
					formatoInfoProducto += "\nCategoria: "+ infoProducto[2];
					formatoInfoProducto += "\nPrecio: " + infoProducto[3];
					formatoInfoProducto += "\nCantidad disponible: " + infoProducto[4];
					formatoInfoProducto = rodearDeCaracter('-', formatoInfoProducto);
					
					System.out.println(formatoInfoProducto);
				}
				break;
				
			case 4:
				
				String fechaActual = input("Ingrese la fecha actual (dd/MM/yyyy): ");
				handlerSi.eliminarProductosVencidos(fechaActual);
				System.out.println("Los productos vencidos han sido eliminados exitosamente!");
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
		System.out.println("2. Consultar desempeño financiero de un producto.");
		System.out.println("3. Consultar estado del inventario.");
		System.out.println("4. Eliminar productos vencidos");
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
