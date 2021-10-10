package global.sistemas.inventario.procesamiento;
import java.util.Iterator;

import global.modelo.*;

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
	
	public void cargarLote(String idDeLote) {
		databaseLoader.loadNuevoLote(idDeLote);
	}

	public double[] consultarDesempenoProducto(String idProducto) {
		
		int productos_perdidos = 0;
		int productos_vendidos = 0;
		
		double gananciaProducto = 0;
		double perdidaProducto = 0;
		
		Codigo codigoProducto = supermarketModeler.modelarCodigo(idProducto);
		Producto productoConsultado = supermarketModeler.getSupermercado().getBodega().getProductos().get(idProducto);
		
		Iterator<Lote> iterLotesConProducto = productoConsultado.getLotesDeOrigen().iterator();
		
		while(iterLotesConProducto.hasNext()) {
			
			Lote lote = iterLotesConProducto.next();
			
			if(lote.isVencido()) {
				productos_perdidos += lote.getNumeroProductosRestantes();
				productos_vendidos += lote.getNumeroProductosBase() - lote.getNumeroProductosRestantes();
				
				perdidaProducto += lote.getNumeroProductosRestantes() * lote.getPrecioCompraUnidad();
				gananciaProducto += ((lote.getNumeroProductosBase() - lote.getNumeroProductosRestantes()) * lote.getPrecioVentaUnidad()) - ((lote.getNumeroProductosBase() - lote.getNumeroProductosRestantes()) * lote.getPrecioCompraUnidad()) ;
				
			}
			
			else {
				productos_vendidos += lote.getNumeroProductosBase() - lote.getNumeroProductosRestantes();
				gananciaProducto += ((lote.getNumeroProductosBase() - lote.getNumeroProductosRestantes()) * lote.getPrecioVentaUnidad()) - ((lote.getNumeroProductosBase() - lote.getNumeroProductosRestantes()) * lote.getPrecioCompraUnidad()) ;
				
			}
		
			
		}
		
		double[] desempenoProducto = {productos_perdidos, productos_vendidos, gananciaProducto, perdidaProducto};
		
		return desempenoProducto;
	
			
		}

		
	
}
