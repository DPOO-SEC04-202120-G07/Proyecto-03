package global.modelo;

import java.util.HashMap;

public class UnidadDeAlmacenamiento {
	
	private int idUnidad;
	private int pasilloUnidad;
	private int capacidad;
	
	private HashMap<Codigo, Producto> productos;
	
	
	public UnidadDeAlmacenamiento(int idUnidad, int pasilloUnidad, int capacidad){
		this.setIdUnidad(idUnidad);
		this.setPasilloUnidad(pasilloUnidad);
		this.setCapacidad(capacidad);
	}

	public int getIdUnidad() {
		return this.idUnidad;
	}

	public void setIdUnidad(int idUnidad) {
		this.idUnidad = idUnidad;
	}

	public int getPasilloUnidad() {
		return this.pasilloUnidad;
	}

	public void setPasilloUnidad(int pasilloUnidad) {
		this.pasilloUnidad = pasilloUnidad;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	//Métodos de manejo de productos disponibles en el inventario
	public void agregarProducto(Producto producto) {
       productos.put(producto.getCodigoProducto(), producto);
	}
	
	public void eliminarProducto(Producto producto) {
		if (productos.size()>0) {productos.remove(producto.getCodigoProducto(), producto);}
	}
	

}
