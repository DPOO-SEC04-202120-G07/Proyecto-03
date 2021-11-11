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
	
	public String agregarProductoCompra(Producto producto) {
		ArrayList<Lote> lotes=producto.getLotesDeOrigen();
		Iterator<Lote> iteradorLote=lotes.iterator();
		
		Lote loteActual=null;
		while(iteradorLote.hasNext()) {
			loteActual=iteradorLote.next();
			if(loteActual.getNumeroProductosRestantes()>0) {
				precioTotal+=producto.getPrecio();
				puntosCompra+=producto.getPrecio()/1000;
				resumenPedido+=producto.getNombre()+" ("+producto.getMarca()+") -- Precio: "+producto.getPrecio()+"\n";
				loteActual.removerProductos(1);
				return "Producto agregado satisfactoriamente.";
			}
		}
		
		return "No hay productos de este tipo.";
		
	}
	
	public String cerrarCompra() {
		return resumenPedido+="Precio Total: "+precioTotal+" -- Puntos totales obtenidos: "+puntosCompra;
	}
	
}
