package global.modelo;

import java.util.HashMap;

public class Inventario {
	
	private HashMap<String, Producto> productosEnInventario;
	
	public Inventario() {
		
		productosEnInventario = new HashMap<String, Producto>();
		
	}
	
	//Métodos de manejo de productos disponibles en el inventario
	public void agregarProducto(Producto producto) {
       productosEnInventario.put(producto.getCodigoProducto().getCodigo(), producto);
	}
	
	public void eliminarProducto(Producto producto) {
		if (productosEnInventario.size()>0) {productosEnInventario.remove(producto.getCodigoProducto(), producto);}
	}
	
	public HashMap<String, Producto> getProductos(){
		return this.productosEnInventario;
	}
	
}
	
	
