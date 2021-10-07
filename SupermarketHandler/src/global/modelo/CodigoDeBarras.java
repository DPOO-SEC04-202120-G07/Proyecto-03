package global.modelo;

public class CodigoDeBarras extends Codigo{
	
	private String codigoBarra;
	
	//Método constructor
	public CodigoDeBarras(String codigoBarra){
		this.codigoBarra = codigoBarra;
	}
	
	//Setter en caso de que el código de barras cambie
	public void setCodigo(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	
	//Getter para obtener el código de barras
	public String getCodigo() {
		return this.codigoBarra;
	}

}
