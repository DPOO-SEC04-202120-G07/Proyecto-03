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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import global.modelo.Producto;
import global.sistemas.inventario.procesamiento.HandledException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JCalendar;

public class FrameSI extends JInternalFrame {

	private static final long serialVersionUID = -3111079588287177991L;
	InterfazGrafica owner;

	JPanel panelInfo;
	JPanel panelRequs;
	JPanel panelBusqueda;
	JScrollPane panelProductosScrolleable;

	public FrameSI(InterfazGrafica owner) {

		setTitle("Sistema Inventario");
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
		JLabel labelSistema = new JLabel("| Sistema Inventario");
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

		// Agregar Boton Cargar Lotes
		JButton botonCargarLotes = new RoundedButton(176, 46, "Cargar lotes", sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsBotonLotes = new GridBagConstraints();
		constraintsBotonLotes.gridx = 0; // El área de texto empieza en la columna
		constraintsBotonLotes.gridy = 0; // El área de texto empieza en la fila
		constraintsBotonLotes.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsBotonLotes.gridheight = 1; // El área de texto ocupa una fila
		constraintsBotonLotes.anchor = GridBagConstraints.CENTER;
		constraintsBotonLotes.weighty = 0.5;
		constraintsBotonLotes.weightx = 1;
		panelRequs.add(botonCargarLotes, constraintsBotonLotes);

		// Agregar Boton Eliminar Lotes
		JButton botonEliminarLotes = new RoundedButton(176, 46, "Eliminar lotes vencidos",
				sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsEliminarLotes = new GridBagConstraints();
		constraintsEliminarLotes.gridx = 0; // El área de texto empieza en la columna
		constraintsEliminarLotes.gridy = 1; // El área de texto empieza en la fila
		constraintsEliminarLotes.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsEliminarLotes.gridheight = 1; // El área de texto ocupa una fila
		constraintsEliminarLotes.anchor = GridBagConstraints.CENTER;
		constraintsEliminarLotes.weighty = 0.5;
		constraintsEliminarLotes.weightx = 1;
		panelRequs.add(botonEliminarLotes, constraintsEliminarLotes);

		// Agregar Boton Agregar Unidad de Almacenamiento
		JButton botonAgregarUnidad = new RoundedButton(176, 46, "Agregar U. de alm.", sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsAgregarUnidad = new GridBagConstraints();
		constraintsAgregarUnidad.gridx = 0; // El área de texto empieza en la columna
		constraintsAgregarUnidad.gridy = 2; // El área de texto empieza en la fila
		constraintsAgregarUnidad.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsAgregarUnidad.gridheight = 1; // El área de texto ocupa una fila
		constraintsAgregarUnidad.anchor = GridBagConstraints.CENTER;
		constraintsAgregarUnidad.weighty = 0.5;
		constraintsAgregarUnidad.weightx = 1;
		panelRequs.add(botonAgregarUnidad, constraintsAgregarUnidad);

		// Agregar Boton Agregar Imagen
		JButton botonAgregarImagen = new RoundedButton(176, 46, "Agregar imagen", sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsAgregarImagen = new GridBagConstraints();
		constraintsAgregarImagen.gridx = 0; // El área de texto empieza en la columna
		constraintsAgregarImagen.gridy = 3; // El área de texto empieza en la fila
		constraintsAgregarImagen.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsAgregarImagen.gridheight = 1; // El área de texto ocupa una fila
		constraintsAgregarImagen.anchor = GridBagConstraints.CENTER;
		constraintsAgregarImagen.weighty = 0.5;
		constraintsAgregarImagen.weightx = 1;
		panelRequs.add(botonAgregarImagen, constraintsAgregarImagen);

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

		// Cargar Lotes
		botonCargarLotes.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				cargarLotes();
				remove(panelProductosScrolleable);
				actualizarPanelProductos(null);
				revalidate();

				;
			}
		});

		// Eliminar Lotes Vencidos
		botonEliminarLotes.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				eliminarLotesVencidos();
				cargarLotes();
				remove(panelProductosScrolleable);
				actualizarPanelProductos(null);
				revalidate();
			}
		});

		// Agregar Unidad de Almacenamiento
		botonAgregarUnidad.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				agregarUnidadDeAlmacenamiento();
			}
		});

		// Agregar Imagen
		botonAgregarImagen.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(java.awt.event.MouseEvent evt) {
				agregarImagen();
				remove(panelProductosScrolleable);
				actualizarPanelProductos(null);
				revalidate();
			}
		});

	}

	// Métodos para resolver requerimientos

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

	public void cargarLotes() {

		FileFilter csv_filter = new FileNameExtensionFilter("CSV File", "csv");

		JFileChooser fileChooser = new JFileChooser("./lotesNuevos");
		fileChooser.setFileFilter(csv_filter);
		fileChooser.setDialogTitle("Cargar Lotes");
		int seleccion = fileChooser.showOpenDialog(owner);

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			String path_fichero = fileChooser.getSelectedFile().toString();

			try {
				owner.getHandlerSi().cargarLote(path_fichero);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (HandledException e) {
				if (e.getCode() == "null-unidad") {
					JOptionPane.showMessageDialog(owner,
							"Una de las unidades en las que se ha intentado almacenar un producto no existe. Intente de nuevo.");
				}
			}
		}
	}

	public void eliminarLotesVencidos() {
		JCalendar calendar = new JCalendar();
		calendar.setPreferredSize(new Dimension(300, 300));

		int option = JOptionPane.showConfirmDialog(owner, calendar, "Ingrese la fecha de hoy",
				JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			Date fecha_vencimiento = calendar.getDate();
			try {
				owner.getHandlerSi().eliminarProductosVencidos(fecha_vencimiento);
			} catch (HandledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JOptionPane.showMessageDialog(owner, "Los lotes vencidos han sido marcados exitosamente.",
					"Operación exitosa", JOptionPane.INFORMATION_MESSAGE);

		}

	}

	public void agregarUnidadDeAlmacenamiento() {
		JLabel pregunta_usuario = new JLabel("¿Qué tipo de Unidad de Almacenamineto desea crear?");
		JComboBox<String> boxUnidad = new JComboBox<String>();
		boxUnidad.addItem("Gondola");
		boxUnidad.addItem("Frescos");
		boxUnidad.addItem("Congelador");
		boxUnidad.addItem("Refrigerador");

		Object[] message = { pregunta_usuario, boxUnidad };

		int option = JOptionPane.showConfirmDialog(owner, message, "Creación Unidad de Almacenamiento",
				JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			String selectedUnit = (String) boxUnidad.getSelectedItem();

			JTextField idUnidad = new JTextField();
			JTextField pasillo = new JTextField();
			JTextField capacidad = new JTextField();

			JTextField caracteristicaExclusiva = new JTextField();

			if (selectedUnit == "Gondola") {

				Object[] message_interno = { "ID de la Unidad (Recuerde el prefijo U-)", idUnidad, "Pasillo", pasillo,
						"Capacidad", capacidad, "Número de repsisas", caracteristicaExclusiva };

				int option2 = JOptionPane.showConfirmDialog(owner, message_interno,
						"Ingrese la información de la Góndola", JOptionPane.OK_CANCEL_OPTION);

				if (option2 == JOptionPane.OK_OPTION) {

					owner.getHandlerSi().crearNuevaGondola(idUnidad.getText(), pasillo.getText(), capacidad.getText(),
							caracteristicaExclusiva.getText());

				}
			}

			else if (selectedUnit == "Frescos") {

				Object[] message_interno = { "ID de la Unidad (Recuerde el prefijo U-)", idUnidad, "Pasillo", pasillo,
						"Capacidad", capacidad, "Condiciones de almacenamiento", caracteristicaExclusiva };

				int option2 = JOptionPane.showConfirmDialog(owner, message_interno,
						"Ingrese la información de la Unidad Frescos", JOptionPane.OK_CANCEL_OPTION);

				if (option2 == JOptionPane.OK_OPTION) {
					owner.getHandlerSi().crearNuevoFrescos(idUnidad.getText(), pasillo.getText(), capacidad.getText(),
							caracteristicaExclusiva.getText());
				}

			}

			else if (selectedUnit == "Congelador") {

				Object[] message_interno = { "ID de la Unidad (Recuerde el prefijo U-)", idUnidad, "Pasillo", pasillo,
						"Capacidad", capacidad, "Volumen (mL)", caracteristicaExclusiva };

				int option2 = JOptionPane.showConfirmDialog(owner, message_interno,
						"Ingrese la información del Congelador", JOptionPane.OK_CANCEL_OPTION);

				if (option2 == JOptionPane.OK_OPTION) {
					owner.getHandlerSi().crearNuevoCongelador(idUnidad.getText(), pasillo.getText(),
							capacidad.getText(), caracteristicaExclusiva.getText());
				}

			}

			else if (selectedUnit == "Refrigerador") {

				Object[] message_interno = { "ID de la Unidad (Recuerde el prefijo U-)", idUnidad, "Pasillo", pasillo,
						"Capacidad", capacidad, "Volumen (mL)", caracteristicaExclusiva };

				int option2 = JOptionPane.showConfirmDialog(owner, message_interno,
						"Ingrese la información del Refrigerador", JOptionPane.OK_CANCEL_OPTION);

				if (option2 == JOptionPane.OK_OPTION) {
					owner.getHandlerSi().crearNuevoRefrigerador(idUnidad.getText(), pasillo.getText(),
							capacidad.getText(), caracteristicaExclusiva.getText());
				}

			}

		}
		;
	}

	public void agregarImagen() {

		String codigoProducto = JOptionPane.showInputDialog(owner,
				"Ingrese el código del producto al que desea agregarle una imagen", "Agregar Imagen",
				JOptionPane.OK_CANCEL_OPTION);

		if (owner.getHandlerSi().getProducto(codigoProducto) != null) {

			FileFilter image_filter = new FileNameExtensionFilter("Image File", "jpg", "png", "jpeg", "tif");

			JFileChooser fileChooser = new JFileChooser("./imagenesProductos");
			fileChooser.setFileFilter(image_filter);
			fileChooser.setDialogTitle("Agregar Imagen");
			int seleccion = fileChooser.showOpenDialog(owner);

			if (seleccion == JFileChooser.APPROVE_OPTION) {
				String path_fichero = "None";
				path_fichero = "./imagenesProductos/" + fileChooser.getSelectedFile().getName();
				owner.getHandlerSi().agregarImagenProducto(codigoProducto, path_fichero);
			}

		} else {
			JOptionPane.showMessageDialog(owner, "El producto ingresado no existe. Intente de nuevo.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// MÉTODOS DE RESPUESTA
	public ArrayList<String> askCategoria(String nombreProducto) {

		ArrayList<String> infoCategoria = new ArrayList<String>();

		// Preguntar por categorias a crear
		JLabel intro_message = new JLabel(
				"El producto '" + nombreProducto + "' no tiene una categoría asociada. Creela a continuación:\n");
		JTextField nombreCat = new JTextField();
		JTextField numPasillo = new JTextField();
		JTextField nombreSubCats = new JTextField();

		Object[] message = { intro_message, "Ingrese el nombre de la categoría asociada:", nombreCat,
				"Ingrese el pasillo en el que se ubica la categoría:", numPasillo,
				"Ingrese el nombre de las subcategorías asociadas separadas por un -:", nombreSubCats };

		int option = JOptionPane.showConfirmDialog(owner, message, "Ingrese la información del producto",
				JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			infoCategoria.add(nombreCat.getText());
			infoCategoria.add(numPasillo.getText());
			infoCategoria.add(nombreSubCats.getText());
		}

		return infoCategoria;
	}

	public ArrayList<String> askSubCategoria(String nombreSubCat) {

		ArrayList<String> infoSubCategoria = new ArrayList<String>();

		JLabel intro_message = new JLabel("Indique la información de la subcategoría: " + nombreSubCat + "\n");
		JTextField numEstante = new JTextField();
		JTextField nivEstante = new JTextField();

		Object[] message = { intro_message, "Ingrese el número de estante en el que se ubica la subcategoría: ",
				numEstante, "Ingrese el nivel de estante en el que se ubica la subcategoría: ", nivEstante };

		int option = JOptionPane.showConfirmDialog(owner, message, "Ingrese la información del producto",
				JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			infoSubCategoria.add(numEstante.getText());
			infoSubCategoria.add(nivEstante.getText());
		}

		return infoSubCategoria;
	}

	public String askUnidad(String nombre) {

		JTextField idUnidad = new JTextField();
		String infoUnidad = "";

		Object[] message = { "Ingrese el ID de la unidad en la que desea almacenar el producto '" + nombre
				+ "': (Recuerde el prefijo U-)", idUnidad };

		int option = JOptionPane.showConfirmDialog(owner, message, "Ingrese la información del producto",
				JOptionPane.OK_CANCEL_OPTION);

		if (option == JOptionPane.OK_OPTION) {
			infoUnidad = idUnidad.getText();
		}

		return infoUnidad;

	}

}