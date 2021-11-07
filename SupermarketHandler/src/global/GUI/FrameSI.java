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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameSI extends JInternalFrame{

	private static final long serialVersionUID = -3111079588287177991L;

	public FrameSI(InterfazGrafica owner) {
		
		setTitle("Sistema Inventario");
		
		//Se establece la fuente externa que se va a usar (Si no la encuentra se usa Arial por defecto)
		Font sourceSansPro = new SourceSansFont(400, 60).getSourceSansFontFont();
		
		//Configuraciones estéticas de la ventana
		getContentPane().setBackground(new Color(118,88,152));
		
		//Establecer el tipo de layout de la interfaz
		setLayout(new GridBagLayout());
		
		
		// // //Establecer los componentes// // //
		
		// //Panel Superior - Información // // 
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new GridBagLayout());
		panelInfo.setBackground(new Color(82,67,110));
		panelInfo.setPreferredSize(new Dimension(1127, 80));
		GridBagConstraints constraintsInfoPanel = new GridBagConstraints();
		constraintsInfoPanel.gridx = 0; // El área de texto empieza en la columna 
		constraintsInfoPanel.gridy = 0; // El área de texto empieza en la fila 
		constraintsInfoPanel.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsInfoPanel.gridheight = 1; // El área de texto ocupa una fila
		constraintsInfoPanel.anchor = GridBagConstraints.NORTHWEST;
		constraintsInfoPanel.weighty = 1;
		constraintsInfoPanel.weightx = 1;
		
		
		
		//Logo supermercado//
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
			constraintslogoLabel.insets = new Insets(0,30,0,0);

			panelInfo.add(logoLabel, constraintslogoLabel);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Nombre Supermercado
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
		constraintsTitle.insets = new Insets(0,0,0,320);
		
		panelInfo.add(labelNombre, constraintsTitle);
		
		
		
		//Nombre Sistema
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
		constraintsSistema.insets = new Insets(0,0,0,30);
		panelInfo.add(labelSistema, constraintsSistema);
		
		//Agregar panel info
		add(panelInfo, constraintsInfoPanel);
		
		
		
		// //Panel Izquierdo - Requerimientos Funcionales // // 
		JPanel panelRequs = new RoundedPanel(238,467) {
			private static final long serialVersionUID = 7755889302022105131L;
			protected void paintComponent(Graphics g){
		        super.paintComponent(g);
		        g.setColor(new Color(99,78,128));
		        g.fillRect(0, -150, this.getWidth(),this.getHeight());
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
		constraintspanelRequs.insets = new Insets(0,0,30,10);
		
		
		//Agregar Boton Cargar Lotes
		JButton botonCargarLotes = new RoundedButton(176,46, "Cargar lotes",sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsBotonLotes = new GridBagConstraints();
		constraintsBotonLotes.gridx = 0; // El área de texto empieza en la columna 
		constraintsBotonLotes.gridy = 0; // El área de texto empieza en la fila 
		constraintsBotonLotes.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsBotonLotes.gridheight = 1; // El área de texto ocupa una fila
		constraintsBotonLotes.anchor = GridBagConstraints.CENTER;
		constraintsBotonLotes.weighty = 0.5;
		constraintsBotonLotes.weightx = 1;
		panelRequs.add(botonCargarLotes, constraintsBotonLotes);
		
		
		//Agregar Boton Eliminar Lotes
		JButton botonEliminarLotes = new RoundedButton(176,46, "Eliminar lotes vencidos",sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsEliminarLotes = new GridBagConstraints();
		constraintsEliminarLotes.gridx = 0; // El área de texto empieza en la columna 
		constraintsEliminarLotes.gridy = 1; // El área de texto empieza en la fila 
		constraintsEliminarLotes.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsEliminarLotes.gridheight = 1; // El área de texto ocupa una fila
		constraintsEliminarLotes.anchor = GridBagConstraints.CENTER;
		constraintsEliminarLotes.weighty = 0.5;
		constraintsEliminarLotes.weightx = 1;
		panelRequs.add(botonEliminarLotes, constraintsEliminarLotes);
		
		
		
		//Agregar Boton Agregar Unidad de Almacenamiento
		JButton botonAgregarUnidad = new RoundedButton(176,46, "Agregar U. de alm.",sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsAgregarUnidad = new GridBagConstraints();
		constraintsAgregarUnidad.gridx = 0; // El área de texto empieza en la columna 
		constraintsAgregarUnidad.gridy = 2; // El área de texto empieza en la fila 
		constraintsAgregarUnidad.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsAgregarUnidad.gridheight = 1; // El área de texto ocupa una fila
		constraintsAgregarUnidad.anchor = GridBagConstraints.CENTER;
		constraintsAgregarUnidad.weighty = 0.5;
		constraintsAgregarUnidad.weightx = 1;
		panelRequs.add(botonAgregarUnidad, constraintsAgregarUnidad);
		
		
		//Agregar Boton Agregar Imagen
		JButton botonAgregarImagen = new RoundedButton(176,46, "Agregar imagen",sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsAgregarImagen = new GridBagConstraints();
		constraintsAgregarImagen.gridx = 0; // El área de texto empieza en la columna 
		constraintsAgregarImagen.gridy = 3; // El área de texto empieza en la fila 
		constraintsAgregarImagen.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsAgregarImagen.gridheight = 1; // El área de texto ocupa una fila
		constraintsAgregarImagen.anchor = GridBagConstraints.CENTER;
		constraintsAgregarImagen.weighty = 0.5;
		constraintsAgregarImagen.weightx = 1;
		panelRequs.add(botonAgregarImagen, constraintsAgregarImagen);
		
	
		//Se añade el panel de los requerimientos
		add(panelRequs, constraintspanelRequs);
		
		
		// //Mini Panel  - Búsqueda de productos // // 
		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setBackground(new Color(118,88,152));
		panelBusqueda.setLayout(new GridBagLayout());
		GridBagConstraints constraintsPanelBusqueda = new GridBagConstraints();
		constraintsPanelBusqueda.gridx = 0; // El área de texto empieza en la columna 
		constraintsPanelBusqueda.gridy = 1; // El área de texto empieza en la fila 
		constraintsPanelBusqueda.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsPanelBusqueda.gridheight = 1; // El área de texto ocupa una fila
		constraintsPanelBusqueda.anchor = GridBagConstraints.NORTHEAST;
		constraintsPanelBusqueda.weighty = 1;
		constraintsPanelBusqueda.weightx = 1;
		
		//Logo lupa //
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
			constraintsLupaLabel.insets = new Insets(30,0,0,0);


			panelBusqueda.add(lupaLabel, constraintsLupaLabel);
	
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//JTextField Búsqueda
		CustomTextField textFieldBusqueda = new CustomTextField(176,31);
		GridBagConstraints constraintsBusqueda = new GridBagConstraints();
		constraintsBusqueda.gridx = 1; // El área de texto empieza en la columna 
		constraintsBusqueda.gridy = 0; // El área de texto empieza en la fila 
		constraintsBusqueda.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsBusqueda.gridheight = 1; // El área de texto ocupa una fila
		constraintsBusqueda.weightx = 1;
		constraintsBusqueda.weighty = 1;
		constraintsBusqueda.anchor = GridBagConstraints.EAST;
		constraintsBusqueda.insets = new Insets(30,10,0,40);

		panelBusqueda.add(textFieldBusqueda, constraintsBusqueda);
		
	
		//Se añade el panel de busqueda
		add(panelBusqueda, constraintsPanelBusqueda);
		
		
		// //Panel ListaProductos // //
		
		RoundedPanel panelProductos = new RoundedPanel(803,380);
		panelProductos.setLayout(new GridBagLayout());
		GridBagConstraints constraintsPanelProductos = new GridBagConstraints();
		constraintsPanelProductos.gridx = 0; // El área de texto empieza en la columna 
		constraintsPanelProductos.gridy = 1; // El área de texto empieza en la fila 
		constraintsPanelProductos.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsPanelProductos.gridheight = 1; // El área de texto ocupa una fila
		constraintsPanelProductos.weightx = 1;
		constraintsPanelProductos.weighty = 1;
		constraintsPanelProductos.anchor = GridBagConstraints.SOUTHEAST;
		constraintsPanelProductos.insets = new Insets(0,0,30,40);

		add(panelProductos, constraintsPanelProductos);
		
		
	}
}
