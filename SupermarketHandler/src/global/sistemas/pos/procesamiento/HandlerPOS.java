package global.sistemas.pos.procesamiento;


public class HandlerPOS {

	
	private LoaderDatabase databaseLoader = new LoaderDatabase();
	private  SupermarketModeler supermarketModeler = new SupermarketModeler();
	
	public void commandLoadCSVDatabase() {
		
		databaseLoader.loadDatabaseCSV(supermarketModeler);

	}
	

}
