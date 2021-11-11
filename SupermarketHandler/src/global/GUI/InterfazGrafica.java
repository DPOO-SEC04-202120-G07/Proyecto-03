package global.GUI;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import global.sistemas.inventario.procesamiento.HandledException;
import global.sistemas.inventario.procesamiento.HandlerSI;
import global.sistemas.pos.procesamiento.HandlerPOS;

public class InterfazGrafica extends JFrame {

	// Atributos globales
	private static final long serialVersionUID = 1L;

	private FrameInicio frameInicio = null;
	private FrameSI frameSI = null;
	private FramePOS framePOS = null;
	private LogInInventario dialogLogInSI = null;
	private LogInPOS dialogLogInPOS = null;

	private HandlerSI handlerSi = null;
	private HandlerPOS handlerPos=null;

	// Constructor
	public InterfazGrafica() {

		this.handlerSi = new HandlerSI();
		this.handlerPos = new HandlerPOS();
		HandlerSI.interfazGrafica = this;
		HandlerPOS.interfazGrafica = this;
		
		// Configuraciones funcionales de la ventana
		setTitle("Supermercados Nerv");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1151, 648);
		setLocationRelativeTo(null);
		setResizable(false);
		this.frameInicio = new FrameInicio(this);

		// Listener de cierre - Guarda la información dependiendo del sistema
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {

				if (frameSI != null) {
					handlerSi.commandSaveCSVDatabase();
					JOptionPane.showMessageDialog(frameSI,
							"La información del Sistema Inventario ha sido actualizada exitosamente.", "Advertencia",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
				if (framePOS != null) {
					handlerPos.commandSaveCSVDatabase();
					JOptionPane.showMessageDialog(framePOS,
							"La información del Sistema POS ha sido actualizada exitosamente.", "Advertencia",
							JOptionPane.INFORMATION_MESSAGE);
				}
			

			}
		});

	}

	// Método main - Instancia un objeto de tipo InterfazGráfica y ejecuta dicha
	// interfaz
	public static void main(String[] args) {
		// Se instancia la interfaz gráfica
		InterfazGrafica interfazGrafica = new InterfazGrafica();
		try {
			interfazGrafica.handlerSi.commandLoadCSVDatabase();
		} catch (FileNotFoundException | HandledException e) {
			e.printStackTrace();
		}
		
		try {
			interfazGrafica.handlerPos.commandLoadCSVDatabase();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Se parte del JInternalFrame de bienvenida
		interfazGrafica.abrirFrameInicio();

		// Se hace visible la totalidad de la interfaz
		interfazGrafica.setVisible(true);
	}

	// MÉTODOS FRAME DE INICIO
	public void abrirFrameInicio() {
		this.frameInicio.setVisible(true);
		this.add(frameInicio);
	}

	public void cerrarFrameInicio() throws PropertyVetoException {
		frameInicio.setClosed(true);
	}

	// MÉTODOS LOG-IN DE LOS SISTEMAS
	public void abrirLogInInventario() {
		this.dialogLogInSI = new LogInInventario(this);
		this.dialogLogInSI.abrirLogInInventario();
	}

	public void abrirLogInPOS() {
		this.dialogLogInPOS = new LogInPOS(this);
		this.dialogLogInPOS.abrirLogInPOS();
	}

	// MÉTODOS FRAMES PRINCIPALES DE LOS SISTEMAS
	public void abrirFrameSI() {

		this.frameSI = new FrameSI(this);
		this.frameSI.setVisible(true);
		this.add(frameSI);

	}

	public void abrirFramePOS() {
		this.framePOS = new FramePOS(this);
		this.framePOS.setVisible(true);
		this.add(framePOS);
	}

	
	//GETTERS Y SETTERS
	public HandlerSI getHandlerSi() {
		return handlerSi;
	}
	
	public HandlerPOS getHandlerPos() {
		return handlerPos;
	}

	public void setHandlerSi(HandlerSI handlerSi) {
		this.handlerSi = handlerSi;
	}
	
	
	public FrameSI getFrameSI() {
		return this.frameSI;
	}
	

}
