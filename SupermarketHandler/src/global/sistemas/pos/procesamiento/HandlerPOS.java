package global.sistemas.pos.procesamiento;


public class HandlerPOS {

	
	private LoaderDatabase databaseLoader = new LoaderDatabase();
	private  SupermarketModeler supermarketModeler = new SupermarketModeler();
	
	public void commandLoadCSVDatabase() {
		
		databaseLoader.loadDatabaseCSV(supermarketModeler);

	}
	
	public boolean cajeroRegistrado (String id) {
		return supermarketModeler.getSupermercado().getCajeros().containsKey(id);
	}
	
	public void registrarCajero(String id, String nombre) {
		supermarketModeler.modelarCajero(nombre, id);
	}

}
