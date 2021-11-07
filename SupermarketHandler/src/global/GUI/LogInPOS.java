package global.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogInPOS extends JDialog {

	private static final long serialVersionUID = 4386966946769252607L;

	public LogInPOS(InterfazGrafica owner) {
		super(owner, true);
		// Se establece la fuente externa que se va a usar (Si no la encuentra se usa
		// Arial por defecto)
		Font sourceSansPro = new SourceSansFont(400, 60).getSourceSansFontFont();

		// Configuraciones funcionales de la ventana
		setTitle("Log-In: Sistema POS");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(860, 484);
		setLocationRelativeTo(null);
		setResizable(false);

		// Configuraciones estéticas de la ventana
		getContentPane().setBackground(new Color(118, 88, 152));

		// Establecer el tipo de layout de la interfaz
		setLayout(new GridBagLayout());

		// Componentes ventana de inicio//

		// Label Titulo
		JLabel labelTitulo = new JLabel("Log-In: Sistema POS");
		labelTitulo.setFont(sourceSansPro.deriveFont(35f));
		labelTitulo.setForeground(Color.WHITE);
		labelTitulo.setVerticalAlignment(JLabel.TOP);

		GridBagConstraints constraintsTitle = new GridBagConstraints();
		constraintsTitle.gridx = 1; // El área de texto empieza en la columna uno
		constraintsTitle.gridy = 0; // El área de texto empieza en la fila cero
		constraintsTitle.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsTitle.gridheight = 1; // El área de texto ocupa 1 fila
		constraintsTitle.anchor = GridBagConstraints.NORTH;
		constraintsTitle.insets = new Insets(35, 0, 0, 0);
		constraintsTitle.weighty = 1;
		add(labelTitulo, constraintsTitle);

		// JPanel Log-In
		JPanel panelLogIn = new RoundedPanel(430, 242);
		panelLogIn.setLayout(new GridBagLayout());

		GridBagConstraints constraintsPanelLogin = new GridBagConstraints();
		constraintsPanelLogin.gridx = 1; // El área de texto empieza en la columna uno
		constraintsPanelLogin.gridy = 2; // El área de texto empieza en la fila dos
		constraintsPanelLogin.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsPanelLogin.gridheight = 1; // El área de texto ocupa una fila
		constraintsPanelLogin.weighty = 1;
		constraintsPanelLogin.insets = new Insets(0, 0, 60, 0);

		// Label Nombre
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setFont(sourceSansPro.deriveFont(18f));
		labelNombre.setForeground(Color.WHITE);
		GridBagConstraints constraintsLabelNombre = new GridBagConstraints();
		constraintsLabelNombre.gridx = 0; // El área de texto empieza en la columna
		constraintsLabelNombre.gridy = 0; // El área de texto empieza en la fila
		constraintsLabelNombre.gridwidth = 2; // El área de texto ocupa una columna.
		constraintsLabelNombre.gridheight = 1; // El área de texto ocupa una fila
		constraintsLabelNombre.anchor = GridBagConstraints.WEST;
		constraintsLabelNombre.weightx = 1;
		constraintsLabelNombre.insets = new Insets(0, 40, 10, 0);
		panelLogIn.add(labelNombre, constraintsLabelNombre);

		// Label ID
		JLabel labelID = new JLabel("ID Cajero");
		labelID.setFont(sourceSansPro.deriveFont(18f));
		labelID.setForeground(Color.WHITE);
		GridBagConstraints constraintsLabelID = new GridBagConstraints();
		constraintsLabelID.gridx = 0; // El área de texto empieza en la columna
		constraintsLabelID.gridy = 1; // El área de texto empieza en la fila
		constraintsLabelID.gridwidth = 2; // El área de texto ocupa una columna.
		constraintsLabelID.gridheight = 1; // El área de texto ocupa una fila
		constraintsLabelID.anchor = GridBagConstraints.WEST;
		constraintsLabelID.weightx = 1;
		constraintsLabelID.insets = new Insets(25, 40, 50, 0);
		panelLogIn.add(labelID, constraintsLabelID);

		// TextField Nombre
		JTextField textFieldNombre = new CustomTextField(204, 34);
		textFieldNombre.setText("");
		GridBagConstraints constraintsTextFieldNombre = new GridBagConstraints();
		constraintsTextFieldNombre.gridx = 1; // El área de texto empieza en la columna
		constraintsTextFieldNombre.gridy = 0; // El área de texto empieza en la fila
		constraintsTextFieldNombre.gridwidth = 2; // El área de texto ocupa una columna.
		constraintsTextFieldNombre.gridheight = 1; // El área de texto ocupa una fila
		constraintsTextFieldNombre.weightx = 1;
		constraintsTextFieldNombre.anchor = GridBagConstraints.EAST;
		constraintsTextFieldNombre.insets = new Insets(0, 0, 10, 40);
		panelLogIn.add(textFieldNombre, constraintsTextFieldNombre);

		// TextField ID
		JTextField textFieldID = new CustomTextField(204, 34);
		textFieldID.setText("");
		GridBagConstraints constraintsTextFieldID = new GridBagConstraints();
		constraintsTextFieldID.gridx = 1; // El área de texto empieza en la columna
		constraintsTextFieldID.gridy = 1; // El área de texto empieza en la fila
		constraintsTextFieldID.gridwidth = 2; // El área de texto ocupa una columna.
		constraintsTextFieldID.gridheight = 1; // El área de texto ocupa una fila
		constraintsTextFieldID.anchor = GridBagConstraints.EAST;
		constraintsTextFieldID.weightx = 1;
		constraintsTextFieldID.insets = new Insets(25, 0, 50, 40);
		panelLogIn.add(textFieldID, constraintsTextFieldID);

		// Boton Ingresar
		JButton botonIngresar = new RoundedButton(122, 35, "Ingresar", sourceSansPro.deriveFont(16f));
		botonIngresar.setFont(sourceSansPro.deriveFont(12f));
		GridBagConstraints constraintsIngresar = new GridBagConstraints();
		constraintsIngresar.gridx = 1; // El área de texto empieza en la columna
		constraintsIngresar.gridy = 2; // El área de texto empieza en la fila
		constraintsIngresar.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsIngresar.gridheight = 1; // El área de texto ocupa una fila
		constraintsIngresar.weightx = 1;
		panelLogIn.add(botonIngresar, constraintsIngresar);

		// Visible y se añade el panel
		add(panelLogIn, constraintsPanelLogin);

		// Se añaden los listeners necesarios para que funcione el panel
		botonIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				String info_nombre = textFieldNombre.getText();
				String info_id = textFieldID.getText();
				try {
					owner.cerrarFrameInicio();
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				owner.abrirFramePOS();
				dispose();
			}
		});

	}

	public void abrirLogInPOS() {
		// Hacer visible
		setVisible(true);
	}
}
