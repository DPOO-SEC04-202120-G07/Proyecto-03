package global.modelo;

public class FrescoDespensa extends UnidadDeAlmacenamiento{
	
	private String condicionesConservacion;

	public FrescoDespensa(String idUnidad, int pasilloUnidad, int capacidad, String condicionesConservacion) {
		super(idUnidad, pasilloUnidad, capacidad);
		super.setTipo("fresco");
		this.setCondicionesConservacion(condicionesConservacion);
	}

	public String getCondicionesConservacion() {
		return condicionesConservacion;
	}

	public void setCondicionesConservacion(String condicionesConservacion) {
		this.condicionesConservacion = condicionesConservacion;
	}


	
	



	
}





