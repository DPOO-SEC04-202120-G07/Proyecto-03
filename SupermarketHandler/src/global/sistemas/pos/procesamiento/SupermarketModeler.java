package global.sistemas.pos.procesamiento;
import java.util.Date;
import java.util.HashMap;

import global.modelo.*;


public class SupermarketModeler {
	
	private Supermercado supermercado;
	
	private HashMap<String, Categoria> mapaTemporalCategorias;
	private HashMap<String, Subcategoria> mapaTemporalSubategorias;
	private HashMap<String, Lote> mapaTemporalLotes;
	
	
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
		producto.setPrecios(precio);
		producto.setCategoria(categoria);
		producto.setRefrigeracion(refrigeracion);
		producto.setCongelacion(congelacion);
		
		//Producto agregado al inventario
		supermercado.getBodega().agregarProducto(producto);
		
	}
	
	
	//Modelar Categoria Individual (y agregar al mapa)
	public void modelarCategoria(String nombre, int pasillo, String nombreSubcategoria) {
		
		Subcategoria subcategoria = mapaTemporalSubategorias.get(nombreSubcategoria);
		
		Categoria categoria = new Categoria(nombre, pasillo, subcategoria);
		mapaTemporalCategorias.put(nombre, categoria);
	}
	
	
	//Modelas Subcategoria Individual (y agregar al mapa)
	public void modelarSubcategoria(String nombre, int numeroEstante, int nivelEstante) {
		
		Subcategoria subcategoria = new Subcategoria(nombre, numeroEstante, nivelEstante);
		mapaTemporalSubategorias.put(nombre, subcategoria);
	}
	
	//Modelar Lote individual
	public void modelarLote(String identificadorLote, Date fechaVencimiento, int numeroProductosBase, int numeroProductosRestantes, double precioCompraUnidad, double precioVentaUnidad, String idProducto) {

		Codigo codigo = modelarCodigo(idProducto);
		
		Producto producto = supermercado.getBodega().getProductos().get(codigo);
		
		Lote lote = new Lote(identificadorLote, fechaVencimiento, numeroProductosBase, numeroProductosRestantes, precioCompraUnidad, precioVentaUnidad, producto);
		mapaTemporalLotes.put(identificadorLote, lote);
		
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
