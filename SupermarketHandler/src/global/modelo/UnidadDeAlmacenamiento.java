package global.modelo;

import java.util.HashMap;

public class UnidadDeAlmacenamiento {
	
	private int idUnidad;
	private int pasilloUnidad;
	private int capacidad;
	
	private HashMap<String, Producto> productos;
	
	
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
	
	//Métodos de manejo de productos disponibles en la unidad
	public void agregarProducto(Codigo codigoProducto) {
		
		
	}
	

}
