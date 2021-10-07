package global.modelo;

public class Categoria {
	
	private String nombre;
	private int pasillo;
	private Subcategoria subcategoria;

	//Método constructor que tiene en cuenta todos los parámetros
	Categoria(String nombre, int pasillo, Subcategoria subcategoria){
		this.nombre = nombre;
		this.pasillo = pasillo;
		this.subcategoria = subcategoria;
	}
	
	//Setters en caso de que se necesite cambiar un aspecto de la categoría
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPasillo(int pasillo) {
		this.pasillo = pasillo;
	}
	
	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}
	
	
	//Getters de todos los atributos
	public String getNombre() {
		return this.nombre;
	}
	
	public int getPasillo() {
		return this.pasillo;
	}
	
	public Subcategoria getSubcategoria() {
		return this.subcategoria;
	}
	
}
