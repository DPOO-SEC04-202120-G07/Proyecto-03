package global.modelo;

public class Cajero {
	
	private String nombre;
	private String codigoCajero;
	private Cliente clienteActual;
	
	public Cajero(String nombre, String codigoCajero) {
		this.codigoCajero = codigoCajero;
		this.nombre = nombre;
		this.clienteActual = null;
	}

	
	//Nombre Cajero
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	//Codigo Cajero
	public String getCodigoCajero() {
		return this.codigoCajero;
	}

	public void setCodigoCajero(String codigoCajero) {
		this.codigoCajero = "C-" + codigoCajero;
	}


	//Cliente Actual
	public Cliente getClienteActual() {
		return this.clienteActual;
	}


	public void setClienteActual(Cliente clienteActual) {
		this.clienteActual = clienteActual;
	}
}
