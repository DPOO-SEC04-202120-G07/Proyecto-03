package global.sistemas.inventario.procesamiento;
import java.util.Date;
import java.util.Iterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		
		double[] desempenoProducto = {productos_perdidos, productos_vendidos, perdidaProducto, gananciaProducto};
		
		return desempenoProducto;
	
			
		}
	
	public int cantidadProductosInventario() {
		return supermarketModeler.getSupermercado().getBodega().getProductos().size();
	}

	public Object[] consultarInfoProducto(String idProductoInteres) {
		Producto productoConsultado = supermarketModeler.getSupermercado().getBodega().getProductos().get(idProductoInteres);
		
		String nombreProducto = productoConsultado.getNombre();
		String marcaProducto = productoConsultado.getMarca();
		String categoriaProducto = productoConsultado.getCategoria().getNombre();
		double precioProducto = productoConsultado.getPrecio();
		int cantidadDisponible = 0;
		
		Iterator<Lote> iterLotesConProducto = productoConsultado.getLotesDeOrigen().iterator();
		while(iterLotesConProducto.hasNext()) {
			
			Lote lote = iterLotesConProducto.next();
			
			if(!lote.isVencido()) {
				cantidadDisponible += lote.getNumeroProductosRestantes();
			}
		
		}
		
		Object[] infoProducto = {nombreProducto, marcaProducto, categoriaProducto, precioProducto, cantidadDisponible};
		return infoProducto;
	}

	public void eliminarProductosVencidos(String fechaActual) {
		
		Date fechaHoy=null;
		
		try {
			fechaHoy = new SimpleDateFormat("dd/MM/yyyy").parse(fechaActual);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		Iterator<Producto> iterProductos = supermarketModeler.getSupermercado().getBodega().getProductos().values().iterator();
		while(iterProductos.hasNext()) {
			Producto productoActual = iterProductos.next();
			
			Iterator<Lote> iterLotesConProducto = productoActual.getLotesDeOrigen().iterator();
			while(iterLotesConProducto.hasNext()) {
				Lote lote = iterLotesConProducto.next();
				Date fechaVencimiento = lote.getfechaVencimiento();
				
				if (fechaVencimiento.before(fechaHoy)){
					lote.setVencido(true);
				}
		}}
			
		}
		


		
	
}
