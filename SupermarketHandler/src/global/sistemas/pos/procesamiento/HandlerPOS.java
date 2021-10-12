package global.sistemas.pos.procesamiento;

public class HandlerPOS {

	
	private LoaderDatabase databaseLoader = new LoaderDatabase();
	private  SupermarketModeler supermarketModeler = new SupermarketModeler();
	private SaverDatabase databaseSaver = new SaverDatabase();
	
	public void commandLoadCSVDatabase() {
		
		databaseLoader.loadDatabaseCSV(supermarketModeler);

	}
	
	
	
	public boolean cajeroRegistrado (String id) {
		return supermarketModeler.getSupermercado().getCajeros().containsKey(id);
	}
	
	public void registrarCajero( String nombre,String id) {
		supermarketModeler.modelarCajero(nombre, id);
	}
	
	public void registrarCliente(String nombre, int edad, char sexo, String cedula, String estadoCivil, String situacionLaboral) {
		supermarketModeler.modelarCliente(nombre, edad, sexo, cedula, estadoCivil, situacionLaboral);
	}
	
	public void registrarCompra(String cajero, String cc) {
		supermarketModeler.modelarCompra(cajero, cc);
		
	}
	
	public String agregarProducto(String producto) {
		return supermarketModeler.getSupermercado().getCompraActual().agregarProductoCompra(supermarketModeler.getSupermercado().getProducto(producto));
		
	}
	
	public String facturarCompra() {
		return supermarketModeler.getSupermercado().cerrarCompraActual();
	}
	
	public void commandSaveCSVDatabase() {
		databaseSaver.saveDatabaseCSV(supermarketModeler);
	}

}
