package global.modelo;

public class Producto {

	private String nombre;
	private String marca;
	private Codigo codigoProducto;
	
	private double precio;
	private double precioPuntos;
	
	private Categoria categoria;
	private boolean refrigeracion;
	private boolean congelacion;
	
	private Lote loteDeOrigen;
	
	//Método constructor con la información más básica del producto
	public Producto(Lote loteDeOrigen, String nombre, String marca, Codigo codigoProducto) {
		this.loteDeOrigen = loteDeOrigen;
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
	
	public Lote getLoteDeOrigen() {
		return this.loteDeOrigen;
	}
	
	
	
	
	
}
