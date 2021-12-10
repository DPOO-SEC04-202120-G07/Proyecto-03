package global.sistemas.pos.procesamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;



public class LoaderDatabase {
	
	private SupermarketModeler modeladorSupermercado;
	
	public void loadDatabaseCSV(SupermarketModeler modeladorSupermercado) throws FileNotFoundException {
		
		this.modeladorSupermercado = modeladorSupermercado;
		
		//Orden de lo general a lo particular
		loadSupermercadoCSV(); //Listo
		loadSubcategoriasCSV(); //Listo
		loadCategoriasCSV(); //Listo
		loadLotesCSV(); //Listo
		loadInventarioCSV(); //Listo
		loadUnidadesDeAlmacenamientoCSV();//Listo (Falta agregar productos a las unidades)
		loadEncargadosCSV();//Listo
		loadCajerosCSV();//Listo
		loadClientesCSV();//Listo
		
	}

	private void loadClientesCSV() {
		String[] fila = null;

		ArrayList<String[]> filas = readCSV("./data/clientes.csv");
		Iterator<String[]> filas_iterator = filas.iterator();
		
		while(filas_iterator.hasNext()) {
			fila = filas_iterator.next();
			
			String nombre = fila[0];
			int edad = Integer.parseInt(fila[1]);
			char sexo = fila[2].toCharArray()[0];
			String cedula=fila[3];
			String estadoCivil=fila[4];
			String situacionLaboral=fila[5];
			String fechaCompras = fila[6];
			int puntos = Integer.parseInt(fila[7]);
			
			modeladorSupermercado.modelarCliente(nombre,edad,sexo,cedula,estadoCivil,situacionLaboral,fechaCompras, puntos);
		}
		
	}

	private void loadCajerosCSV() {
		String[] fila = null;

		ArrayList<String[]> filas = readCSV("./data/cajeros.csv");
		Iterator<String[]> filas_iterator = filas.iterator();
		
		while(filas_iterator.hasNext()) {
			fila = filas_iterator.next();
			
			String nombre = fila[0];
			String codigo = fila[1];
			
			modeladorSupermercado.modelarCajero(nombre, codigo);
		}
	}

	private void loadEncargadosCSV() {
		String[] fila = null;

		ArrayList<String[]> filas = readCSV("./data/encargados.csv");
		Iterator<String[]> filas_iterator = filas.iterator();
		
		while(filas_iterator.hasNext()) {
			fila = filas_iterator.next();
			
			String nombre = fila[0];
			String codigo = fila[1];
			
			modeladorSupermercado.modelarEncargado(nombre, codigo);
		}
		
	}

	private void loadUnidadesDeAlmacenamientoCSV() throws FileNotFoundException{
		String[] fila = null;

		
		//LEER REFRIGERADORES
		ArrayList<String[]> filas = readCSV("./data/unidadesDeAlmacenamiento/refrigeradores.csv");
		Iterator<String[]> filas_iterator = filas.iterator();
		
		while(filas_iterator.hasNext()) {
			fila = filas_iterator.next();
			
			String id = fila[0];
			int pasillo = Integer.parseInt(fila[1]);
			int capacidad = Integer.parseInt(fila[2]);
			double volumen = Double.parseDouble(fila[3]);
			String[] idProductosAlmacenados = fila[4].replace("(", "").replace(")", "").split("/");
			
			modeladorSupermercado.modelarRefrigerador(id, pasillo, capacidad, idProductosAlmacenados, volumen);
		}
		
		
		//LEER CONGELADORES
		filas = readCSV("./data/unidadesDeAlmacenamiento/congeladores.csv");
		filas_iterator = filas.iterator();
		
		while(filas_iterator.hasNext()) {
			fila = filas_iterator.next();
			
			String id = fila[0];
			int pasillo = Integer.parseInt(fila[1]);
			int capacidad = Integer.parseInt(fila[2]);
			double volumen = Double.parseDouble(fila[3]);
			String[] idProductosAlmacenados = fila[4].replace("(", "").replace(")", "").split("/");
			
			modeladorSupermercado.modelarCongelador(id, pasillo, capacidad, idProductosAlmacenados, volumen);
		}
		
		//LEER GONDOLAS
		filas = readCSV("./data/unidadesDeAlmacenamiento/gondolas.csv");
		filas_iterator = filas.iterator();
		
		while(filas_iterator.hasNext()) {
			fila = filas_iterator.next();
			
			String id = fila[0];
			int pasillo = Integer.parseInt(fila[1]);
			int capacidad = Integer.parseInt(fila[2]);
			int numRepisas = Integer.parseInt(fila[3]);
			String[] idProductosAlmacenados = fila[4].replace("(", "").replace(")", "").split("/");
			
			modeladorSupermercado.modelarGondola(id, pasillo, capacidad, idProductosAlmacenados, numRepisas);
		}
		
		//LEER FRESCOS
		filas = readCSV("./data/unidadesDeAlmacenamiento/gondolas.csv");
		filas_iterator = filas.iterator();
		
		while(filas_iterator.hasNext()) {
			fila = filas_iterator.next();
			
			String id = fila[0];
			int pasillo = Integer.parseInt(fila[1]);
			int capacidad = Integer.parseInt(fila[2]);
			String condicionesAlmacenamiento = fila[3];
			String[] idProductosAlmacenados = fila[4].replace("(", "").replace(")", "").split("/");
			
			modeladorSupermercado.modelarFresco(id, pasillo, capacidad, idProductosAlmacenados, condicionesAlmacenamiento);
		}
		
		
	}

	private void loadInventarioCSV() throws FileNotFoundException {
		
		//Se empieza modelando un nuevo inventario desde 0
		modeladorSupermercado.modelarInventario();
		
		String[] fila = null;
		
		ArrayList<String[]> filas = readCSV("./data/inventario.csv");
		Iterator<String[]> filas_iterator = filas.iterator();
		
		while(filas_iterator.hasNext()) {
			fila = filas_iterator.next();
			
			//Cada fila del inventario representa un producto
			loadProductoCSV(fila);
			
		}

	}

	private void loadSupermercadoCSV() {
		
		String[] fila = null;

		ArrayList<String[]> filas = readCSV("./data/supermercado.csv");
		Iterator<String[]> filas_iterator = filas.iterator();
		
		while(filas_iterator.hasNext()) {
			fila = filas_iterator.next();
		}
		
		String nombreSupermercado = fila[0];
		
		modeladorSupermercado.modelarSupermercado(nombreSupermercado);
		
		
		
	
	}
	
	
	
	private void loadProductoCSV(String[] infoProducto) throws FileNotFoundException{

		String nombre = infoProducto[0];
		String marca = infoProducto[1];
		double precio = Double.parseDouble(infoProducto[2]);
		double precioPuntos = Double.parseDouble(infoProducto[3]);
		
		//Obtener categoria
		String nombreCategoria = infoProducto[4];
		
		
		boolean refrigeracion = Boolean.parseBoolean(infoProducto[5]);
		boolean congelacion = Boolean.parseBoolean(infoProducto[6]);
		
		//Obtener lote 
		String idLoteDeOrigen = infoProducto[7];
		
		
		//Obtener codigo
		String numeroCodigo = infoProducto[8];
		
		//Determinar si es fresco
		boolean fresco = Boolean.parseBoolean(infoProducto[9]);
		
		
		// CARACTERÃ�STICAS EXCLUSIVAS (DIFERENCIACIÃ“N DE PRODUCTOS) //
		
		String volumen = infoProducto[10];
		
		String peso = infoProducto[11];
		
		boolean empacado = Boolean.parseBoolean(infoProducto[12]);
		
		String unidadesIncluidas = infoProducto[13];
		
		String pathImagen = infoProducto[14];
		
		String unidadesPorFecha = infoProducto[15];

		modeladorSupermercado.modelarProducto(nombre, marca, precio, precioPuntos,
				nombreCategoria, refrigeracion, congelacion, idLoteDeOrigen, numeroCodigo, fresco, volumen,peso, empacado, unidadesIncluidas, pathImagen, unidadesPorFecha);
	}
	



	private void loadSubcategoriasCSV() {
		
	String[] fila = null;

	ArrayList<String[]> filas = readCSV("./data/subcategorias.csv");
	Iterator<String[]> filas_iterator = filas.iterator();
	
	while(filas_iterator.hasNext()) {
		fila = filas_iterator.next();
		
		String nombre = fila[0];
		int numeroEstante = Integer.parseInt(fila[1]);
		int nivelEstante = Integer.parseInt(fila[2]);
		
		modeladorSupermercado.modelarSubcategoria(nombre, numeroEstante, nivelEstante);
	}
	}
		

	private void loadCategoriasCSV() {
	
		String[] fila = null;

		ArrayList<String[]> filas = readCSV("./data/categorias.csv");
		Iterator<String[]> filas_iterator = filas.iterator();
		
		while(filas_iterator.hasNext()) {
			fila = filas_iterator.next();
			
			String nombre = fila[0];
			int pasillo = Integer.parseInt(fila[1]);
			String nombreSubcategoria = fila[2];
			
			modeladorSupermercado.modelarCategoria(nombre, pasillo, nombreSubcategoria);
		}
		
			}
	
	
	private void loadLotesCSV() {
		String[] fila = null;
		Date fechaVencimiento = null;

		ArrayList<String[]> filas = readCSV("./data/lotes.csv");
		Iterator<String[]> filas_iterator = filas.iterator();
		
		while(filas_iterator.hasNext()) {
			fila = filas_iterator.next();
			
			String identificadorLote = fila[0]; 
			try {
				fechaVencimiento = new SimpleDateFormat("dd/MM/yyyy").parse(fila[1]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			int numeroProductosBase = Integer.parseInt(fila[2]);
			int numeroProductosRestantes = Integer.parseInt(fila[3]);
			double precioCompraUnidad = Double.parseDouble(fila[4]);
			double precioVentaUnidad = Double.parseDouble(fila[5]);
			String idProducto = fila[6];
			boolean vencido = Boolean.parseBoolean(fila[7]);
			
			modeladorSupermercado.modelarLote(identificadorLote, fechaVencimiento, numeroProductosBase, numeroProductosRestantes, precioCompraUnidad, precioVentaUnidad, idProducto, vencido);
			
		}
	}
	
		
	public String[] loadArchivoPromo(String path){
		File file=new File(path);
		if (!file.exists()) {
			return null;
		}
		
		ArrayList<String[]> lectura = readCSV(path);
		return lectura.get(0);
	}
	
	
	public ArrayList<String[]> readCSV(String pathArchivo){
		
			//ArrayList con las filas analizadas
			ArrayList<String[]> filas = new ArrayList<String[]>();
		
			// Abrir el archivo y leerlo lÃ­nea por lÃ­nea usando un BufferedReader
			try(BufferedReader br = new BufferedReader(new FileReader(pathArchivo));) {
			String linea = br.readLine(); // La primera lÃ­nea del archivo se ignora porque Ãºnicamente tiene los tÃ­tulos de
											// las columnas
			linea = br.readLine();
			while (linea != null) // Cuando se llegue al final del archivo, linea tendrÃ¡ el valor null
			{
				// Separar los valores que estaban en una lÃ­nea
				String[] partes = linea.split(",");
				filas.add(partes);
				
				linea = br.readLine(); // Leer la siguiente lÃ­nea
		}
			br.close();}
			
			catch (IOException ioe) {
	            ioe.printStackTrace();
	        }
			
			return filas;

	
	
	

}}
