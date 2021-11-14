package global.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import global.sistemas.pos.procesamiento.HandledException;

public class CompraFramePOS extends JDialog {
	private static final long serialVersionUID = 5385568765630751405L;

	private Font sourceSansPro;

	private String cedula_actual;
	private int[] arrayFrecuenciaCompras = new int[365];
	@SuppressWarnings("unused")
	private  JLabel numeroCompras;

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

		// Configuraciones estÃ©ticas de la ventana
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
				g.fillRoundRect(60, 35, 556, 237, 50, 50);

				Point pixelInicioCasilla = new Point(80, 100);
				int filas = 7;
				int columnas = 52;
				int filaHeight = 10;
				int columnaWidth = 10;
				g.setFont(sourceSansPro.deriveFont(9f));

				for (int i = 0; i < filas; i++) {

					for (int j = 0; j < columnas; j++) {

						g.setColor(Color.BLACK);
						if (j == 1) {
							g.drawString("Jan", pixelInicioCasilla.x, 90);
						}
						if (j == 6) {
							g.drawString("Feb", pixelInicioCasilla.x, 90);
						}
						if (j == 10) {
							g.drawString("Mar", pixelInicioCasilla.x, 90);
						}
						if (j == 14) {
							g.drawString("Apr", pixelInicioCasilla.x, 90);
						}
						if (j == 18) {
							g.drawString("May", pixelInicioCasilla.x, 90);
						}
						if (j == 23) {
							g.drawString("Jun", pixelInicioCasilla.x, 90);
						}
						if (j == 27) {
							g.drawString("Jul", pixelInicioCasilla.x, 90);
						}
						if (j == 31) {
							g.drawString("Aug", pixelInicioCasilla.x, 90);
						}
						if (j == 36) {
							g.drawString("Sep", pixelInicioCasilla.x, 90);
						}
						if (j == 40) {
							g.drawString("Oct", pixelInicioCasilla.x, 90);
						}
						if (j == 44) {
							g.drawString("Nov", pixelInicioCasilla.x, 90);
						}

						if (j == 48) {
							g.drawString("Dec", pixelInicioCasilla.x, 90);
						}
						
						
						//SE REVISA CUANTAS COMPRAS HAN HABIDO EN EL DÃ�A ACTUAL Y SE DETERMINA EL COLOR


						if (!(i < 5 && j == 0)) {
							g.setColor(new Color(238, 238, 238));
						
							int frequency = arrayFrecuenciaCompras[(7*j + i)-4];
							
							if(frequency > 0) {
								Color c = Color.getHSBColor((float)0.30524346, (float)0.39732143, (float)0.8784314-(float)(0.04*frequency));
								g.setColor(c);
							}
							
				
							g.fillRoundRect(pixelInicioCasilla.x, pixelInicioCasilla.y, columnaWidth, filaHeight, 10,
									10);

						}
						pixelInicioCasilla.setLocation(pixelInicioCasilla.x + columnaWidth, pixelInicioCasilla.y);
					}

					pixelInicioCasilla.setLocation(80, pixelInicioCasilla.y + filaHeight);

					g.setColor(Color.BLACK);
					if (i == 1) {
						g.drawString("Mon", 63, pixelInicioCasilla.y - 2);
					}

					if (i == 3) {
						g.drawString("Wed", 63, pixelInicioCasilla.y - 2);
					}

					if (i == 5) {
						g.drawString("Fri", 63, pixelInicioCasilla.y - 2);
					}

				}


			}
		};
		panelImagenProducto.setPreferredSize(new Dimension(300, 440));
		panelImagenProducto.setOpaque(false);

		GridBagConstraints constraintsPanelImagen = new GridBagConstraints();
		constraintsPanelImagen.gridx = 0; // El Ã¡rea de texto empieza en la columna
		constraintsPanelImagen.gridy = 0; // El Ã¡rea de texto empieza en la fila
		constraintsPanelImagen.gridwidth = GridBagConstraints.REMAINDER; // El Ã¡rea de texto ocupa una columna.
		constraintsPanelImagen.gridheight = 6; // El Ã¡rea de texto ocupa una fila
		constraintsPanelImagen.anchor = GridBagConstraints.NORTHWEST;
		constraintsPanelImagen.fill = GridBagConstraints.BOTH;
		constraintsPanelImagen.weighty = 1;
		constraintsPanelImagen.weightx = 1;

		// Se aÃ±ade el panel
		add(panelImagenProducto, constraintsPanelImagen);

		// Boton Lotes
		JButton botonIniciar = new RoundedButton(170, 40, "Iniciar compra", sourceSansPro.deriveFont(18f));
		GridBagConstraints constraintsIniciar = new GridBagConstraints();
		constraintsIniciar.gridx = 2; // El Ã¡rea de texto empieza en la columna
		constraintsIniciar.gridy = 5; // El Ã¡rea de texto empieza en la fila
		constraintsIniciar.gridwidth = 1; // El Ã¡rea de texto ocupa una columna.
		constraintsIniciar.gridheight = 1; // El Ã¡rea de texto ocupa una fila
		constraintsIniciar.anchor = GridBagConstraints.SOUTHEAST;
		constraintsIniciar.weighty = 1;
		constraintsIniciar.weightx = 1;
		constraintsIniciar.insets = new Insets(0, 0, 80, 50);

		add(botonIniciar, constraintsIniciar);

		JLabel labelCliente = new JLabel("Cliente registrado");
		labelCliente.setFont(sourceSansPro.deriveFont(18f));
		labelCliente.setForeground(Color.WHITE);
		GridBagConstraints constraintsLabelCliente = new GridBagConstraints();
		constraintsLabelCliente.gridx = 1; // El Ã¡rea de texto empieza en la columna
		constraintsLabelCliente.gridy = 5; // El Ã¡rea de texto empieza en la fila
		constraintsLabelCliente.gridwidth = 1; // El Ã¡rea de texto ocupa una columna.
		constraintsLabelCliente.gridheight = 1; // El Ã¡rea de texto ocupa una fila
		constraintsLabelCliente.anchor = GridBagConstraints.SOUTHWEST;
		constraintsLabelCliente.weighty = 1;
		constraintsLabelCliente.weightx = 1;
		constraintsLabelCliente.insets = new Insets(0, 40, 100, 40);

		add(labelCliente, constraintsLabelCliente);

		JComboBox<String> cliente = new JComboBox<String>();
		cliente.addItem("Si");
		cliente.addItem("No");
		cliente.setBackground(new Color(75, 57, 97));
		cliente.setForeground(Color.WHITE);
		GridBagConstraints constraintsCliente = new GridBagConstraints();
		constraintsCliente.gridx = 1; // El Ã¡rea de texto empieza en la columna
		constraintsCliente.gridy = 5; // El Ã¡rea de texto empieza en la fila
		constraintsCliente.gridwidth = 1; // El Ã¡rea de texto ocupa una columna.
		constraintsCliente.gridheight = 1; // El Ã¡rea de texto ocupa una fila
		constraintsCliente.anchor = GridBagConstraints.SOUTHWEST;
		constraintsCliente.weighty = 1;
		constraintsCliente.weightx = 1;
		constraintsCliente.insets = new Insets(0, 190, 100, 40);

		add(cliente, constraintsCliente);

		JLabel labelCedula = new JLabel("Cedula");
		labelCedula.setFont(sourceSansPro.deriveFont(18f));
		labelCedula.setForeground(Color.WHITE);
		GridBagConstraints constraintsLabelCedula = new GridBagConstraints();
		constraintsLabelCedula.gridx = 1; // El Ã¡rea de texto empieza en la columna
		constraintsLabelCedula.gridy = 5; // El Ã¡rea de texto empieza en la fila
		constraintsLabelCedula.gridwidth = 1; // El Ã¡rea de texto ocupa una columna.
		constraintsLabelCedula.gridheight = 1; // El Ã¡rea de texto ocupa una fila
		constraintsLabelCedula.anchor = GridBagConstraints.SOUTHWEST;
		constraintsLabelCedula.weighty = 1;
		constraintsLabelCedula.weightx = 1;
		constraintsLabelCedula.insets = new Insets(0, 124, 65, 40);

		add(labelCedula, constraintsLabelCedula);
		
		JLabel numeroCompras = new JLabel("Compras: 0");
		numeroCompras.setFont(sourceSansPro.deriveFont(18f));
		numeroCompras.setForeground(Color.WHITE);
		GridBagConstraints constraintsNumeroCompras = new GridBagConstraints();
		constraintsNumeroCompras.gridx = 1; // El Ã¡rea de texto empieza en la columna
		constraintsNumeroCompras.gridy = 5; // El Ã¡rea de texto empieza en la fila
		constraintsNumeroCompras.gridwidth = 1; // El Ã¡rea de texto ocupa una columna.
		constraintsNumeroCompras.gridheight = 1; // El Ã¡rea de texto ocupa una fila
		constraintsNumeroCompras.anchor = GridBagConstraints.SOUTHWEST;
		constraintsNumeroCompras.weighty = 1;
		constraintsNumeroCompras.weightx = 1;
		constraintsNumeroCompras.insets = new Insets(0, 123, 32, 40);

		add(numeroCompras, constraintsNumeroCompras);

		JTextField cedulaField = new JTextField(15);
		cedulaField.setBackground(new Color(75, 57, 97));
		cedulaField.setForeground(Color.WHITE);
		cedulaField.setEditable(true);
		GridBagConstraints constrainsCedulaField = new GridBagConstraints();
		constrainsCedulaField.gridx = 1; // El Ã¡rea de texto empieza en la columna
		constrainsCedulaField.gridy = 5; // El Ã¡rea de texto empieza en la fila
		constrainsCedulaField.gridwidth = 1; // El Ã¡rea de texto ocupa una columna.
		constrainsCedulaField.gridheight = 1; // El Ã¡rea de texto ocupa una fila
		constrainsCedulaField.anchor = GridBagConstraints.SOUTHWEST;
		constrainsCedulaField.weighty = 1;
		constrainsCedulaField.weightx = 1;
		constrainsCedulaField.insets = new Insets(0, 187, 65, 0);

		add(cedulaField, constrainsCedulaField);

		// Listeners

		cliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = (String) cliente.getSelectedItem();
				if (selected.equals("No")) {
					cedulaField.setText("");
					cedulaField.setEditable(false);
				} else {
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
				} else {
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

		cedulaField.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent e) {

				cedula_actual = cedulaField.getText();
				arrayFrecuenciaCompras = owner.getHandlerPos().getFechasCliente(cedula_actual);
				
				remove(numeroCompras);
				int totalC=0;
				for (int i=0; i<arrayFrecuenciaCompras.length;i++) {
					totalC+=arrayFrecuenciaCompras[i];
				}
				
				JLabel numeroCompras = new JLabel("Compras: "+totalC);
				numeroCompras.setFont(sourceSansPro.deriveFont(18f));
				numeroCompras.setForeground(Color.WHITE);
				GridBagConstraints constraintsNumeroCompras = new GridBagConstraints();
				constraintsNumeroCompras.gridx = 1; // El Ã¡rea de texto empieza en la columna
				constraintsNumeroCompras.gridy = 5; // El Ã¡rea de texto empieza en la fila
				constraintsNumeroCompras.gridwidth = 1; // El Ã¡rea de texto ocupa una columna.
				constraintsNumeroCompras.gridheight = 1; // El Ã¡rea de texto ocupa una fila
				constraintsNumeroCompras.anchor = GridBagConstraints.SOUTHWEST;
				constraintsNumeroCompras.weighty = 1;
				constraintsNumeroCompras.weightx = 1;
				constraintsNumeroCompras.insets = new Insets(0, 123, 32, 40);

				add(numeroCompras, constraintsNumeroCompras);
				
				revalidate();
				repaint();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				cedula_actual = cedulaField.getText();
				arrayFrecuenciaCompras = owner.getHandlerPos().getFechasCliente(cedula_actual);
				
				remove(numeroCompras);
				int totalC=0;
				for (int i=0; i<arrayFrecuenciaCompras.length;i++) {
					totalC+=arrayFrecuenciaCompras[i];
				}
				
				JLabel numeroCompras = new JLabel("Compras: "+totalC);
				numeroCompras.setFont(sourceSansPro.deriveFont(18f));
				numeroCompras.setForeground(Color.WHITE);
				GridBagConstraints constraintsNumeroCompras = new GridBagConstraints();
				constraintsNumeroCompras.gridx = 1; // El Ã¡rea de texto empieza en la columna
				constraintsNumeroCompras.gridy = 5; // El Ã¡rea de texto empieza en la fila
				constraintsNumeroCompras.gridwidth = 1; // El Ã¡rea de texto ocupa una columna.
				constraintsNumeroCompras.gridheight = 1; // El Ã¡rea de texto ocupa una fila
				constraintsNumeroCompras.anchor = GridBagConstraints.SOUTHWEST;
				constraintsNumeroCompras.weighty = 1;
				constraintsNumeroCompras.weightx = 1;
				constraintsNumeroCompras.insets = new Insets(0, 123, 32, 40);

				add(numeroCompras, constraintsNumeroCompras);
				
				revalidate();
				
				repaint();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {

				cedula_actual = cedulaField.getText();
				arrayFrecuenciaCompras = owner.getHandlerPos().getFechasCliente(cedula_actual);
				
				remove(numeroCompras);
				int totalC=0;
				for (int i=0; i<arrayFrecuenciaCompras.length;i++) {
					totalC+=arrayFrecuenciaCompras[i];
				}
				
				JLabel numeroCompras = new JLabel("Compras: "+totalC);
				numeroCompras.setFont(sourceSansPro.deriveFont(18f));
				numeroCompras.setForeground(Color.WHITE);
				GridBagConstraints constraintsNumeroCompras = new GridBagConstraints();
				constraintsNumeroCompras.gridx = 1; // El Ã¡rea de texto empieza en la columna
				constraintsNumeroCompras.gridy = 5; // El Ã¡rea de texto empieza en la fila
				constraintsNumeroCompras.gridwidth = 1; // El Ã¡rea de texto ocupa una columna.
				constraintsNumeroCompras.gridheight = 1; // El Ã¡rea de texto ocupa una fila
				constraintsNumeroCompras.anchor = GridBagConstraints.SOUTHWEST;
				constraintsNumeroCompras.weighty = 1;
				constraintsNumeroCompras.weightx = 1;
				constraintsNumeroCompras.insets = new Insets(0, 123, 32, 40);

				add(numeroCompras, constraintsNumeroCompras);
				
				revalidate();
				
				repaint();
			}

		});

		// Se hace visible
		setVisible(true);
	}

	public void cerrarVentana() {
		setVisible(false);
	}
}
