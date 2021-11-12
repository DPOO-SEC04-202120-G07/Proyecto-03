package global.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import global.modelo.Producto;
import global.sistemas.pos.procesamiento.HandledException;

public class CompraFramePOS extends JDialog{
	private static final long serialVersionUID = 5385568765630751405L;
	
	@SuppressWarnings("unused")
	private Font sourceSansPro;
	private BufferedImage image;
	
	@SuppressWarnings("unused")
	private InterfazGrafica owner;

	public CompraFramePOS(InterfazGrafica owner) {
		super(owner, true);
		this.owner = owner;
		sourceSansPro = new SourceSansFont(400, 12).getSourceSansFontFont();

		// Configuraciones funcionales de la ventana
		setTitle("Iniciar compra");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(688, 478);
		setLocationRelativeTo(null);
		setResizable(false);

		// Configuraciones estéticas de la ventana
		getContentPane().setBackground(new Color(118, 88, 152));

		// Establecer el tipo de layout de la interfaz
		setLayout(new GridBagLayout());


		// Componentes

		// Panel con imagen, nombre e id
		JPanel panelImagenProducto = new JPanel() {
			private static final long serialVersionUID = 7231326846636583268L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.setColor(new Color(89, 68, 115));

				g.fillRoundRect(45, 20, 586, 267, 50, 50);


				g.setFont(sourceSansPro.deriveFont(32f));
				g.setColor(Color.WHITE);


				repaint();

			}
		};
		panelImagenProducto.setPreferredSize(new Dimension(300, 440));
		panelImagenProducto.setOpaque(false);

		GridBagConstraints constraintsPanelImagen = new GridBagConstraints();
		constraintsPanelImagen.gridx = 0; // El área de texto empieza en la columna
		constraintsPanelImagen.gridy = 0; // El área de texto empieza en la fila
		constraintsPanelImagen.gridwidth = GridBagConstraints.REMAINDER; // El área de texto ocupa una columna.
		constraintsPanelImagen.gridheight = 6; // El área de texto ocupa una fila
		constraintsPanelImagen.anchor = GridBagConstraints.NORTHWEST;
		constraintsPanelImagen.fill=GridBagConstraints.BOTH;
		constraintsPanelImagen.weighty = 1;
		constraintsPanelImagen.weightx = 1;

		// Se añade el panel
		add(panelImagenProducto, constraintsPanelImagen);


		
		
		
		
		
		//Boton Lotes
		JButton botonIniciar = new RoundedButton(170,40,"Iniciar compra",sourceSansPro.deriveFont(18f));
		GridBagConstraints constraintsIniciar = new GridBagConstraints();
		constraintsIniciar.gridx = 2; // El área de texto empieza en la columna
		constraintsIniciar.gridy = 5; // El área de texto empieza en la fila
		constraintsIniciar.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsIniciar.gridheight = 1; // El área de texto ocupa una fila
		constraintsIniciar.anchor = GridBagConstraints.SOUTHEAST;
		constraintsIniciar.weighty = 1;
		constraintsIniciar.weightx = 1;
		constraintsIniciar.insets = new Insets(0,0,80,50);

		add(botonIniciar, constraintsIniciar);
		
		JLabel labelCliente = new JLabel("Cliente registrado");
		labelCliente.setFont(sourceSansPro.deriveFont(18f));
		labelCliente.setForeground(Color.WHITE);
		GridBagConstraints constraintsLabelCliente = new GridBagConstraints();
		constraintsLabelCliente.gridx = 1; // El área de texto empieza en la columna
		constraintsLabelCliente.gridy = 5; // El área de texto empieza en la fila
		constraintsLabelCliente.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsLabelCliente.gridheight = 1; // El área de texto ocupa una fila
		constraintsLabelCliente.anchor = GridBagConstraints.SOUTHWEST;
		constraintsLabelCliente.weighty = 1;
		constraintsLabelCliente.weightx = 1;
		constraintsLabelCliente.insets = new Insets(0,40,100,40);
		
		add(labelCliente, constraintsLabelCliente);
		
		JComboBox<String> cliente = new JComboBox<String>();
		cliente.addItem("Si");
		cliente.addItem("No");
		cliente.setBackground(new Color(75, 57, 97));
		cliente.setForeground(Color.WHITE);
		GridBagConstraints constraintsCliente = new GridBagConstraints();
		constraintsCliente.gridx = 1; // El área de texto empieza en la columna
		constraintsCliente.gridy = 5; // El área de texto empieza en la fila
		constraintsCliente.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsCliente.gridheight = 1; // El área de texto ocupa una fila
		constraintsCliente.anchor = GridBagConstraints.SOUTHWEST;
		constraintsCliente.weighty = 1;
		constraintsCliente.weightx = 1;
		constraintsCliente.insets = new Insets(0,190,100,40);
		
		add(cliente, constraintsCliente);
		
		JLabel labelCedula = new JLabel("Cedula");
		labelCedula.setFont(sourceSansPro.deriveFont(18f));
		labelCedula.setForeground(Color.WHITE);
		GridBagConstraints constraintsLabelCedula = new GridBagConstraints();
		constraintsLabelCedula.gridx = 1; // El área de texto empieza en la columna
		constraintsLabelCedula.gridy = 5; // El área de texto empieza en la fila
		constraintsLabelCedula.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsLabelCedula.gridheight = 1; // El área de texto ocupa una fila
		constraintsLabelCedula.anchor = GridBagConstraints.SOUTHWEST;
		constraintsLabelCedula.weighty = 1;
		constraintsLabelCedula.weightx = 1;
		constraintsLabelCedula.insets = new Insets(0,124,65,40);
		
		add(labelCedula, constraintsLabelCedula);
		
		
		JTextField cedulaField = new JTextField(15);
		cedulaField.setBackground(new Color(75, 57, 97));
		cedulaField.setForeground(Color.WHITE);
		cedulaField.setEditable(true);
		GridBagConstraints constrainsCedulaField = new GridBagConstraints();
		constrainsCedulaField.gridx = 1; // El área de texto empieza en la columna
		constrainsCedulaField.gridy = 5; // El área de texto empieza en la fila
		constrainsCedulaField.gridwidth = 1; // El área de texto ocupa una columna.
		constrainsCedulaField.gridheight = 1; // El área de texto ocupa una fila
		constrainsCedulaField.anchor = GridBagConstraints.SOUTHWEST;
		constrainsCedulaField.weighty = 1;
		constrainsCedulaField.weightx = 1;
		constrainsCedulaField.insets = new Insets(0,187,65,0);
		
		add(cedulaField, constrainsCedulaField);
		
		//Listeners
		
		cliente.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
		       String selected = (String) cliente.getSelectedItem();
		       if (selected.equals("No")) {
		    	   cedulaField.setText("");
		    	   cedulaField.setEditable(false);
		       }else{
		    	   cedulaField.setEditable(true);
		       }
		    }
		});
		
		botonIniciar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				String selected = (String) cliente.getSelectedItem();
				if (selected.equals("No")) {
					try {
						owner.getHandlerPos().registrarCompra(owner.getCajero(), "None");
						JOptionPane.showMessageDialog(owner, "Se comenzo una compra de forma satisfactoria.", "Compra",
								JOptionPane.PLAIN_MESSAGE);
						owner.getFramePOS().actualizarNumClientes("N/A");
						cerrarVentana();
					} catch (HandledException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(owner, "Ingrese un valor valido. Intente de nuevo.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}else {
					try {
						owner.getHandlerPos().registrarCompra(owner.getCajero(), cedulaField.getText());
						JOptionPane.showMessageDialog(owner, "Se comenzo una compra de forma satisfactoria.", "Compra",
								JOptionPane.PLAIN_MESSAGE);
						owner.getFramePOS().actualizarNumClientes(cedulaField.getText());
						cerrarVentana();
					} catch (HandledException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(owner, "Ingrese un valor valido. Intente de nuevo.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				
				
			}
		});
		
		
		
		

		setVisible(true);
	}
	
	public void cerrarVentana() {
		setVisible(false);
	}
}
