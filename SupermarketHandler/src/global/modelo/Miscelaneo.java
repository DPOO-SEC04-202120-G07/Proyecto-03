package global.modelo;

public class Miscelaneo extends Producto{
	
	private int unidades;
	private double precioPorUnidad;

	public Miscelaneo(Lote loteDeOrigen, String nombre, String marca, Codigo codigoProducto) {
		super(loteDeOrigen, nombre, marca, codigoProducto);
		// TODO Auto-generated constructor stub
	}

	public int getUnidades() {
		return this.unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public double getPrecioPorUnidad() {
		return this.precioPorUnidad;
	}

	public void setPrecioPorUnidad() {
		this.precioPorUnidad = super.getPrecio() / this.unidades;
	}

}
