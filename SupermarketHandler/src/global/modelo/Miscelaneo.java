package global.modelo;

public class Miscelaneo extends Producto{
	
	private int unidades;
	private double precioPorUnidad;

	public Miscelaneo(Lote loteDeOrigen, String nombre, String marca, Codigo codigoProducto, int unidades, double precioPorUnidad) {
		super(loteDeOrigen, nombre, marca, codigoProducto);
		this.unidades=unidades;
		this.precioPorUnidad=precioPorUnidad;
	}

	@Override
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
