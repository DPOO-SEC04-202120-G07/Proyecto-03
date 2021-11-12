package global.modelo;

import java.util.ArrayList;
import java.util.Iterator;

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
		if (cliente==null) {
			nombre_cliente="NO REGISTRADO";
		}else {
			nombre_cliente=cliente.getNombre();
		}
		resumenPedido="RECIBO DE COMPRA -- Cajero: "+cajero.getNombre()+" -- Cliente: "+nombre_cliente+"\n";
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public String agregarProductoCompra(Producto producto, int numero) {
		ArrayList<Lote> lotes=producto.getLotesDeOrigen();
		Iterator<Lote> iteradorLote=lotes.iterator();
		int precio=0;
		int puntos=0;
		int numOrg=numero;
		Lote loteActual=null;
		
		while(iteradorLote.hasNext() && numero >0) {
			loteActual=iteradorLote.next();
			while(loteActual.getNumeroProductosRestantes()>0 && numero >0) {
				precio+=producto.getPrecio();
				puntos+=producto.getPrecio()/1000;
				loteActual.removerProductos(1);
				numero--;
				
				
			}
		}
		
		if (numero==0) {
			loteActual.removerProductos(numOrg);
			precioTotal+=precio;
			puntosCompra+=puntos;
			resumenPedido+=producto.getNombre()+" ("+producto.getMarca()+") -- Precio: "+producto.getPrecio()+"  Cantidad: "+numOrg+ "\n";
			return "Producto(s) agregado satisfactoriamente.";
		}
		
		return "No se ha podido agregar el numero de productos solicitados";
		
		
	}
	
	public String cerrarCompra() {
		return resumenPedido+="Precio Total: "+precioTotal+" -- Puntos totales obtenidos: "+puntosCompra;
	}
	
}
