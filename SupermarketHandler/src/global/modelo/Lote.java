package global.modelo;
import java.util.Date;

public class Lote {

	private String identificadorLote;
	private Date fechaVencimiento;
	
	private int numeroProductosBase;
	private int numeroProductosRestantes;
	
	private double precioCompraUnidad;
	private double precioVentaUnidad;
	
	private Producto producto;
	
	
	//Método constructor
	public Lote(String identificadorLote, Date fechaVencimiento, int numeroProductosBase, int numeroProductosRestantes, double precioCompraUnidad, double precioVentaUnidad, Producto producto){
		this.fechaVencimiento = fechaVencimiento;
		this.numeroProductosBase = numeroProductosBase;
		this.numeroProductosRestantes = numeroProductosRestantes;
		this.precioCompraUnidad = precioCompraUnidad;
		this.precioVentaUnidad = precioVentaUnidad;
		this.producto = producto;
	}
	
	//No existen Setters puesto que una vez el lote ingresa al supermercado, su información ya no puede cambiar (excepto por los productos restantes)
	//Solo se modifican los productos restantes
	public boolean removerProductos(int productos_removidos) {
		
		if (this.numeroProductosBase - productos_removidos >= 0) {
			this.numeroProductosRestantes = this.numeroProductosBase - productos_removidos;
			return true;
		}
		else {
			return false;
		}		
	}
		
	public void vaciarLote() {
		this.numeroProductosRestantes = 0;
	}

	
	//Getters para obtener la información del lote
	public String getIdentificadorLote() {
		return this.identificadorLote;
	}
	
	public Date getfechaVencimiento() {
		return this.fechaVencimiento;
	}
	
	public int getNumeroProductosBase() {
		return this.getNumeroProductosBase();
	}
	
	public int numeroProductosRestantes() {
		return this.numeroProductosRestantes;
	}
	
	public double precioCompraUnidad() {
		return this.precioCompraUnidad;
	}
	
	public double precioVentaUnidad() {
		return this.precioVentaUnidad; 
	}
	
	public Producto getItems(){
		return this.producto;
	}
}
