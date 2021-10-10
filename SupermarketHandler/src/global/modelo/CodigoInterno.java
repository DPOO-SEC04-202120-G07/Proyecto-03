package global.modelo;

public class CodigoInterno extends Codigo{
	
	private String codigoInterno;
	
	//Método constructor
	public CodigoInterno(String codigoInterno){
		this.codigoInterno = "P-"+codigoInterno;
	}
	
	//Setter en caso de que el código de barras cambie
	public void setCodigo(String codigoInterno) {
		this.codigoInterno = "P-" + codigoInterno;
	}
	
	//Getter para obtener el código de barras
	public String getCodigo() {
		return this.codigoInterno;
	}

}
