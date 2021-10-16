package global.sistemas.inventario.procesamiento;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Stream;


import java.util.stream.Collectors;


import global.modelo.*;

public class SaverDatabase {
	
	Supermercado supermercadoVolatil;

	public void saveDatabaseCSV(SupermarketModeler supermarketModeler) {
	
		this.supermercadoVolatil = supermarketModeler.getSupermercado();
	
		//Guardar lotes/productos cargados en inventario.csv
		saveInventarioCSV();
		
		
	}

	private void saveInventarioCSV() {
		
		Inventario inventarioVolatil = supermercadoVolatil.getBodega();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		
		HashMap<String, Categoria> mapaFiltradorCategorias = new HashMap<String, Categoria>();
		HashMap<String, Subcategoria> mapaFiltradorSubcategorias = new HashMap<String, Subcategoria>();
		
		
		ArrayList<String[]> lineasInventario = new ArrayList<String[]>();
		lineasInventario.add(new String[]{"nombreProducto", "marcaProducto", "precioVentaUnidad", "precioPuntos", "nombreCategoria", "refrigeracion", 
				"congelacion", "identificadorLote", "idProducto", "fresco", "volumen", "peso", "empacado", "unidadesIncluidas"});
		
		ArrayList<String[]> lineasLotes = new ArrayList<String[]>();
		lineasLotes.add(new String[]{"identificadorLote", "fechaVencimiento", "numeroProductosBase", "numeroProductosRestantes", "precioCompraUnidad", "precioVentaUnidad", "idProducto", "vencido"});
		
		
		ArrayList<String[]> lineasCategoria = new ArrayList<String[]>();
		lineasCategoria.add(new String[] {"nombre","pasillo","subcategoria"});
		
		ArrayList<String[]> lineasSubcategoria = new ArrayList<String[]>();
		lineasSubcategoria.add(new String[] {"nombre", "numeroEstante", "nivelEstante"});
		
	    File csvInventarioFile = new File("./data/inventario.csv");
	    File csvLotesFile = new File("./data/lotes.csv");
	    File csvCategoriasFile = new File("./data/categorias.csv");
	    File csvSubcategoriasFile = new File("./data/subcategorias.csv");
	    
		
		Iterator<Producto> iterProductos = inventarioVolatil.getProductos().values().iterator();
		while(iterProductos.hasNext()) {
			Producto productoActual = iterProductos.next();
			
			Iterator<Lote> iterLotesConProducto = productoActual.getLotesDeOrigen().iterator();
			while(iterLotesConProducto.hasNext()) {
				Lote lote = iterLotesConProducto.next();

				String identificadorLote = lote.getIdentificadorLote();
				String fechaVencimiento = dateFormat.format(lote.getfechaVencimiento());
				String numeroProductosBase = "" + lote.getNumeroProductosBase();
				String numeroProductosRestantes = "" + lote.getNumeroProductosRestantes();
				String precioCompraUnidad = "" + lote.getPrecioCompraUnidad();
				String precioVentaUnidad = "" + lote.getPrecioVentaUnidad(); 
				String precioPuntos = "" +lote.getProducto().getPrecioPuntos();
				
				String codigoProducto = lote.getProducto().getCodigoProducto().getCodigo();
				String nombreProducto = lote.getProducto().getNombre();
				String marca = lote.getProducto().getMarca();
				String categoria = lote.getProducto().getCategoria().getNombre();
				String refrigeracion = ""+lote.getProducto().getRefrigeracion();
				String congelacion = ""+lote.getProducto().getRefrigeracion();
				String vencido = "" +lote.isVencido();
				
				String fresco = "" + lote.getProducto().isFresco();
				String empacado = "true";
				String volumen = "None";
				String peso = "None";
				String unidadesIncluidas = "None";
				
				
				volumen = ""+ lote.getProducto().getVolumen();
				if(volumen == "0") {
					volumen = "None";
					empacado = "false";}
		
				
				 peso = "" + lote.getProducto().getPeso();
				if(peso == "0") {
					 peso = "None";
					empacado = "false";}
	
				

				unidadesIncluidas = "" + lote.getProducto().getUnidades();
				if (unidadesIncluidas == "0") {
				 unidadesIncluidas = "None";
				empacado = "false";}
				
				
				
				
				
				String[] lineaInventario = {nombreProducto, marca, precioVentaUnidad, precioPuntos, categoria, refrigeracion, congelacion, identificadorLote, codigoProducto, fresco, volumen, peso, empacado, unidadesIncluidas};
				String[] lineaLote = {identificadorLote, fechaVencimiento, numeroProductosBase, numeroProductosRestantes, precioCompraUnidad, precioVentaUnidad, codigoProducto, vencido};
		
				lineasInventario.add(lineaInventario);
				lineasLotes.add(lineaLote);
				
				
				//Guardado categorias
				Categoria categoria_completa = lote.getProducto().getCategoria();
				String nombre_categoria_completa = lote.getProducto().getCategoria().getNombre();
				String pasillo_categoria_completa = ""+lote.getProducto().getCategoria().getPasillo();
				
				String subcategorias_categoria = "";
				HashMap<String, Subcategoria> mapaSubcategorias = lote.getProducto().getCategoria().getSubcategorias();
				Iterator<Subcategoria> iterSubcats= mapaSubcategorias.values().iterator();
				while(iterSubcats.hasNext()) {
					Subcategoria actualSubcat = iterSubcats.next();
					String numeroEstanteSubcat = "" + actualSubcat.getNumeroEstante();
					String nivelEstanteSubcat = "" + actualSubcat.getNivelEstante();
					
					subcategorias_categoria += actualSubcat.getNombre() + "-";
					
					
					//Guardado subcategorias
					if(!mapaFiltradorSubcategorias.containsKey(actualSubcat.getNombre())) {
						mapaFiltradorSubcategorias.put(actualSubcat.getNombre(), actualSubcat);
						String[] lineaSubcategoria = {actualSubcat.getNombre(), numeroEstanteSubcat, nivelEstanteSubcat};
						lineasSubcategoria.add(lineaSubcategoria);
						
					}
				}

				
				if(!mapaFiltradorCategorias.containsKey(nombre_categoria_completa)) {
					mapaFiltradorCategorias.put(nombre_categoria_completa, categoria_completa);
					String[] lineaCategoria = {nombre_categoria_completa, pasillo_categoria_completa, subcategorias_categoria};
					lineasCategoria.add(lineaCategoria);
				}
				
				

				
				
				
				
			}}
		
		//Escribir sobre inventario.csv
	    try (PrintWriter pw = new PrintWriter(csvInventarioFile)) {
	    	lineasInventario.stream()
	          .map(this::convertToCSV)
	          .forEach(pw::println);
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    //Escribir sobre lotes.csv
	    try (PrintWriter pw = new PrintWriter(csvLotesFile)) {
	    	lineasLotes.stream()
	          .map(this::convertToCSV)
	          .forEach(pw::println);
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    
	    
	    //Escribir sobre categorias.csv
	    try (PrintWriter pw = new PrintWriter(csvCategoriasFile)) {
	    	lineasCategoria.stream()
	          .map(this::convertToCSV)
	          .forEach(pw::println);
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    //Escribir sobre subcategorias.csv
	    try (PrintWriter pw = new PrintWriter(csvSubcategoriasFile)) {
	    	lineasSubcategoria.stream()
	          .map(this::convertToCSV)
	          .forEach(pw::println);
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	
	
		
		
		//Métodos para cargar al CSV tomados de https://www.baeldung.com/java-csv
		
		public String convertToCSV(String[] data) {
		    return Stream.of(data)
		      .collect(Collectors.joining(","));
		}
		
	}


