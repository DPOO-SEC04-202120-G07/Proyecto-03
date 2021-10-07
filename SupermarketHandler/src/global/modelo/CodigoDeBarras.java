package global.modelo;

public class CodigoDeBarras {
	
	private int codigoBarra;
	
	//Método constructor
	public CodigoDeBarras(int codigoBarra){
		this.codigoBarra = codigoBarra;
	}
	
	//Setter en caso de que el código de barras cambie
	public void setCodigoBarra(int codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	
	//Getter para obtener el código de barras
	public int getCodigoBarra() {
		return this.codigoBarra;
	}

}
