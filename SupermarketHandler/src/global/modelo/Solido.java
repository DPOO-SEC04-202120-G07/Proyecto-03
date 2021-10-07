package global.modelo;

public class Solido extends Producto{

	private double peso;
	private double precioPorgr;
	
	public Solido(Lote loteDeOrigen, String nombre, String marca, String codigoProducto) {
		super(loteDeOrigen, nombre, marca, codigoProducto);
		// TODO Auto-generated constructor stub
	}

	public double getPeso() {
		return this.peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getPrecioPorgr() {
		return this.precioPorgr;
	}

	public void setPrecioPorgr() {
		this.precioPorgr = super.getPrecio() / this.peso;
	}

}
