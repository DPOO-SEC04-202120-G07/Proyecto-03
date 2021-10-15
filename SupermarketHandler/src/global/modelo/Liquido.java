package global.modelo;

public class Liquido extends Producto{
	
	private double volumen;
	private double precioPormL;
	
	public Liquido(Lote loteDeOrigen, String nombre, String marca, Codigo codigoProducto, double volumen, double precioPormL) {
		super(loteDeOrigen, nombre, marca, codigoProducto);
		this.volumen=volumen;
		this.precioPormL=precioPormL;
	}

	@Override
	public double getVolumen() {
		return this.volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public double getPrecioPormL() {
		return this.precioPormL;
	}

	public void setPrecioPormL() {
		this.precioPormL = super.getPrecio() / this.volumen;
	}

}
