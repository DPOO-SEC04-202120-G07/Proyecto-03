package global.sistemas.inventario.consola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import global.sistemas.inventario.procesamiento.HandledException;
import global.sistemas.inventario.procesamiento.HandlerSI;


public class InterfazSI {

	private HandlerSI handlerSi = new HandlerSI();
	private String id_encargado;
	private String nombre_encargado;
	
	public static void main(String[] args)
	{
		
		InterfazSI interfazSi = new InterfazSI();
		interfazSi.ejecutarAplicacion();

	}
	
	
	public void ejecutarAplicacion() {
	
		
		//Ingreso al sistema
		mostrarIngreso();
		
	
		while (true) {
			//Menu del sistema
			mostrarMenu();
			
			try{
				int opcionSeleccionada=Integer.parseInt(input("Ingrese la opcion deseada: "));
				if (opcionSeleccionada==7) {break;}
				ejecutarOpcion(opcionSeleccionada);}
			catch (NumberFormatException e) {
				System.out.println("No ha ingresado una opcion correcta.");
			}
			catch (HandledException e) {
				if (e.getCode() == "null-supermercado") {
					System.out.println("Recuerda primero cargar la base de datos al sistema!");
				}
				else if(e.getCode() == "null-producto") {
					System.out.println("El código del producto ingresado no se encuentra registrado en el supermercado.");
				}
				else if(e.getCode() == "parse-date") {
					System.out.println("No se ha respetado el formato de fecha. Intente de nuevo.");
				}
				else if(e.getCode()=="null-unidad") {
					System.out.println("La unidad en la que se desea almacenar el producto no existe, intentelo de nuevo.");
				}
			}


		}
	}
	
	
	
	public void ejecutarOpcion(int opcion) throws HandledException{
		switch(opcion) {
		
			case 1:
				//Cargar informacion desde la fuente persistente (Archivos CSV)
			try {
				handlerSi.commandLoadCSVDatabase();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
				//Verificar si se encuentra registrado el encargado, caso contrario se registra el encargado.
				if (!handlerSi.encargadoRegistrado(this.id_encargado)) {
					handlerSi.registrarEncargado(this.nombre_encargado, this.id_encargado);
				}
				break;
		
			case 2:
				
				String idLoteNuevo = input("Ingrese el nombre del archivo de lotes que desea cargar: ");
				try{handlerSi.cargarLote(idLoteNuevo);
					System.out.println("Su nuevo lote ha sido cargado con éxito.");}
				catch(FileNotFoundException e) {
					System.out.println("El archivo de lotes ingresado no existe.");
				}
				break;
			case 3:
				String idProducto = input("Ingrese el codigo que identifica al producto que desea consultar (recuerde el prefijo P- si no se trata de un codigo de barras): ");
				double[] desempenoProducto = handlerSi.consultarDesempenoProducto(idProducto);
				System.out.println("\nEl total de productos perdidos fue de: " + desempenoProducto[0]);
				System.out.println("El total de productos vendidos fue de: "+ desempenoProducto[1]);
				System.out.println("La perdida económica es de: "+ desempenoProducto[2] + "$");
				System.out.println("La ganancia es de: " + + desempenoProducto[3] + "$");
				break;
			case 4:
				
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
				
			case 5:
				
				String fechaActual = input("Ingrese la fecha actual (dd/MM/yyyy): ");
				handlerSi.eliminarProductosVencidos(fechaActual);
				System.out.println("Los productos vencidos han sido eliminados exitosamente!");

			case 6:
				handlerSi.commandSaveCSVDatabase();
				System.out.println("La información ha sido almacenada de forma exitosa en la base de datos.");
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
		this.nombre_encargado = input("Nombre: ");
		this.id_encargado = input("Número de identificacion (Recuerde el prefijo E-): ");
		
		


	}
	
	public void mostrarMenu() {
		
		System.out.println("\n" + rodearDeCaracter('-', "Funcionalidades disponibles") + "\n");
		System.out.println("1. Cargar la información de la base de datos.");
		System.out.println("2. Cargar nuevo lote de productos.");
		System.out.println("3. Consultar desempeño financiero de un producto.");
		System.out.println("4. Consultar estado del inventario.");
		System.out.println("5. Eliminar productos vencidos");
		System.out.println("6. Guardar información actualizada en la base de datos.");
		System.out.println("7. Salir de la aplicación.");
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
	
	//Métodos comunicación máquina-usuario
	public ArrayList<String> askCategoria(String nombreProducto) {
		
		ArrayList<String> infoCategoria = new ArrayList<String>();
		
		System.out.println("\nEl producto '"+ nombreProducto +"' no tiene una categoría asociada. Creela a continuación: ");
		String nombreCategoria =  input("Ingrese el nombre de la categoría asociada: ");
		String pasilloCateogoria = input("Ingrese el pasillo en el que se ubica la categoría: ");
		String nombreSubcategorias = input("Ingrese el nombre de las subcategorías asociadas separadas por un guión: ");
		
		infoCategoria.add(nombreCategoria);
		infoCategoria.add(pasilloCateogoria);
		infoCategoria.add(nombreSubcategorias);
		
		return infoCategoria;
		
	}


	public ArrayList<String> askSubCategoria(String nombreSubCat) {
		ArrayList<String> infoSubCategoria = new ArrayList<String>();
		
		System.out.println("\nIndique la información de la subcategoría "+ nombreSubCat);

		String numeroEstante = input("Ingrese el número de estante en el que se ubica la subcategoría: ");
		String nivelEstante = input("Ingrese el nivel de estante en el que se ubica la subcategoría: ");
		
		infoSubCategoria.add(numeroEstante);
		infoSubCategoria.add(nivelEstante);

		return infoSubCategoria;
	}


	public String askUnidad(String nombre) {
		String idUnidad = "U-"+input("Ingrese el ID de la unidad en la que desea almacenar el producto '"+nombre+"': U-");
		return idUnidad;
	}
	

}
