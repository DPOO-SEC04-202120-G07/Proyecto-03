package global.modelo;

public class Refrigerador extends UnidadDeAlmacenamiento{

	private double volumen;
	public Refrigerador(String idUnidad, int pasilloUnidad, int capacidad, double volumen) {
		super(idUnidad, pasilloUnidad, capacidad);
		super.setTipo("refrigerador");
		this.volumen = volumen;
	}
	public double getVolumen() {
		return this.volumen;
	}
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

}
