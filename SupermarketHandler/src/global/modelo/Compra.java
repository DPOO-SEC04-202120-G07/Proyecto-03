package global.modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Compra {
	int precioTotal;
	int puntosCompra;
	String resumenPedido;
	Cliente cliente;
	Cajero cajero;
	String fecha;

	public Compra(Cajero pCajero, Cliente cliente) {
		precioTotal=0;
		puntosCompra=0;
		cajero=pCajero;
		this.cliente=cliente;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		fecha=dateFormat.format(date);
		
		String nombre_cliente;
		if (cliente==null) {
			nombre_cliente="NO REGISTRADO";
		}else {
			nombre_cliente=cliente.getNombre();
		}
		resumenPedido="RECIBO DE COMPRA -- Cajero: "+cajero.getNombre()+" -- Cliente: "+nombre_cliente+" -- Fecha: "+fecha+"\n\n";
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public String agregarProductoCompra(Producto producto, int numero, double descuento, int multiPuntos, String addMssg) {
		ArrayList<Lote> lotes=producto.getLotesDeOrigen();
		Iterator<Lote> iteradorLote=lotes.iterator();
		int precio=0;
		int puntos=0;
		int numOrg=numero;
		Lote loteActual=null;
		
		while(iteradorLote.hasNext() && numero >0) {
			loteActual=iteradorLote.next();
			while(loteActual.getNumeroProductosRestantes()>0 && numero >0) {
				precio+=producto.getPrecio()-(producto.getPrecio()*descuento);
				puntos+=(producto.getPrecio()/1000)*multiPuntos;
				loteActual.removerProductos(1);
				numero--;
				
				
			}
		}
		
		if (numero==0) {
			loteActual.removerProductos(numOrg);
			precioTotal+=precio;
			puntosCompra+=puntos;
			resumenPedido+=producto.getNombre()+" ("+producto.getMarca()+") -- Precio: "+producto.getPrecio()+"  Cantidad: "+numOrg+ "\n";
			resumenPedido+=addMssg+"\n\n";
			return "Producto(s) agregado satisfactoriamente.";
		}
		
		return "No se ha podido agregar el numero de productos solicitados";
		
		
	}
	
	public void descontarAlPrecio(int descuento) {
		this.precioTotal -= descuento;
	}
	
	public String cerrarCompra(int puntos_a_redimir, int descuento_puntos) {
		
		int puntos_acumulados_antes = 0;
		int puntos_redimidos = 0;
		int puntos_obtenidos_compra = 0;
		int puntos_despues_compra = 0;

		
		if(cliente != null) {
		cliente.agregarCompra(this);
		
		
		puntos_acumulados_antes = cliente.getPuntos();
		puntos_redimidos = puntos_a_redimir;
		puntos_obtenidos_compra = this.puntosCompra;
		
		cliente.eliminarPuntos(puntos_redimidos);
		cliente.agregarPuntos(puntos_obtenidos_compra);
		
		puntos_despues_compra = cliente.getPuntos();
		
		}
		return resumenPedido+="Precio Total: "+precioTotal+" -- Puntos acumulados antes de la compra:" + puntos_acumulados_antes
							+ "-- Puntos redimidos en la compra: "+puntos_redimidos + " -- Puntos acumulados por la compra: "+puntos_obtenidos_compra
							+ " -- Puntos acumulados después de la compra: "+puntos_despues_compra;
				
	}	
	
	public String getFecha(){
		return this.fecha;
	}
	
}
