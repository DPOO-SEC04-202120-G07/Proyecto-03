package global.modelo;

import java.util.HashMap;

public class Categoria {
	
	private String nombre;
	private int pasillo;
	private HashMap<String, Subcategoria> subcategorias;

	//Método constructor que tiene en cuenta todos los parámetros
	public Categoria(String nombre, int pasillo, HashMap<String, Subcategoria> subcategorias){
		this.nombre = nombre;
		this.pasillo = pasillo;
		subcategorias = new HashMap<String, Subcategoria>();
	}
	


	//Setters en caso de que se necesite cambiar un aspecto de la categoría
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPasillo(int pasillo) {
		this.pasillo = pasillo;
	}
	

	
	
	//Getters de todos los atributos
	public String getNombre() {
		return this.nombre;
	}
	
	public int getPasillo() {
		return this.pasillo;
	}

	public HashMap<String, Subcategoria> getSubcategorias() {
		return subcategorias;
	}

	public void agregarSubcategoria(Subcategoria subcategoria) {
		this.subcategorias.put(subcategoria.getNombre(), subcategoria);
	}
	

	
}
