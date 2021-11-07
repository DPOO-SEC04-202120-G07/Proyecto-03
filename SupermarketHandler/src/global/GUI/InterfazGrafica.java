package global.GUI;


import java.beans.PropertyVetoException;

import javax.swing.JFrame;


public class InterfazGrafica extends JFrame{
	
	
	
	//Atributos globales
	private static final long serialVersionUID = 1L;
	private FrameInicio frameInicio = new FrameInicio(this);
	
	
	//Constructor
	public InterfazGrafica() {
		
		//Configuraciones funcionales de la ventana
		setTitle("Supermercados Nerv");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1151, 648);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	
	
	//Método main - Instancia un objeto de tipo InterfazGráfica y ejecuta dicha interfaz
	public static void main(String[] args)
	{
		//Se instancia la interfaz gráfica
		InterfazGrafica interfazGrafica = new InterfazGrafica();
		
		//Se parte del JInternalFrame de bienvenida
		interfazGrafica.abrirFrameInicio();
		
		//Se hace visible la totalidad de la interfaz
		interfazGrafica.setVisible(true);
	}
	
	
	//MÉTODOS FRAME DE INICIO
	public void abrirFrameInicio() {
		this.frameInicio.setVisible(true);
		this.add(frameInicio);
	}
	public void cerrarFrameInicio() throws PropertyVetoException {
		frameInicio.setClosed(true);
	}
	
	

	//MÉTODOS LOG-IN DE LOS SISTEMAS
	public void abrirLogInInventario() {
		new LogInInventario(this).abrirLogInInventario();	
	}
	public void abrirLogInPOS() {
		new LogInPOS(this).abrirLogInPOS();
	}


	
	//MÉTODOS FRAMES PRINCIPALES DE LOS SISTEMAS
	public void abrirFrameSI() {
		
		FrameSI frameSI = new FrameSI(this);
		frameSI.setVisible(true);
		this.add(frameSI);
		
	}
	public void abrirFramePOS() {
		
	}
	
	




	


	

	

	
	
	
	

}



