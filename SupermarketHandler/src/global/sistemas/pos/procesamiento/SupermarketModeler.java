package global.sistemas.pos.procesamiento;
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
			 String peso,  boolean empacado, String unidadesIncluidas, String pathImagen, String unidadesPorFecha) {
		
		Categoria categoria = mapaTemporalCategorias.get(nombreCategoria);
		Lote lote = mapaTemporalLotes.get(idDelote);
		Codigo codigo = modelarCodigo(numeroDeCodigo);

		//Se rescata el Ãºltimo path del producto (todo cambia con la llegada de un nuevo lote menos la imagen)
				if(supermercado.getProducto(numeroDeCodigo) != null) {
					pathImagen = supermercado.getProducto(numeroDeCodigo).getPathImagen();
				}
		
		//SE MODELA EL OBJETO DE ACUERDO A SI PRESENTA LAS CARACTERÃ�STICAS ESPECIALES
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
		

		

		lote.setProducto(producto);
		
		producto.setPrecios(precio);
		producto.setCategoria(categoria);
		producto.setRefrigeracion(refrigeracion);
		producto.setCongelacion(congelacion);
		producto.setFresco(fresco);
		producto.setPathImagen(pathImagen);
		
		
		//MODELADO DE NUEVA CARACTERÍSTICA // FECHAS CON UNIDADES
		if(!unidadesPorFecha.equals("None")) {
		String[] fechasUnidades = unidadesPorFecha.split(";");
		for(int i = 0; i < fechasUnidades.length; i++) {
			
			String[] fechaUnidad = fechasUnidades[i].split(":");
			
			producto.addFechaUnidades(fechaUnidad);
			
		}}
		
		
		
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
	
	public void modelarCompra(String pCajero, String pCliente) {
		Cajero cajero=supermercado.getCajeros().get(pCajero);
		Cliente cliente;
		if (!pCliente.equals("None")) {
			cliente=supermercado.getClientes().get(pCliente);
		}else {
			cliente=null;
		}
		Compra compra= new Compra(cajero,cliente);
		supermercado.setCompraActual(compra);
	}
	
	public void modelarCajero( String nombre,String codigo) {
		Cajero cajero= new Cajero(nombre,codigo);
		supermercado.agregarCajero(cajero);
	}
	
	public void modelarCliente(String nombre, int edad, char sexo, String cedula, String estadoCivil, String situacionLaboral, String fechaCompras, int puntos) {
		Cliente cliente= new Cliente(nombre,edad,sexo,cedula,estadoCivil,situacionLaboral, puntos);
		
		

		
		if(!fechaCompras.contains("None")) {
			
			String[] arrayFechas = fechaCompras.split("-");
			
			for(int i = 0; i<arrayFechas.length; i++)
			cliente.agregarFechaCompra(arrayFechas[i]);
		}
		
		supermercado.agregarCliente(cliente);
	}
	
	public void modelarRefrigerador(String idUnidad, int pasilloUnidad, int capacidad, String[] idsProductosAlmacenados, double volumen) {
		Refrigerador refrigerador = new Refrigerador(idUnidad,pasilloUnidad,capacidad,volumen);
		
		if(!idsProductosAlmacenados[0].contains("None")) {
		for(int i=0; i<idsProductosAlmacenados.length; i++) {
			
			String idProductoActual = idsProductosAlmacenados[i];
			Producto productoActual = supermercado.getProducto(idProductoActual);
			refrigerador.agregarProducto(productoActual);
		}}

		supermercado.agregarUnidadDeAlmacenamiento(refrigerador);
	}
	
	public void modelarCongelador(String idUnidad, int pasilloUnidad, int capacidad, String[] idsProductosAlmacenados, double volumen) {
		Congelador congelador = new Congelador(idUnidad,pasilloUnidad,capacidad,volumen);
		
		if(!idsProductosAlmacenados[0].contains("None")) {
		for(int i=0; i<idsProductosAlmacenados.length; i++) {
			
			String idProductoActual = idsProductosAlmacenados[i];
			Producto productoActual = supermercado.getProducto(idProductoActual);
			congelador.agregarProducto(productoActual);
		}}

		supermercado.agregarUnidadDeAlmacenamiento(congelador);
	}
	
	public void modelarGondola(String idUnidad, int pasilloUnidad, int capacidad, String[] idsProductosAlmacenados, int numRepisas) {
		Gondola gondola = new Gondola(idUnidad,pasilloUnidad,capacidad,numRepisas);
		
		if(!idsProductosAlmacenados[0].contains("None")) {
		for(int i=0; i<idsProductosAlmacenados.length; i++) {
			
			String idProductoActual = idsProductosAlmacenados[i];
			Producto productoActual = supermercado.getProducto(idProductoActual);
			gondola.agregarProducto(productoActual);
		}}

		supermercado.agregarUnidadDeAlmacenamiento(gondola);
	}
	
	
	public void modelarFresco(String idUnidad, int pasilloUnidad, int capacidad, String[] idsProductosAlmacenados, String condicionesAlmacenamiento) {
		FrescoDespensa fresco = new FrescoDespensa(idUnidad,pasilloUnidad,capacidad,condicionesAlmacenamiento);
		
		if(!idsProductosAlmacenados[0].contains("None")) {
		for(int i=0; i<idsProductosAlmacenados.length; i++) {
			
			String idProductoActual = idsProductosAlmacenados[i];
			Producto productoActual = supermercado.getProducto(idProductoActual);
			fresco.agregarProducto(productoActual);
		}}

		supermercado.agregarUnidadDeAlmacenamiento(fresco);
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
