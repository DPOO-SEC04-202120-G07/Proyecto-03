package global.modelo;

import java.util.HashMap;

public class Inventario {
	
	private HashMap<Codigo, Producto> productosEnInventario;
	
	public Inventario() {
		
	}
	
	//Métodos de manejo de productos disponibles en el inventario
	public void agregarProducto(Producto producto) {
       productosEnInventario.put(producto.getCodigoProducto(), producto);
	}
	
	public void eliminarProducto(Producto producto) {
		if (productosEnInventario.size()>0) {productosEnInventario.remove(producto.getCodigoProducto(), producto);}
	}
	
	public HashMap<Codigo, Producto> getProductos(){
		return this.productosEnInventario;
	}
	
}
	
	
