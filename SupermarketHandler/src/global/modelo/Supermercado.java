package global.modelo;

import java.util.HashMap;

public class Supermercado {
	
	private String nombre;
	
	private HashMap<String, EncargadoInventario> encargados;
	private HashMap<String, Cajero> cajeros;
	private HashMap<String, Cliente> clientes;
	
	private Inventario bodega;
	private HashMap<String, UnidadDeAlmacenamiento>unidadesDeAlmacenamiento;

	public Supermercado(String nombre) {
		setNombre(nombre);
		
		this.encargados = new HashMap<String, EncargadoInventario>();
		this.cajeros = new HashMap<String, Cajero>();
		this.clientes = new HashMap<String, Cliente>();
		
		this.setBodega(new Inventario());
		this.unidadesDeAlmacenamiento = new HashMap<String, UnidadDeAlmacenamiento>();
	}
	
	
	
	//Nombre
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	//Encargados
	public void agregarEncargado(EncargadoInventario encargado) {
		
		String idEncargado = encargado.getCodigoEncargado();
		encargados.put(idEncargado, encargado);
	}
	
	public void eliminarEncargado(EncargadoInventario encargado) {
		String idEncargado = encargado.getCodigoEncargado();
		encargados.remove(idEncargado, encargado);
	}
	
	public  HashMap<String, EncargadoInventario> getEncargados() {
		return this.encargados;
	}

	//Cajeros
	public void agregarCajero(Cajero cajero) {
		
		String idCajero = cajero.getCodigoCajero();
		cajeros.put(idCajero, cajero);
	}
	
	public void eliminarCajero(Cajero cajero) {
		String idCajero = cajero.getCodigoCajero();
		cajeros.remove(idCajero, cajero);
	}
	
	public  HashMap<String, Cajero> getCajeros() {
		return this.cajeros;
	}
	
	//Clientes
	public void agregarCliente(Cliente cliente) {
		String cedulaCliente = cliente.getCedula();
		clientes.put(cedulaCliente, cliente);
	}
	
	public void eliminarCliente(Cliente cliente) {
		String cedulaCliente = cliente.getCedula();
		clientes.remove(cedulaCliente, cliente);
	}

	
	
	//Inventario (Bodega)
	public Inventario getBodega() {
		return this.bodega;
	}

	public void setBodega(Inventario bodega) {
		this.bodega = bodega;
	}


	//Unidades de Almacenamiento
	public HashMap<String, UnidadDeAlmacenamiento> getUnidadesDeAlmacenamiento() {
		return this.unidadesDeAlmacenamiento;
	}

	public void agregarUnidadDeAlmacenamiento(UnidadDeAlmacenamiento unidadDeAlmacenamiento) {
		
		String idUnidad = unidadDeAlmacenamiento.getIdUnidad();
		this.unidadesDeAlmacenamiento.put(idUnidad, unidadDeAlmacenamiento);
	}
	
	public void eliminarUnidadDeAlmacenamiento(UnidadDeAlmacenamiento unidadDeAlmacenamiento) {
		
		String idUnidad = unidadDeAlmacenamiento.getIdUnidad();
		this.unidadesDeAlmacenamiento.remove(idUnidad, unidadDeAlmacenamiento);
	}
	
	
	
	
}
