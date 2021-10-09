package global.modelo;

import java.util.ArrayList;

public class Cliente {
	
	private String nombre;
	private int edad;
	private char sexo;
	private String estadoCivil;
	private String situacionLaboral;
	
	private String cedula;
	private int puntos;
	private ArrayList<Compra> compras;
	
	private Cajero cajeroActual;
	
	
	public Cliente(String nombre, int edad, char sexo, String cedula, String estadoCivil, String situacionLaboral) {
		
		this.setNombre(nombre);
		this.setEdad(edad);
		this.setSexo(sexo);
		this.setCedula(cedula);
		this.setEstadoCivil(estadoCivil);
		this.setSituacionLaboral(situacionLaboral);
		
		this.compras= new ArrayList<Compra>();
		this.puntos = 0;
		this.setCajeroActual(null);
		
	}
	
	//Puntos (Importante)
	public int getPuntos() {
		return this.puntos;
	}

	public void agregarPuntos(int puntos_nuevos) {
		this.puntos += puntos_nuevos;
	}
	
	public void eliminarPuntos(int puntos_a_eliminar) {
		this.puntos -= puntos_a_eliminar;
	}
	
	//Compras (Importante)
	
	//Agrega una nueva compra al final de la lista
	public void agregarCompra(Compra compra) {
		this.compras.add(compra);
	}
	
	//En dado caso que alguna compra haya presentado problemas, esta se puede revertir y eliminar
	public void eliminarCompra(Compra compra) {
		this.compras.remove(compra);
	}
	
	//Retorna la lista con todas las compras del cliente
	public ArrayList<Compra> getCompras(){
		return this.compras;
	}
	
	
	
	//Nombre
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//Edad
	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	//Estado Civil
	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	
	//Sexo
	public char getSexo() {
		return this.sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	//Situacion Laboral
	public String getSituacionLaboral() {
		return this.situacionLaboral;
	}

	public void setSituacionLaboral(String situacionLaboral) {
		this.situacionLaboral = situacionLaboral;
	}

	
	//Cedula (En caso que haya un error y se desee corregir)
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	
	//Cajero
	public Cajero getCajeroActual() {
		return this.cajeroActual;
	}

	public void setCajeroActual(Cajero cajeroActual) {
		this.cajeroActual = cajeroActual;
	}

	

	
	

}
