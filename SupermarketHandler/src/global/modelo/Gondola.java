package global.modelo;

public class Gondola extends UnidadDeAlmacenamiento{
	
	private int numRepisas;
	
	public Gondola(String idUnidad, int pasilloUnidad, int capacidad, int numRepisas) {
		super(idUnidad, pasilloUnidad, capacidad);
		super.setTipo("gondola");
		this.numRepisas = numRepisas;
	}

	public int getNumRepisas() {
		return this.numRepisas;
	}

	public void setNumRepisas(int numRepisas) {
		this.numRepisas = numRepisas;
	}

}
