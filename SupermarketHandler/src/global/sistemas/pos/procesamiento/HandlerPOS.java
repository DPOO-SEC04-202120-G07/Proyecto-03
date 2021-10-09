package global.sistemas.pos.procesamiento;

import global.sistemas.procesamientoGeneral.*;

public class HandlerPOS {

	
	private LoaderDatabase databaseLoader = new LoaderDatabase();
	private  SupermarketModeler supermarketModeler = new SupermarketModeler();
	
	public void commandLoadCSVDatabase() {
		
		databaseLoader.loadDatabaseCSV(supermarketModeler);

	}
	

}
