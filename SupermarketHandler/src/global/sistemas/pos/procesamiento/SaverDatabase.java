package global.sistemas.pos.procesamiento;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;


import java.util.stream.Collectors;


import global.modelo.*;

public class SaverDatabase {
	
	Supermercado supermercadoVolatil;

	public void saveDatabaseCSV(SupermarketModeler supermarketModeler) {
	
		this.supermercadoVolatil = supermarketModeler.getSupermercado();
	
		//Guardar lotes/productos cargados en inventario.csv
		savePosCSV();
		
		
	}

	private void savePosCSV() {
		
		ArrayList<String[]> lineasClientes = new ArrayList<String[]>();
		lineasClientes.add(new String[]{"nombre", "edad", "sexo", "cedula", "estadoCivil", "situacionLaboral"});
		
		ArrayList<String[]> lineasCajeros= new ArrayList<String[]>();
		lineasCajeros.add(new String[]{"nombre", "codigoC"});
		
	    File csvClientesFile = new File("./data/clientes.csv");
	    File csvCajerosFile = new File("./data/cajeros.csv");
	    
	    
		
	    Iterator<Cliente> iterClientes = supermercadoVolatil.getClientes().values().iterator();
	    Iterator<Cajero> iterCajeros = supermercadoVolatil.getCajeros().values().iterator();
	    
	    while(iterClientes.hasNext()) {
	    	Cliente clienteActual=iterClientes.next();
	    	
	    	String nombre=clienteActual.getNombre();
	    	String edad=""+clienteActual.getEdad();
	    	String sexo=""+clienteActual.getSexo();
	    	String cedula=clienteActual.getCedula();
	    	String estadoCivil=clienteActual.getEstadoCivil();
	    	String situacionLaboral=clienteActual.getSituacionLaboral();
	    	
	    	String[] lineaCliente= {nombre,edad,sexo,cedula,estadoCivil,situacionLaboral};
	    	lineasClientes.add(lineaCliente);
	    }
	    
	    while(iterCajeros.hasNext()) {
	    	Cajero cajeroActual=iterCajeros.next();
	    	
	    	String nombre=cajeroActual.getNombre();
	    	String codigoC=cajeroActual.getCodigoCajero();
	    	
	    	String[] lineaCajero= {nombre,codigoC};
	    	lineasCajeros.add(lineaCajero);
	    }
	    		
		//Escribir sobre clientes.csv
	    try (PrintWriter pw = new PrintWriter(csvClientesFile)) {
	    	lineasClientes.stream()
	          .map(this::convertToCSV)
	          .forEach(pw::println);
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	  //Escribir sobre cajeros.csv
	    try (PrintWriter pw = new PrintWriter(csvCajerosFile)) {
	    	lineasCajeros.stream()
	          .map(this::convertToCSV)
	          .forEach(pw::println);
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}
		
		
		//Métodos para cargar al CSV tomados de https://www.baeldung.com/java-csv
		
		public String convertToCSV(String[] data) {
		    return Stream.of(data)
		      .collect(Collectors.joining(","));
		}
		
	}


