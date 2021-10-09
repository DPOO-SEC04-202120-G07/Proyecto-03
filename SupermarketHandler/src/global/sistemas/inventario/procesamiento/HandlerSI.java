package global.sistemas.inventario.procesamiento;


public class HandlerSI {
	
	private LoaderDatabase databaseLoader = new LoaderDatabase();
	private SupermarketModeler supermarketModeler = new SupermarketModeler();
	
	public void commandLoadCSVDatabase() {
		
		databaseLoader.loadDatabaseCSV(supermarketModeler);
		
	}
	
	public boolean encargadoRegistrado (String id) {
		return supermarketModeler.getSupermercado().getEncargados().containsKey(id);
	}
	
	public void registrarEncargado(String id, String nombre) {
		supermarketModeler.modelarEncargado(nombre, id);
	}
}
