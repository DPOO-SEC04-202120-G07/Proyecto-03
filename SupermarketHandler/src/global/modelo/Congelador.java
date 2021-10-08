package global.modelo;

public class Congelador extends UnidadDeAlmacenamiento{
	
	private double volumen;
	
	public Congelador(int idUnidad, int pasilloUnidad, int capacidad, int volumen) {
		super(idUnidad, pasilloUnidad, capacidad);
		this.volumen = volumen;
	}

	public double getVolumen() {
		return this.volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

}
