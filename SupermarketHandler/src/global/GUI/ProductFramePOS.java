package global.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import global.modelo.Producto;
import global.sistemas.inventario.procesamiento.HandledException;

public class ProductFramePOS extends JDialog  {
	private static final long serialVersionUID = 5385568765630751405L;
	private String imagePath;
	@SuppressWarnings("unused")
	private String productId;
	private String productName;
	private Font sourceSansPro;
	private BufferedImage image;
	
	@SuppressWarnings("unused")
	private InterfazGrafica owner;

	public ProductFramePOS(InterfazGrafica owner, String productId) {
		super(owner, true);
		this.owner = owner;
		sourceSansPro = new SourceSansFont(400, 12).getSourceSansFontFont();

		// Configuraciones funcionales de la ventana
		setTitle("Consultar Producto");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(688, 478);
		setLocationRelativeTo(null);
		setResizable(false);

		// Configuraciones estéticas de la ventana
		getContentPane().setBackground(new Color(118, 88, 152));

		// Establecer el tipo de layout de la interfaz
		setLayout(new GridBagLayout());

		// Variables
		Producto productoMostrado = owner.getHandlerSi().getProducto(productId);
		this.imagePath = productoMostrado.getPathImagen();
		this.productName = productoMostrado.getNombre();
		this.productId = productoMostrado.getCodigoProducto().getCodigo();

		try {
			if (!imagePath.contains("None")) {
				this.image = ImageIO.read(new File(imagePath));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Componentes

		// Panel con imagen, nombre e id
		JPanel panelImagenProducto = new JPanel() {
			private static final long serialVersionUID = 7231326846636583268L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.setColor(new Color(89, 68, 115));

				g.fillRoundRect(15, 20, 273, 247, 50, 50);

				if (!imagePath.contains("None")) {
					g.drawImage(image, 15 + 30, 20 + 24, 273 - 60, 247 - 50, this);
				}

				g.setFont(sourceSansPro.deriveFont(32f));
				g.setColor(Color.WHITE);

				FontMetrics fm = g.getFontMetrics();
				int x = ((273 + 15 - fm.stringWidth(productName)) / 2);
				int y = ((247 + 80));

				g.drawString(productName, x, y - 15);
				g.drawString("ID:" + productId, x, y + 40);

				repaint();

			}
		};
		panelImagenProducto.setPreferredSize(new Dimension(300, 440));
		panelImagenProducto.setOpaque(false);

		GridBagConstraints constraintsPanelImagen = new GridBagConstraints();
		constraintsPanelImagen.gridx = 0; // El área de texto empieza en la columna
		constraintsPanelImagen.gridy = 0; // El área de texto empieza en la fila
		constraintsPanelImagen.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsPanelImagen.gridheight = 6; // El área de texto ocupa una fila
		constraintsPanelImagen.anchor = GridBagConstraints.NORTHWEST;
		constraintsPanelImagen.weighty = 1;
		constraintsPanelImagen.weightx = 1;

		// Se añade el panel
		add(panelImagenProducto, constraintsPanelImagen);

		// Label Marca
		JLabel marcaProducto = new JLabel("Marca");
		marcaProducto.setFont(sourceSansPro.deriveFont(20f));
		marcaProducto.setForeground(Color.WHITE);
		GridBagConstraints constraintsLabelMarca = new GridBagConstraints();
		constraintsLabelMarca.gridx = 1; // El área de texto empieza en la columna
		constraintsLabelMarca.gridy = 0; // El área de texto empieza en la fila
		constraintsLabelMarca.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsLabelMarca.gridheight = 1; // El área de texto ocupa una fila
		constraintsLabelMarca.anchor = GridBagConstraints.WEST;
		constraintsLabelMarca.weighty = 1;
		constraintsLabelMarca.weightx = 1;
		constraintsLabelMarca.insets = new Insets(20,0,0,0);

		add(marcaProducto, constraintsLabelMarca);

		// JTextField Marca
		CustomTextField marcaProductoField = new CustomTextField(190, 34);
		marcaProductoField.setEditable(false);
		marcaProductoField.setText(productoMostrado.getMarca());
		marcaProductoField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints constraintsFieldMarca = new GridBagConstraints();
		constraintsFieldMarca.gridx = 2; // El área de texto empieza en la columna
		constraintsFieldMarca.gridy = 0; // El área de texto empieza en la fila
		constraintsFieldMarca.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsFieldMarca.gridheight = 1; // El área de texto ocupa una fila
		constraintsFieldMarca.anchor = GridBagConstraints.WEST;
		constraintsFieldMarca.weighty = 1;
		constraintsFieldMarca.weightx = 1;
		constraintsFieldMarca.insets = new Insets(20,0,0,0);

		add(marcaProductoField, constraintsFieldMarca);

		// Label Categoria
		JLabel categoriaProducto = new JLabel("Categoría");
		categoriaProducto.setFont(sourceSansPro.deriveFont(20f));
		categoriaProducto.setForeground(Color.WHITE);
		GridBagConstraints constraintsLabelCategoria = new GridBagConstraints();
		constraintsLabelCategoria.gridx = 1; // El área de texto empieza en la columna
		constraintsLabelCategoria.gridy = 1; // El área de texto empieza en la fila
		constraintsLabelCategoria.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsLabelCategoria.gridheight = 1; // El área de texto ocupa una fila
		constraintsLabelCategoria.anchor = GridBagConstraints.NORTHWEST;
		constraintsLabelCategoria.weighty = 1;
		constraintsLabelCategoria.weightx = 1;
		constraintsLabelCategoria.insets = new Insets(15,0,0,0);

		add(categoriaProducto, constraintsLabelCategoria);

		// JTextField Categoria
		CustomTextField categoriaProductoField = new CustomTextField(190, 34);
		categoriaProductoField.setEditable(false);
		categoriaProductoField.setText(productoMostrado.getCategoria().getNombre());
		categoriaProductoField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints constraintsFieldCategoria = new GridBagConstraints();
		constraintsFieldCategoria.gridx = 2; // El área de texto empieza en la columna
		constraintsFieldCategoria.gridy = 1; // El área de texto empieza en la fila
		constraintsFieldCategoria.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsFieldCategoria.gridheight = 1; // El área de texto ocupa una fila
		constraintsFieldCategoria.anchor = GridBagConstraints.NORTHWEST;
		constraintsFieldCategoria.weighty = 1;
		constraintsFieldCategoria.weightx = 1;
		constraintsFieldCategoria.insets = new Insets(15,0,0,0);

		add(categoriaProductoField, constraintsFieldCategoria);

		// Label Subcategoria
		JLabel subCategoriaProducto = new JLabel("Subcategorías");
		subCategoriaProducto.setFont(sourceSansPro.deriveFont(20f));
		subCategoriaProducto.setForeground(Color.WHITE);
		GridBagConstraints constraintsLabelSubCategoria = new GridBagConstraints();
		constraintsLabelSubCategoria.gridx = 1; // El área de texto empieza en la columna
		constraintsLabelSubCategoria.gridy = 3; // El área de texto empieza en la fila
		constraintsLabelSubCategoria.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsLabelSubCategoria.gridheight = 1; // El área de texto ocupa una fila
		constraintsLabelSubCategoria.anchor = GridBagConstraints.NORTHWEST;
		constraintsLabelSubCategoria.weighty = 1;
		constraintsLabelSubCategoria.weightx = 1;
		

		add(subCategoriaProducto, constraintsLabelSubCategoria);

		// JTextField SubCategoria
		CustomTextField subCategoriaProductoField = new CustomTextField(190, 34);
		subCategoriaProductoField.setEditable(false);
		subCategoriaProductoField.setText(productoMostrado.getCategoria().getSubcategoriasAsString());
		subCategoriaProductoField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints constraintsFieldSubCategoria = new GridBagConstraints();
		constraintsFieldSubCategoria.gridx = 2; // El área de texto empieza en la columna
		constraintsFieldSubCategoria.gridy = 3; // El área de texto empieza en la fila
		constraintsFieldSubCategoria.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsFieldSubCategoria.gridheight = 1; // El área de texto ocupa una fila
		constraintsFieldSubCategoria.anchor = GridBagConstraints.NORTHWEST;
		constraintsFieldSubCategoria.weighty = 1;
		constraintsFieldSubCategoria.weightx = 1;

		add(subCategoriaProductoField, constraintsFieldSubCategoria);

		// Label Precio
		JLabel precioProducto = new JLabel("Precio actual");
		precioProducto.setFont(sourceSansPro.deriveFont(20f));
		precioProducto.setForeground(Color.WHITE);
		GridBagConstraints constraintsLabelPrecio = new GridBagConstraints();
		constraintsLabelPrecio.gridx = 1; // El área de texto empieza en la columna
		constraintsLabelPrecio.gridy = 4; // El área de texto empieza en la fila
		constraintsLabelPrecio.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsLabelPrecio.gridheight = 1; // El área de texto ocupa una fila
		constraintsLabelPrecio.anchor = GridBagConstraints.NORTHWEST;
		constraintsLabelPrecio.weighty = 1;
		constraintsLabelPrecio.weightx = 1;
	

		add(precioProducto, constraintsLabelPrecio);

		// JTextField Precio
		CustomTextField precioProductoField = new CustomTextField(190, 34);
		precioProductoField.setEditable(false);
		precioProductoField.setText(""+productoMostrado.getPrecio());
		precioProductoField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints constraintsFieldPrecio = new GridBagConstraints();
		constraintsFieldPrecio.gridx = 2; // El área de texto empieza en la columna
		constraintsFieldPrecio.gridy = 4; // El área de texto empieza en la fila
		constraintsFieldPrecio.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsFieldPrecio.gridheight = 1; // El área de texto ocupa una fila
		constraintsFieldPrecio.anchor = GridBagConstraints.NORTHWEST;
		constraintsFieldPrecio.weighty = 1;
		constraintsFieldPrecio.weightx = 1;
		constraintsFieldPrecio.insets = new Insets(0,0,40,0);

		add(precioProductoField, constraintsFieldPrecio);
		
		
		
		
		
		
		//Boton Lotes
		JButton botonAgregar = new RoundedButton(170,40,"Agregar a la compra",sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsAgregar = new GridBagConstraints();
		constraintsAgregar.gridx = 2; // El área de texto empieza en la columna
		constraintsAgregar.gridy = 5; // El área de texto empieza en la fila
		constraintsAgregar.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsAgregar.gridheight = 1; // El área de texto ocupa una fila
		constraintsAgregar.anchor = GridBagConstraints.CENTER;
		constraintsAgregar.weighty = 1;
		constraintsAgregar.weightx = 1;
		constraintsAgregar.insets = new Insets(0,0,80,40);

		add(botonAgregar, constraintsAgregar);
		
		
		
		//Listeners
				
		
		botonAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {

				
				String[] nombresColumnas = {"ID Lote", "Fecha de Vencimiento", "Disponible"};
				Object[][] filas = owner.getHandlerSi().getLotesAsociadosProducto(productId);
				JTable  tablaLotes = new JTable(filas,nombresColumnas);
				JScrollPane scrollLotes = new JScrollPane(tablaLotes);
				
				
				JOptionPane.showConfirmDialog(owner, scrollLotes, "Lotes vigentes asociados a producto",
						JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		
		
		
		

		setVisible(true);
	}

}
