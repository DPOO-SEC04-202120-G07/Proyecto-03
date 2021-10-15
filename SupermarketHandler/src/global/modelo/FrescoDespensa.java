package global.modelo;

import java.util.ArrayList;

public class FrescoDespensa extends UnidadDeAlmacenamiento{
	
	private ArrayList<String> condicionesConservacion = new ArrayList<String>();

	public FrescoDespensa(String idUnidad, int pasilloUnidad, int capacidad, ArrayList<String> condicionesConservacion) {
		super(idUnidad, pasilloUnidad, capacidad);
		
		this.condicionesConservacion = condicionesConservacion;
	}

	public void agregarCondicionConservacion(String condicionConservacion) {
		
		condicionesConservacion.add(condicionConservacion);
	
	}
	
	public void eliminarCondicionConservacion(String condicionConservacion) {
		
		condicionesConservacion.add(condicionConservacion);
		condicionesConservacion.remove(condicionConservacion);
	
	}
	
	
	public ArrayList<String> getCondicionesConservacion(){
		return this.condicionesConservacion;
	}
	


	
}





