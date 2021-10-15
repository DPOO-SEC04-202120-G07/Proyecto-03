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
			boolean refrigeracion, boolean congelacion, String idDelote, String numeroDeCodigo) {
		
		Categoria categoria = mapaTemporalCategorias.get(nombreCategoria);
		Lote lote = mapaTemporalLotes.get(idDelote);
		Codigo codigo = modelarCodigo(numeroDeCodigo);
		
		Producto producto = new Producto(lote, nombre, marca, codigo);
		lote.setProducto(producto);
		
		producto.setPrecios(precio);
		producto.setCategoria(categoria);
		producto.setRefrigeracion(refrigeracion);
		producto.setCongelacion(congelacion);
		
		//Producto agregado al inventario
		supermercado.getBodega().agregarProducto(producto);
		
	}
	
	
	//Modelar Categoria Individual (y agregar al mapa)
	public void modelarCategoria(String nombre, int pasillo, String nombreSubcategoria) {
		
		Subcategoria subcategoria = mapaTemporalSubcategorias.get(nombreSubcategoria);
		
		Categoria categoria = new Categoria(nombre, pasillo, subcategoria);
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
	
	public void modelarCliente(String nombre, int edad, char sexo, String cedula, String estadoCivil, String situacionLaboral) {
		Cliente cliente= new Cliente(nombre,edad,sexo,cedula,estadoCivil,situacionLaboral);
		supermercado.agregarCliente(cliente);
	}
	
	public void modelarUnidad(String idUnidad, int pasilloUnidad, int capacidad) {
		UnidadDeAlmacenamiento unidad= new UnidadDeAlmacenamiento(idUnidad,pasilloUnidad,capacidad);
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
