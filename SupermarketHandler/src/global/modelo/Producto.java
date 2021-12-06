package global.modelo;

import java.util.ArrayList;

public class Producto {

	private String nombre;
	private String marca;
	private Codigo codigoProducto;
	
	private double precio;
	private double precioPuntos;
	
	private Categoria categoria;
	private boolean refrigeracion;
	private boolean congelacion;
	private boolean fresco; 

	private String pathImagen = "None";
	
	private ArrayList<Lote> lotesDeOrigen = new ArrayList<Lote>();
	
	private ArrayList<String[]> fechasUnidadesDisponibles = new ArrayList<String[]>();
	
	
	//Método constructor con la información más básica del producto
	public Producto(Lote loteDeOrigen, String nombre, String marca, Codigo codigoProducto) {
		lotesDeOrigen.add(loteDeOrigen);
		this.nombre = nombre;
		this.marca = marca;
		this.codigoProducto = codigoProducto;
	}
	
	//Setters para toda la información adicional
	
	public void setPrecios(double precio) {
		this.precio = precio;
		this.precioPuntos = precio/1000;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public void setRefrigeracion(boolean refrigeracion) {
		this.refrigeracion = refrigeracion;
	}
	
	public void setCongelacion(boolean congelacion) {
		this.congelacion = congelacion;
	}
	
	
   //Getters para todos los atributos
	public String getNombre() {
		return this.nombre;
	}

	public String getMarca() {
		return this.marca;
	}

	public Codigo getCodigoProducto() {
		return this.codigoProducto;
	}

	public double getPrecio() {
		return this.precio;
	}

	public double getPrecioPuntos() {
		return this.precioPuntos;
	}
	
	public Categoria getCategoria() {
		return this.categoria;
	}
	
	public boolean getCongelacion() {
		return this.congelacion;
	}
	
	public boolean getRefrigeracion() {
		return this.refrigeracion;
	}
	
	public ArrayList<Lote> getLotesDeOrigen() {
		return this.lotesDeOrigen;
	}

	public boolean isFresco() {
		return fresco;
	}

	public void setFresco(boolean fresco) {
		this.fresco = fresco;
	}
	
	
	public int getUnidades() {
		return 0;
	}
	
	public double getPeso() {
		return 0;
	}
	
	public double getVolumen() {
		return 0;
	}

	public String getPathImagen() {
		return pathImagen;
	}

	public void setPathImagen(String pathImagen) {
		this.pathImagen = pathImagen;
	}
	
	
	public void addFechaUnidades(String[] nuevaFechaUnidades) {
		this.fechasUnidadesDisponibles.add(nuevaFechaUnidades);
	}
	
	public ArrayList<String[]> getFechasUnidades(){
		return this.fechasUnidadesDisponibles;
	}
	
	
}
