package global.modelo;

public class Compra {
	int precioTotal;
	int puntosCompra;
	String resumenPedido;
	Cliente cliente;
	Cajero cajero;
	
	public Compra(Cajero pCajero, Cliente cliente) {
		precioTotal=0;
		puntosCompra=0;
		cajero=pCajero;
		this.cliente=cliente;
		
		String nombre_cliente;
		if (cliente.equals(null)) {
			nombre_cliente="NO REGISTRADO";
		}else {
			nombre_cliente=cliente.getNombre();
		}
		resumenPedido="RECIBO DE COMPRA -- Cajero: "+cajero.getNombre()+" -- Cliente: "+nombre_cliente+"\n";
	}
	
	public void agregarProductoCompra(Producto producto) {
		precioTotal+=producto.getPrecio();
		puntosCompra+=producto.getPrecio()/1000;
		resumenPedido+=producto.getNombre()+" ("+producto.getMarca()+") -- Precio: "+producto.getPrecio()+"\n";
	}
	
	public String cerrarCompra() {
		return resumenPedido+="Precio Total: "+precioTotal+" -- Puntos totales obtenidos: "+puntosCompra;
	}
	
}
