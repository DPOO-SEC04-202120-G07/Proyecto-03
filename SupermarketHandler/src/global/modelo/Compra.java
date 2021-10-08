package global.modelo;

public class Compra {
	int precioTotal;
	int puntosCompra;
	String resumenPedido;
	String cajero;
	
	public Compra(String pCajero) {
		precioTotal=0;
		puntosCompra=0;
		cajero=pCajero;
		resumenPedido="RECIBO DE COMPRA -- Cajero: "+cajero+"\n";
	}
	
	public void agregarProductoCompra(Producto producto) {
		precioTotal+=producto.getPrecio();
		puntosCompra+=producto.getPrecio()/2;
		resumenPedido+=producto.getNombre()+" ("+producto.getMarca()+") -- Precio: "+producto.getPrecio()+"\n";
	}
	
	public void cerrarCompra() {
		resumenPedido+="Precio Total: "+precioTotal+" -- Puntos totales obtenidos: "+puntosCompra;
	}
}
