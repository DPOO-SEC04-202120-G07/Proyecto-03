package global.GUI;


import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import global.sistemas.inventario.procesamiento.HandlerSI;


public class InterfazGrafica extends JFrame{
	
	
	
	//Atributos globales
	private static final long serialVersionUID = 1L;
	
	private FrameInicio frameInicio = null;
	private FrameSI frameSI = null;
	//private FramePOS framePOS = null;
	private LogInInventario dialogLogInSI = null;
	private LogInPOS dialogLogInPOS = null;
	
	
	
	
	private HandlerSI handlerSi = new HandlerSI();
	
	
	//Constructor
	public InterfazGrafica() {
		
		//Configuraciones funcionales de la ventana
		setTitle("Supermercados Nerv");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1151, 648);
		setLocationRelativeTo(null);
		setResizable(false);
		this.frameInicio = new FrameInicio(this);
		
		
		//Listener de cierre - Guarda la información dependiendo del sistema
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	
		    	if (frameSI != null) {
		    	handlerSi.commandSaveCSVDatabase();
		    	JOptionPane.showMessageDialog(frameSI,"La información del Sistema Inventario ha sido actualizada exitosamente.", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
		    	}
		    	
		    	
		    }
		});
		
		
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
		this.dialogLogInSI = new LogInInventario(this);	
		this.dialogLogInSI.abrirLogInInventario();
	}

	public void abrirLogInPOS() {
		this.dialogLogInPOS = new LogInPOS(this);
		this.dialogLogInPOS.abrirLogInPOS();
	}


	
	//MÉTODOS FRAMES PRINCIPALES DE LOS SISTEMAS
	public void abrirFrameSI() {
		
		this.frameSI = new FrameSI(this);
		this.frameSI.setVisible(true);
		this.add(frameSI);
		
	}
	public void abrirFramePOS() {
		
	}



	public HandlerSI getHandlerSi() {
		return handlerSi;
	}



	public void setHandlerSi(HandlerSI handlerSi) {
		this.handlerSi = handlerSi;
	}
	
	




	


	

	

	
	
	
	

}



