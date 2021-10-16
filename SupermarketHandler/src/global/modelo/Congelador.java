package global.modelo;

public class Congelador extends UnidadDeAlmacenamiento{
	
	private double volumen;
	
	public Congelador(String idUnidad, int pasilloUnidad, int capacidad, double volumen) {
		super(idUnidad, pasilloUnidad, capacidad);
		super.setTipo("congelador");
		this.volumen = volumen;
	}

	public double getVolumen() {
		return this.volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

}
