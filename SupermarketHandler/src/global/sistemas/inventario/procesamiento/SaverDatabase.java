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
		
		//Guarda las unidades de almacenamiento
		saveUnidadesDeAlmacenamiento();
		
		
	}

	private void saveInventarioCSV() {
		
		Inventario inventarioVolatil = supermercadoVolatil.getBodega();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		
		HashMap<String, Categoria> mapaFiltradorCategorias = new HashMap<String, Categoria>();
		HashMap<String, Subcategoria> mapaFiltradorSubcategorias = new HashMap<String, Subcategoria>();
		
		
		ArrayList<String[]> lineasInventario = new ArrayList<String[]>();
		lineasInventario.add(new String[]{"nombreProducto", "marcaProducto", "precioVentaUnidad", "precioPuntos", "nombreCategoria", "refrigeracion", 
				"congelacion", "identificadorLote", "idProducto", "fresco", "volumen", "peso", "empacado", "unidadesIncluidas", "pathImagen"});
		
		ArrayList<String[]> lineasLotes = new ArrayList<String[]>();
		lineasLotes.add(new String[]{"identificadorLote", "fechaVencimiento", "numeroProductosBase", "numeroProductosRestantes", "precioCompraUnidad", "precioVentaUnidad", "idProducto", "vencido"});
		
		
		ArrayList<String[]> lineasCategoria = new ArrayList<String[]>();
		lineasCategoria.add(new String[] {"nombre","pasillo","subcategoria"});
		
		ArrayList<String[]> lineasSubcategoria = new ArrayList<String[]>();
		lineasSubcategoria.add(new String[] {"nombre", "numeroEstante", "nivelEstante"});
		
		ArrayList<String[]> lineasEncargados= new ArrayList<String[]>();
		lineasEncargados.add(new String[]{"nombre", "codigoE"});
		
	    File csvInventarioFile = new File("./data/inventario.csv");
	    File csvLotesFile = new File("./data/lotes.csv");
	    File csvCategoriasFile = new File("./data/categorias.csv");
	    File csvSubcategoriasFile = new File("./data/subcategorias.csv");
	    File csvEncargadosFile = new File("./data/encargados.csv");
	    
	    Iterator<EncargadoInventario> iterEncargados = supermercadoVolatil.getEncargados().values().iterator();
	    
	    while(iterEncargados.hasNext()) {
	    	EncargadoInventario encargadoActual=iterEncargados.next();
	    	
	    	String nombre=encargadoActual.getNombre();
	    	String codigoE=encargadoActual.getCodigoEncargado();
	    	
	    	String[] lineaEncargado= {nombre,codigoE};
	    	lineasEncargados.add(lineaEncargado);
	    }
	    
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
				
				String pathImagen = lote.getProducto().getPathImagen();
				
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
				
				
				
				
				
				String[] lineaInventario = {nombreProducto, marca, precioVentaUnidad, precioPuntos, categoria, refrigeracion, congelacion, identificadorLote, codigoProducto, fresco, volumen, peso, empacado, unidadesIncluidas, pathImagen};
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
	    
	  //Escribir sobre encargados.csv
	    try (PrintWriter pw = new PrintWriter(csvEncargadosFile)) {
	    	lineasEncargados.stream()
	          .map(this::convertToCSV)
	          .forEach(pw::println);
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	}
	
		public void saveUnidadesDeAlmacenamiento(){
			
		    File csvRefrigeradoresFile = new File("./data/unidadesDeAlmacenamiento/refrigeradores.csv");
		    File csvCongeladoresFile = new File("./data/unidadesDeAlmacenamiento/congeladores.csv");
		    File csvGondolasFile = new File("./data/unidadesDeAlmacenamiento/gondolas.csv");
		    File csvFrescosFile = new File("./data/unidadesDeAlmacenamiento/frescos.csv");
		    
		    
			ArrayList<String[]> lineasRefrigeradores = new ArrayList<String[]>();
			lineasRefrigeradores.add(new String[]{"idUnidad","pasilloUnidad","capacidad","volumen", "idProductosAlmacenados"});
			
			ArrayList<String[]> lineasCongeladores = new ArrayList<String[]>();
			lineasCongeladores.add(new String[]{"idUnidad","pasilloUnidad","capacidad","volumen", "idProductosAlmacenados"});
			
			ArrayList<String[]> lineasGondolas = new ArrayList<String[]>();
			lineasGondolas.add(new String[]{"idUnidad","pasilloUnidad","capacidad","numRepisas", "idProductosAlmacenados"});
			
			ArrayList<String[]> lineasFrescos = new ArrayList<String[]>();
			lineasFrescos.add(new String[]{"idUnidad","pasilloUnidad","capacidad","condicionesAlmacenamiento", "idProductosAlmacenados"});

			
			Iterator<UnidadDeAlmacenamiento> iterUnidades = supermercadoVolatil.getUnidadesDeAlmacenamiento().values().iterator();
			
			while(iterUnidades.hasNext()) {
				UnidadDeAlmacenamiento unidadActual = iterUnidades.next();
				
				String idUnidad = unidadActual.getIdUnidad();
				String pasilloUnidad = "" + unidadActual.getPasilloUnidad();
				String capacidad = "" + unidadActual.getCapacidad();
		
				String productosAlmacenados = "";
				Iterator<Codigo> iterProductos = unidadActual.getProductos().keySet().iterator();
				while(iterProductos.hasNext()) {
					Codigo productoActual = iterProductos.next();
					String idProducto = productoActual.getCodigo();
					productosAlmacenados += idProducto + "/";
				}
				
				if (unidadActual.getProductos().size() == 0) {
					productosAlmacenados = "None/";
				}
				
				
				if(unidadActual.getTipo() == "refrigerador") {
					
					String volumen = ""+unidadActual.getVolumen();
					String[] lineaRefrigerador = {idUnidad, pasilloUnidad, capacidad, volumen, productosAlmacenados};
					lineasRefrigeradores.add(lineaRefrigerador);
			}
				
				else if(unidadActual.getTipo() == "congelador") {
					
					String volumen = ""+unidadActual.getVolumen();
					String[] lineaCongelador = {idUnidad, pasilloUnidad, capacidad, volumen, productosAlmacenados};
					lineasCongeladores.add(lineaCongelador);
			}
			
				else if(unidadActual.getTipo() == "gondola") {
					
					String numRepisa = ""+unidadActual.getNumRepisas();
					String[] lineaGondola = {idUnidad, pasilloUnidad, capacidad, numRepisa, productosAlmacenados};
					lineasGondolas.add(lineaGondola);
			}
				
				else if(unidadActual.getTipo() == "fresco") {
					
					String condicionesAlmacenamiento = unidadActual.getCondicionesConservacion();
					String[] lineaFresco = {idUnidad, pasilloUnidad, capacidad, condicionesAlmacenamiento, productosAlmacenados};
					lineasFrescos.add(lineaFresco);
					
					
			}
			
		}
			
			//Escribir sobre refrigeradores.csv
		    try (PrintWriter pw = new PrintWriter(csvRefrigeradoresFile)) {
		    	lineasRefrigeradores.stream()
		          .map(this::convertToCSV)
		          .forEach(pw::println);
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    
			//Escribir sobre congeladores.csv
		    try (PrintWriter pw = new PrintWriter(csvCongeladoresFile)) {
		    	lineasCongeladores.stream()
		          .map(this::convertToCSV)
		          .forEach(pw::println);
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			//Escribir sobre gondolas.csv
		    try (PrintWriter pw = new PrintWriter(csvGondolasFile)) {
		    	lineasGondolas.stream()
		          .map(this::convertToCSV)
		          .forEach(pw::println);
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    
			//Escribir sobre frescos.csv
		    try (PrintWriter pw = new PrintWriter(csvFrescosFile)) {
		    	lineasFrescos.stream()
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


