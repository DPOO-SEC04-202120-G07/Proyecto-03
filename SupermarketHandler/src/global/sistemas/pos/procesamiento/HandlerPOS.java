package global.sistemas.pos.procesamiento;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import global.GUI.InterfazGrafica;
import global.modelo.Cliente;
import global.modelo.Compra;


public class HandlerPOS {

	private LoaderDatabase databaseLoader = new LoaderDatabase();
	private SupermarketModeler supermarketModeler = new SupermarketModeler();
	private SaverDatabase databaseSaver = new SaverDatabase();

	public static InterfazGrafica interfazGrafica;

	public void commandLoadCSVDatabase() throws FileNotFoundException {

		databaseLoader.loadDatabaseCSV(supermarketModeler);

	}

	public boolean hayCompraActual() {
		return supermarketModeler.getSupermercado().getCompraActual() != null;
	}
	
	
	public Compra getCompraActual() {
		return supermarketModeler.getSupermercado().getCompraActual();
	}

	public boolean cajeroRegistrado(String id) {
		return supermarketModeler.getSupermercado().getCajeros().containsKey(id);
	}

	public void registrarCajero(String nombre, String id) {
		supermarketModeler.modelarCajero(nombre, id);
	}

	public void registrarCliente(String nombre, int edad, char sexo, String cedula, String estadoCivil,
			String situacionLaboral) throws HandledException {

		if (supermarketModeler.getSupermercado() == null) {
			throw new HandledException("null-supermercado");
		}

		supermarketModeler.modelarCliente(nombre, edad, sexo, cedula, estadoCivil, situacionLaboral,"None", 0);
	}

	public void registrarCompra(String cajero, String cc) throws HandledException {

		if (supermarketModeler.getSupermercado() == null) {
			throw new HandledException("null-supermercado");
		}

		else if (supermarketModeler.getSupermercado().getClientes().get(cc) == null && cc != "None") {
			throw new HandledException("null-cliente");
		}

		supermarketModeler.modelarCompra(cajero, cc);

	}

	public String clienteActual() {
		
		String nombre_cliente = "";
		
		try {
			nombre_cliente = supermarketModeler.getSupermercado().getCompraActual().getCliente().getNombre();
		}
		
		catch(NullPointerException e) {
			nombre_cliente = "N/A";
		}
		
		return nombre_cliente;
	}
	
	
	public int getPuntosCliente() {
		
		if (clienteActual().equals("N/A")) {
			return 0;
		}
		return supermarketModeler.getSupermercado().getCompraActual().getCliente().getPuntos();
	}
		
	
	public void removerPuntosCliente(int puntos) {
		
		if (!clienteActual().equals("N/A")) {
			supermarketModeler.getSupermercado().getCompraActual().getCliente().eliminarPuntos(puntos);
		}

	}
	
		
	

	public String agregarProducto(String producto, int numero) throws HandledException {
		if (supermarketModeler.getSupermercado().getProducto(producto) == null) {
			throw new HandledException("null-producto");
		}

		return supermarketModeler.getSupermercado().getCompraActual()
				.agregarProductoCompra(supermarketModeler.getSupermercado().getProducto(producto), numero);
	}

	public String facturarCompra(int puntos_a_redimir, int descuento_puntos) throws HandledException {

		if (supermarketModeler.getSupermercado() == null) {
			throw new HandledException("null-supermercado");
		} else if (supermarketModeler.getSupermercado().getCompraActual() == null) {
			throw new HandledException("null-compra");
		}

		return supermarketModeler.getSupermercado().cerrarCompraActual(puntos_a_redimir, descuento_puntos);

	}

	public int[] getFechasCliente(String cedula) {
		Cliente clienteActual = supermarketModeler.getSupermercado().getClientes().get(cedula);
		int[] arrayFrecuenciaCompras = new int[365] ;
		
		if (clienteActual != null) {
			Iterator<String> iterFechaCompras = clienteActual.getFechaCompras().iterator();

			while (iterFechaCompras.hasNext()) {
				String fechaCompraActual = iterFechaCompras.next();
				
			    try {
					Date formatFechaCompra=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(fechaCompraActual);
					  Calendar cal = Calendar.getInstance();
					  cal.setTime(formatFechaCompra);
					  
					  int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
					  arrayFrecuenciaCompras[dayOfYear] += 1;
					 
					  
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		 return arrayFrecuenciaCompras;
	}

	public void commandSaveCSVDatabase() {
		databaseSaver.saveDatabaseCSV(supermarketModeler);
	}

}
