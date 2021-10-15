package global.sistemas.inventario.procesamiento;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import global.modelo.*;



public class SupermarketModeler {
	
	private Supermercado supermercado;
	
	private HashMap<String, Categoria> mapaTemporalCategorias;
	private HashMap<String, Subcategoria> mapaTemporalSubcategorias;
	private HashMap<String, Lote> mapaTemporalLotes;
	
	public SupermarketModeler() {
		
		mapaTemporalCategorias = new HashMap<String, Categoria>();
		mapaTemporalSubcategorias = new HashMap<String, Subcategoria>();
		mapaTemporalLotes = new HashMap<String, Lote>();}
	
	
	//Modelar Supermercado (Macro-objeto)
	public Supermercado modelarSupermercado(String nombre) {
		
		supermercado = new Supermercado(nombre);
		return supermercado;
		
	}
	
	
	//Modelar Inventario
	public Inventario modelarInventario() {
	
		Inventario inventario = new Inventario();
		supermercado.setBodega(inventario);
		return inventario;
	}
	
	
	//Modelar Producto Individual (y agregar a inventario)
	public void modelarProducto(String nombre, String marca, double precio, double precioPuntos, String nombreCategoria,
			boolean refrigeracion, boolean congelacion, String idDelote, String numeroDeCodigo, boolean fresco, String volumen, 
			 String peso,  boolean empacado, String unidadesIncluidas) {
		
		Categoria categoria = mapaTemporalCategorias.get(nombreCategoria);
		Lote lote = mapaTemporalLotes.get(idDelote);
		Codigo codigo = modelarCodigo(numeroDeCodigo);

		
		//SE MODELA EL OBJETO DE ACUERDO A SI PRESENTA LAS CARACTERÍSTICAS ESPECIALES
		Producto producto = new Producto(lote, nombre, marca, codigo);

		if (empacado == true) {
			
			if (!volumen.contains("None")){
				double volumen_double = Double.parseDouble(volumen);
				double precioPormL_double = precio / volumen_double;
				
				producto = new Liquido(lote, nombre, marca, codigo, volumen_double, precioPormL_double);
				
			}
			
			else if (!peso.contains("None")) {
				double peso_double = Double.parseDouble(peso);
				double precioPorgr_double = precio / peso_double;
				
				producto = new Solido(lote, nombre, marca, codigo, peso_double, precioPorgr_double);
				
			}
			
			else {
				int unidadesIncluidas_int = Integer.parseInt(unidadesIncluidas);
				double precioPorUnidad_double = precio/unidadesIncluidas_int;
				
				 producto = new Miscelaneo(lote, nombre, marca, codigo, unidadesIncluidas_int, precioPorUnidad_double);
			}

		}
		
		//EN DADO CASO DE QUE NO SE ENCUENTRE LA CATEGORÍA, ESTA DEBE SER AÑADIDA AL MODELO, PREGUNTANDO SUS CARACTERÍSTICAS AL ENCARGADO
		if (categoria == null) {
			
			ArrayList<String> infoCategoria = new HandlerSI().askCategoria(nombre);
			
			String nombreCat = infoCategoria.get(0);
			int pasilloCat = Integer.parseInt(infoCategoria.get(1));
			HashMap<String, Subcategoria> subcategorias = new HashMap<String, Subcategoria>();
			
			
			String[] nombreSubCats = infoCategoria.get(2).split("-");
			for(int i = 0; i<nombreSubCats.length; i++) {
				
				String nombreSubCat = nombreSubCats[i];
				ArrayList<String> subCatInfo = new HandlerSI().askSubcategoria(nombreSubCat);
				
				int numeroEstanteSubcat = Integer.parseInt(subCatInfo.get(0));
				int nivelEstanteSubcar = Integer.parseInt(subCatInfo.get(1));
				
				Subcategoria subcategoriaLocal = new Subcategoria(nombreSubCat, numeroEstanteSubcat, nivelEstanteSubcar);
				subcategorias.put(nombreSubCat, subcategoriaLocal);
				
			}
			
			 categoria = new Categoria(nombreCat, pasilloCat, subcategorias);
			
			}
		
		
		
		
		lote.setProducto(producto);
		
		producto.setPrecios(precio);
		producto.setCategoria(categoria);
		producto.setRefrigeracion(refrigeracion);
		producto.setCongelacion(congelacion);
		producto.setFresco(fresco);
		
		//Producto agregado al inventario
		supermercado.getBodega().agregarProducto(producto);
		
	}
	
	
	//Modelar Categoria Individual (y agregar al mapa)
	public void modelarCategoria(String nombre, int pasillo, String nombresSubcategorias) {
		
		HashMap<String, Subcategoria> mapaLocalSubCats = new HashMap<String, Subcategoria>();
		
		String[] nombresSubCatSplit = nombresSubcategorias.split("-");
		for(int i = 0; i<nombresSubCatSplit.length; i++) {
			String nombreSubCat = nombresSubCatSplit[i];
			Subcategoria subcategoria = mapaTemporalSubcategorias.get(nombreSubCat);
			
			mapaLocalSubCats.put(nombreSubCat, subcategoria);
		}
		

		
		Categoria categoria = new Categoria(nombre, pasillo, mapaLocalSubCats);
		mapaTemporalCategorias.put(nombre, categoria);
	}
	
	
	//Modelas Subcategoria Individual (y agregar al mapa)
	public void modelarSubcategoria(String nombre, int numeroEstante, int nivelEstante) {
		
		Subcategoria subcategoria = new Subcategoria(nombre, numeroEstante, nivelEstante);
		mapaTemporalSubcategorias.put(nombre, subcategoria);
	}
	
	//Modelar Lote individual
	public void modelarLote(String identificadorLote, Date fechaVencimiento, int numeroProductosBase, int numeroProductosRestantes, double precioCompraUnidad, double precioVentaUnidad, String idProducto, boolean vencido) {

		Producto producto = supermercado.getBodega().getProductos().get(idProducto);
		
		Lote lote = new Lote(identificadorLote, fechaVencimiento, numeroProductosBase, numeroProductosRestantes, precioCompraUnidad, precioVentaUnidad, producto);
		lote.setVencido(vencido);
		mapaTemporalLotes.put(identificadorLote, lote);
		
	}
	
	public void modelarEncargado(String nombre, String codigo) {
		EncargadoInventario encargado= new EncargadoInventario(nombre,codigo);
		supermercado.agregarEncargado(encargado);
	}
	
	public void modelarCajero(String nombre, String codigo) {
		Cajero cajero= new Cajero(nombre,codigo);
		supermercado.agregarCajero(cajero);
	}
	
	public void modelarCliente(String nombre, int edad, char sexo, String cedula, String estadoCivil, String situacionLaboral) {
		Cliente cliente= new Cliente(nombre,edad,sexo,cedula,estadoCivil,situacionLaboral);
		supermercado.agregarCliente(cliente);
	}
	
	public void modelarUnidad(String idUnidad, int pasilloUnidad, int capacidad, String[] idsProductosAlmacenados) {
		UnidadDeAlmacenamiento unidad= new UnidadDeAlmacenamiento(idUnidad,pasilloUnidad,capacidad);
		
		for(int i=0; i<idsProductosAlmacenados.length; i++) {
			
			String idProductoActual = idsProductosAlmacenados[i];
			Producto productoActual = supermercado.getProducto(idProductoActual);
			unidad.agregarProducto(productoActual);
		}

		supermercado.agregarUnidadDeAlmacenamiento(unidad);
	}
	
	public Codigo modelarCodigo(String idProducto) {
		Codigo codigo;
		
		if(idProducto.contains("P-")) {
			codigo = new CodigoInterno(idProducto);
		}
		else {
			codigo = new CodigoDeBarras(idProducto);
		}
		
		return codigo;
	}
	
	
	
	
	
	public Supermercado getSupermercado() {
		return this.supermercado;
	}




}
