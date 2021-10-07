package global.modelo;

public class Subcategoria {

	private String nombre;
	private int numeroEstante;
	private int nivelEstante;

	//Método constructor que tiene en cuenta todos los parametros
	public Subcategoria(String nombre, int numeroEstante, int nivelEstante){
		this.nombre = nombre;
		this.numeroEstante = numeroEstante;
		this.nivelEstante = nivelEstante;
	}
	
	//Setters en caso de que se cambie la ubicación de un producto
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setNumeroEstante(int numeroEstante) {
		this.numeroEstante = numeroEstante;
	}
	
	public void setNivelEstante(int nivelEstante) {
		this.numeroEstante = nivelEstante;
	}
	
	
	//Getters de los atributos
	public String getNombre() {
		return this.nombre;
	}
	
	public int getNumeroEstante() {
		return this.numeroEstante;
	}
	
	public int getNivelEstante() {
		return this.nivelEstante;
	}
}
