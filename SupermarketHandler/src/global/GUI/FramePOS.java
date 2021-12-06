package global.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import global.modelo.Producto;


public class FramePOS extends JInternalFrame{

	private static final long serialVersionUID = -3111079588287177991L;
	InterfazGrafica owner;

	JPanel panelInfo;
	JPanel panelRequs;
	JPanel panelBusqueda;
	JPanel panelCompra;
	JScrollPane panelProductosScrolleable;
	int numeroCompra=0;

	public FramePOS(InterfazGrafica owner) {

		setTitle("Sistema POS");
		this.owner = owner;

		// Se establece la fuente externa que se va a usar (Si no la encuentra se usa
		// Arial por defecto)
		Font sourceSansPro = new SourceSansFont(400, 60).getSourceSansFontFont();

		// Configuraciones estéticas de la ventana
		getContentPane().setBackground(new Color(118, 88, 152));

		// Establecer el tipo de layout de la interfaz
		setLayout(new GridBagLayout());

		// // //Establecer los componentes// // //

		// //Panel Superior - Información // //
		panelInfo = new JPanel();

		panelInfo.setLayout(new GridBagLayout());
		panelInfo.setBackground(new Color(82, 67, 110));
		panelInfo.setPreferredSize(new Dimension(1127, 80));
		GridBagConstraints constraintsInfoPanel = new GridBagConstraints();
		constraintsInfoPanel.gridx = 0; // El área de texto empieza en la columna
		constraintsInfoPanel.gridy = 0; // El área de texto empieza en la fila
		constraintsInfoPanel.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsInfoPanel.gridheight = 1; // El área de texto ocupa una fila
		constraintsInfoPanel.anchor = GridBagConstraints.NORTHWEST;
		constraintsInfoPanel.weighty = 1;
		constraintsInfoPanel.weightx = 1;

		// Logo supermercado//
		try {
			BufferedImage logo = ImageIO.read(new File("sources/logo.png"));
			JLabel logoLabel = new JLabel(new ImageIcon(logo));
			GridBagConstraints constraintslogoLabel = new GridBagConstraints();
			constraintslogoLabel.gridx = 0; // El área de texto empieza en la columna
			constraintslogoLabel.gridy = 0; // El área de texto empieza en la fila
			constraintslogoLabel.gridwidth = 1; // El área de texto ocupa una columna.
			constraintslogoLabel.gridheight = 1; // El área de texto ocupa una fila
			constraintslogoLabel.weightx = 1;
			constraintslogoLabel.weighty = 1;
			constraintslogoLabel.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
			constraintslogoLabel.insets = new Insets(0, 30, 0, 0);

			panelInfo.add(logoLabel, constraintslogoLabel);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Nombre Supermercado
		JLabel labelNombre = new JLabel("Supermercados NERV");
		labelNombre.setFont(sourceSansPro.deriveFont(35f));
		labelNombre.setForeground(Color.WHITE);
		GridBagConstraints constraintsTitle = new GridBagConstraints();
		constraintsTitle.gridx = 1; // El área de texto empieza en la columna uno
		constraintsTitle.gridy = 0; // El área de texto empieza en la fila cero
		constraintsTitle.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsTitle.gridheight = 1; // El área de texto ocupa 1 fila
		constraintsTitle.anchor = GridBagConstraints.WEST;
		constraintsTitle.weightx = 1;
		constraintsTitle.weighty = 1;
		constraintsTitle.insets = new Insets(0, 0, 0, 320);

		panelInfo.add(labelNombre, constraintsTitle);

		// Nombre Sistema
		JLabel labelSistema = new JLabel("| Sistema POS");
		labelSistema.setFont(sourceSansPro.deriveFont(35f));
		labelSistema.setForeground(Color.WHITE);
		GridBagConstraints constraintsSistema = new GridBagConstraints();
		constraintsSistema.gridx = 2; // El área de texto empieza en la columna uno
		constraintsSistema.gridy = 0; // El área de texto empieza en la fila cero
		constraintsSistema.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsSistema.gridheight = 1; // El área de texto ocupa 1 fila
		constraintsSistema.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
		constraintsSistema.weightx = 1;
		constraintsSistema.weighty = 1;
		constraintsSistema.insets = new Insets(0, 0, 0, 30);
		panelInfo.add(labelSistema, constraintsSistema);

		// Agregar panel info
		add(panelInfo, constraintsInfoPanel);

		// //Panel Izquierdo - Requerimientos Funcionales // //
		panelRequs = new RoundedPanel(238, 467) {
			private static final long serialVersionUID = 7755889302022105131L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(new Color(99, 78, 128));
				g.fillRect(0, -150, this.getWidth(), this.getHeight());
			};
		};
		panelRequs.setLayout(new GridBagLayout());
		GridBagConstraints constraintspanelRequs = new GridBagConstraints();
		constraintspanelRequs.gridx = 0; // El área de texto empieza en la columna
		constraintspanelRequs.gridy = 1; // El área de texto empieza en la fila
		constraintspanelRequs.gridwidth = 1; // El área de texto ocupa una columna.
		constraintspanelRequs.gridheight = 1; // El área de texto ocupa una fila
		constraintspanelRequs.anchor = GridBagConstraints.NORTHWEST;
		constraintspanelRequs.weighty = 1;
		constraintspanelRequs.weightx = 1;
		constraintspanelRequs.insets = new Insets(0, 0, 30, 10);


		// Agregar Boton Registrar cliente
		JButton botonRegistrarCliente = new RoundedButton(176, 46, "Registrar cliente",
				sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsRegistrarCliente = new GridBagConstraints();
		constraintsRegistrarCliente.gridx = 0; // El área de texto empieza en la columna
		constraintsRegistrarCliente.gridy = 1; // El área de texto empieza en la fila
		constraintsRegistrarCliente.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsRegistrarCliente.gridheight = 1; // El área de texto ocupa una fila
		constraintsRegistrarCliente.anchor = GridBagConstraints.CENTER;
		constraintsRegistrarCliente.weighty = 0.5;
		constraintsRegistrarCliente.weightx = 1;
		panelRequs.add(botonRegistrarCliente, constraintsRegistrarCliente);

		// Agregar Boton Iniciar Compra Activa
		JButton botonIniciarCompra = new RoundedButton(176, 46, "Iniciar compra activa", sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsIniciarCompra = new GridBagConstraints();
		constraintsIniciarCompra.gridx = 0; // El área de texto empieza en la columna
		constraintsIniciarCompra.gridy = 2; // El área de texto empieza en la fila
		constraintsIniciarCompra.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsIniciarCompra.gridheight = 1; // El área de texto ocupa una fila
		constraintsIniciarCompra.anchor = GridBagConstraints.CENTER;
		constraintsIniciarCompra.weighty = 0.5;
		constraintsIniciarCompra.weightx = 1;
		panelRequs.add(botonIniciarCompra, constraintsIniciarCompra);

		// Agregar Boton Finalizar compra
		JButton botonFinalizarCompra = new RoundedButton(176, 46, "Finalizar compra", sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsFinalizarCompra = new GridBagConstraints();
		constraintsFinalizarCompra.gridx = 0; // El área de texto empieza en la columna
		constraintsFinalizarCompra.gridy = 3; // El área de texto empieza en la fila
		constraintsFinalizarCompra.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsFinalizarCompra.gridheight = 1; // El área de texto ocupa una fila
		constraintsFinalizarCompra.anchor = GridBagConstraints.CENTER;
		constraintsFinalizarCompra.weighty = 0.5;
		constraintsFinalizarCompra.weightx = 1;
		panelRequs.add(botonFinalizarCompra, constraintsFinalizarCompra);

		// Se añade el panel de los requerimientos
		add(panelRequs, constraintspanelRequs);

		// //Mini Panel - Búsqueda de productos // //
		panelBusqueda = new JPanel();
		panelBusqueda.setBackground(new Color(118, 88, 152));
		panelBusqueda.setLayout(new GridBagLayout());
		GridBagConstraints constraintsPanelBusqueda = new GridBagConstraints();
		constraintsPanelBusqueda.gridx = 0; // El área de texto empieza en la columna
		constraintsPanelBusqueda.gridy = 1; // El área de texto empieza en la fila
		constraintsPanelBusqueda.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsPanelBusqueda.gridheight = 1; // El área de texto ocupa una fila
		constraintsPanelBusqueda.anchor = GridBagConstraints.NORTHEAST;
		constraintsPanelBusqueda.weighty = 1;
		constraintsPanelBusqueda.weightx = 1;

		// Logo lupa //
		try {
			BufferedImage lupa = ImageIO.read(new File("sources/lupa.png"));
			JLabel lupaLabel = new JLabel(new ImageIcon(lupa));
			GridBagConstraints constraintsLupaLabel = new GridBagConstraints();
			constraintsLupaLabel.gridx = 0; // El área de texto empieza en la columna
			constraintsLupaLabel.gridy = 0; // El área de texto empieza en la fila
			constraintsLupaLabel.gridwidth = 1; // El área de texto ocupa una columna.
			constraintsLupaLabel.gridheight = 1; // El área de texto ocupa una fila
			constraintsLupaLabel.weightx = 1;
			constraintsLupaLabel.weighty = 1;
			constraintsLupaLabel.anchor = GridBagConstraints.WEST;
			constraintsLupaLabel.insets = new Insets(30, 0, 0, 0);

			panelBusqueda.add(lupaLabel, constraintsLupaLabel);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// JTextField Búsqueda
		CustomTextField textFieldBusqueda = new CustomTextField(176, 31);
		GridBagConstraints constraintsBusqueda = new GridBagConstraints();
		constraintsBusqueda.gridx = 1; // El área de texto empieza en la columna
		constraintsBusqueda.gridy = 0; // El área de texto empieza en la fila
		constraintsBusqueda.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsBusqueda.gridheight = 1; // El área de texto ocupa una fila
		constraintsBusqueda.weightx = 1;
		constraintsBusqueda.weighty = 1;
		constraintsBusqueda.anchor = GridBagConstraints.EAST;
		constraintsBusqueda.insets = new Insets(30, 10, 0, 40);

		panelBusqueda.add(textFieldBusqueda, constraintsBusqueda);

		// Se añade el panel de busqueda
		add(panelBusqueda, constraintsPanelBusqueda);
		
		// Informacion de la compra activa
		panelCompra= new JPanel();
		panelCompra.setBackground(new Color(118, 88, 152));
		panelCompra.setLayout(new GridBagLayout());
		GridBagConstraints constraintsPanelCompra = new GridBagConstraints();
		constraintsPanelCompra.gridx = 0; // El área de texto empieza en la columna
		constraintsPanelCompra.gridy = 1; // El área de texto empieza en la fila
		constraintsPanelCompra.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsPanelCompra.gridheight = 1; // El área de texto ocupa una fila
		constraintsPanelCompra.anchor = GridBagConstraints.NORTH;
		constraintsPanelCompra.weighty = 1;
		constraintsPanelCompra.weightx = 1;
		
		
		JLabel labelCompra = new JLabel("Compra activa: #"+numeroCompra+"          Cliente: N/A");
		labelCompra.setFont(sourceSansPro.deriveFont(20f));
		labelCompra.setForeground(Color.WHITE);
		GridBagConstraints constraintsCompra = new GridBagConstraints();
		constraintsCompra.gridx = 1; // El área de texto empieza en la columna uno
		constraintsCompra.gridy = 0; // El área de texto empieza en la fila cero
		constraintsCompra.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsCompra.gridheight = 1; // El área de texto ocupa 1 fila
		constraintsCompra.anchor = GridBagConstraints.CENTER;
		constraintsCompra.weightx = 1;
		constraintsCompra.weighty = 1;
		constraintsCompra.insets = new Insets(50, 40, 0, 300);
		
		
		panelCompra.add(labelCompra, constraintsCompra);
		add(panelCompra, constraintsPanelCompra);

		// //Panel ListaProductos // //
		actualizarPanelProductos(null);

		// // // Listeners Necesarios! // // //
		// Buscar producto
		textFieldBusqueda.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent e) {

				String idBuscado = textFieldBusqueda.getText();

				remove(panelProductosScrolleable);
				actualizarPanelProductos(idBuscado);
				revalidate();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				String idBuscado = textFieldBusqueda.getText();

				// TODO Auto-generated method stub
				remove(panelProductosScrolleable);
				actualizarPanelProductos(idBuscado);
				revalidate();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {

				String idBuscado = textFieldBusqueda.getText();

				// TODO Auto-generated method stub
				remove(panelProductosScrolleable);
				actualizarPanelProductos(idBuscado);
				revalidate();
			}

		});

		// // Listeners requerimientos funcionales // //

		// Registrar Cliente
		botonRegistrarCliente.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				agregarCliente();
			}
		});

		// Iniciar Compra
		botonIniciarCompra.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				iniciarCompra();
			}
		});

		// Finalizar compra
		botonFinalizarCompra.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				mostrarFactura();
			}
		});

	}

	// Métodos para resolver requerimientos
	public void actualizarNumClientes(String clienteCC) {
		this.numeroCompra++;
		remove(panelCompra);
		panelCompra= new JPanel();
		panelCompra.setBackground(new Color(118, 88, 152));
		panelCompra.setLayout(new GridBagLayout());
		GridBagConstraints constraintsPanelCompra = new GridBagConstraints();
		constraintsPanelCompra.gridx = 0; // El área de texto empieza en la columna
		constraintsPanelCompra.gridy = 1; // El área de texto empieza en la fila
		constraintsPanelCompra.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsPanelCompra.gridheight = 1; // El área de texto ocupa una fila
		constraintsPanelCompra.anchor = GridBagConstraints.NORTH;
		constraintsPanelCompra.weighty = 1;
		constraintsPanelCompra.weightx = 1;
		

		Font sourceSansPro = new SourceSansFont(400, 60).getSourceSansFontFont();
		JLabel labelCompra = new JLabel("Compra activa: #"+numeroCompra+"          Cliente: "+clienteCC);
		labelCompra.setFont(sourceSansPro.deriveFont(20f));
		labelCompra.setForeground(Color.WHITE);
		GridBagConstraints constraintsCompra = new GridBagConstraints();
		constraintsCompra.gridx = 1; // El área de texto empieza en la columna uno
		constraintsCompra.gridy = 0; // El área de texto empieza en la fila cero
		constraintsCompra.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsCompra.gridheight = 1; // El área de texto ocupa 1 fila
		constraintsCompra.anchor = GridBagConstraints.CENTER;
		constraintsCompra.weightx = 1;
		constraintsCompra.weighty = 1;
		constraintsCompra.insets = new Insets(50, 40, 0, 300);
		
		
		panelCompra.add(labelCompra, constraintsCompra);
		add(panelCompra, constraintsPanelCompra);
		revalidate();
	}
	private void actualizarPanelProductos(String idBuscado) {
		int miniPanel_width = 150;
		int miniPanel_height = 150;
		int miniPanel_amount = 0;

		RoundedPanel panelProductos = new RoundedPanel(803, 380);
		GridBagConstraints constraintsPanelProductos = new GridBagConstraints();
		constraintsPanelProductos.gridx = 0; // El área de texto empieza en la columna
		constraintsPanelProductos.gridy = 1; // El área de texto empieza en la fila
		constraintsPanelProductos.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsPanelProductos.gridheight = 1; // El área de texto ocupa una fila
		constraintsPanelProductos.weightx = 1;
		constraintsPanelProductos.weighty = 1;
		constraintsPanelProductos.anchor = GridBagConstraints.SOUTHEAST;
		constraintsPanelProductos.insets = new Insets(0, 0, 30, 40);

		// Productos agregados al panel
		Collection<Producto> colleccionProductos = owner.getHandlerSi().ListaProductosInventario();
		Iterator<Producto> iteratorCollecionProductos = colleccionProductos.iterator();

		while (iteratorCollecionProductos.hasNext()) {
			Producto productoActual = iteratorCollecionProductos.next();

			String nombreProducto = productoActual.getNombre();
			String codigoProducto = productoActual.getCodigoProducto().getCodigo();
			String pathImagen = productoActual.getPathImagen();

			CustomImagePanel miniPanelProducto = new CustomImagePanel(miniPanel_width, miniPanel_height, nombreProducto,
					codigoProducto, pathImagen, owner);

			if (idBuscado == null || codigoProducto.startsWith(idBuscado)) {
				
				panelProductos.add(miniPanelProducto);
				miniPanel_amount += 1;
			}
		}

		int scollableWidth = 803;
		int scrollableHeight = ((miniPanel_amount * miniPanel_height)
				- ((miniPanel_amount * miniPanel_width) / scollableWidth));
		panelProductos.setPreferredSize(new Dimension(scollableWidth, scrollableHeight));

		// Se añade el panel productos /scrolleable
		panelProductosScrolleable = new JScrollPane(panelProductos);
		panelProductosScrolleable.setPreferredSize(new Dimension(803, 380));
		panelProductosScrolleable.setOpaque(false);
		panelProductosScrolleable.getViewport().setOpaque(false);
		add(panelProductosScrolleable, constraintsPanelProductos);

	}




	public void agregarCliente() {
		

			JTextField nombre = new JTextField();
			JTextField edad = new JTextField();
			JTextField cedula = new JTextField();
			JTextField sexo = new JTextField();
			JTextField estadoCivil = new JTextField();
			JTextField situacionLaboral = new JTextField();


			

				Object[] message_interno = { "Nombre", nombre, "Edad", edad,
						"Cedula", cedula, "Sexo (M/F)", sexo, "Estado Civil", estadoCivil, 
						"Situacion Laboral", situacionLaboral};

				int registro = JOptionPane.showConfirmDialog(owner, message_interno,
						"Registrar Cliente", JOptionPane.OK_CANCEL_OPTION);

				if (registro == JOptionPane.OK_OPTION) {

					try {
						owner.getHandlerPos().registrarCliente(nombre.getText(), Integer.parseInt(edad.getText()),
								sexo.getText().charAt(0), cedula.getText(), estadoCivil.getText(), situacionLaboral.getText());
						JOptionPane.showMessageDialog(owner, "Se registro el cliente de forma satisfactoria", "Registro",
								JOptionPane.PLAIN_MESSAGE);
					} catch (NumberFormatException e ) {
						JOptionPane.showMessageDialog(owner, "Se produjo un error en los datos ingresados, vuelva a intentarlo", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (global.sistemas.pos.procesamiento.HandledException e) {
						JOptionPane.showMessageDialog(owner, "Se produjo un error en los datos ingresados, vuelva a intentarlo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			
	}
	
	public void iniciarCompra() {
		

		new CompraFramePOS(owner);

		
}

	public void mostrarFactura() {
		if (owner.getHandlerPos().hayCompraActual()) {
			String factura="";
			int puntos_a_redimir = 0;
			int descuento_puntos = 0;
			
		
				String puntos_disponibles = ""+ owner.getHandlerPos().getPuntosCliente();
				
				String option = JOptionPane.showInputDialog("¿Desea redimir algunos de sus puntos acumulados para completar la compra?\n Dispone de "+ puntos_disponibles + " puntos (1 punto = 15 pesos)");
				
				try {
					puntos_a_redimir = Integer.parseInt(option);
					
					//Manejo de error - Se intentan redimir más puntos de los disponibles
					if(puntos_a_redimir > owner.getHandlerPos().getPuntosCliente()) {
						JOptionPane.showMessageDialog(owner, "Ha ingresado una cantidad de puntos superior a la disponible. No se descontaran puntos.");
					}
					
					else {
						
						descuento_puntos = puntos_a_redimir * 15;
						owner.getHandlerPos().getCompraActual().descontarAlPrecio(descuento_puntos);
						factura+= owner.getHandlerPos().facturarCompra(puntos_a_redimir, descuento_puntos);
						JOptionPane.showMessageDialog(owner, factura, "Factura",
								JOptionPane.PLAIN_MESSAGE);
					}
					
				}
				//Manejo de error - No se ingresa un número válido
				catch(Exception e) {
					JOptionPane.showMessageDialog(owner, "Ha ingresado una cantidad invalida. No se descontaran puntos.");
				}
			
			
		}else {
			JOptionPane.showMessageDialog(owner, "Inicie una compra primero", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	
}
