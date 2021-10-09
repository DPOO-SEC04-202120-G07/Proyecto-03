package global.modelo;

public class EncargadoInventario {

	private String nombre;
	private String codigoEncargado;

	
	public EncargadoInventario(String nombre, String codigoEncargado) {
		this.setNombre(nombre);
		this.setCodigoEncargado(codigoEncargado);
	}


	//Nombre
	public String getNombre() {
		return this.nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	//Codigo Encargado
	public String getCodigoEncargado() {
		return this.codigoEncargado;
	}


	public void setCodigoEncargado(String codigoEncargado) {
		this.codigoEncargado = "E-"+codigoEncargado;
	}
	
}
