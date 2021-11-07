package global.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameInicio extends JInternalFrame {

	private static final long serialVersionUID = 4025228801389810860L;

	public FrameInicio(InterfazGrafica owner) {

		setTitle("Inicio");

		// Se establece la fuente externa que se va a usar (Si no la encuentra se usa
		// Arial por defecto)
		Font sourceSansPro = new SourceSansFont(400, 60).getSourceSansFontFont();

		// Configuraciones estéticas de la ventana
		getContentPane().setBackground(new Color(118, 88, 152));

		// Establecer el tipo de layout de la interfaz
		setLayout(new GridBagLayout());

		// Componentes ventana de inicio//

		// Label Nombre
		JLabel labelNombre = new JLabel("Supermercados NERV");
		labelNombre.setFont(sourceSansPro.deriveFont(60f));
		labelNombre.setForeground(Color.WHITE);
		labelNombre.setVerticalAlignment(JLabel.TOP);

		GridBagConstraints constraintsTitle = new GridBagConstraints();
		constraintsTitle.gridx = 1; // El área de texto empieza en la columna uno
		constraintsTitle.gridy = 0; // El área de texto empieza en la fila cero
		constraintsTitle.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsTitle.gridheight = 1; // El área de texto ocupa 1 fila
		constraintsTitle.anchor = GridBagConstraints.NORTH;
		constraintsTitle.insets = new Insets(35, 0, 0, 0);
		add(labelNombre, constraintsTitle);

		// JPanel Botones sistema
		JPanel panelBotonesSistema = new RoundedPanel(528, 297);
		panelBotonesSistema.setLayout(new GridBagLayout());

		GridBagConstraints constraintsSistemasPanel = new GridBagConstraints();
		constraintsSistemasPanel.gridx = 1; // El área de texto empieza en la columna uno
		constraintsSistemasPanel.gridy = 2; // El área de texto empieza en la fila dos
		constraintsSistemasPanel.gridwidth = 1; // El área de texto ocupa una columna.
		constraintsSistemasPanel.gridheight = 1; // El área de texto ocupa una fila
		constraintsSistemasPanel.anchor = GridBagConstraints.NORTH;
		constraintsSistemasPanel.weighty = 1;
		constraintsSistemasPanel.insets = new Insets(50, 0, 0, 0);

		JButton botonInventario = new RoundedButton(266, 51, "Sistema Inventario", sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsBotonINV = new GridBagConstraints();
		constraintsBotonINV.gridx = 1;
		constraintsBotonINV.gridy = 0;
		constraintsBotonINV.gridwidth = 1;
		constraintsBotonINV.gridheight = 1;
		constraintsBotonINV.anchor = GridBagConstraints.CENTER;
		constraintsBotonINV.weighty = 1;
		constraintsBotonINV.insets = new Insets(25, 0, 0, 0);
		panelBotonesSistema.add(botonInventario, constraintsBotonINV);

		JButton botonPOS = new RoundedButton(266, 51, "Sistema POS", sourceSansPro.deriveFont(16f));
		GridBagConstraints constraintsBotonPOS = new GridBagConstraints();
		constraintsBotonPOS.gridx = 1;
		constraintsBotonPOS.gridy = 1;
		constraintsBotonPOS.gridwidth = 1;
		constraintsBotonPOS.gridheight = 1;
		constraintsBotonPOS.anchor = GridBagConstraints.CENTER;
		constraintsBotonPOS.weighty = 1;
		constraintsBotonPOS.insets = new Insets(0, 0, 25, 0);
		panelBotonesSistema.add(botonPOS, constraintsBotonPOS);

		// Añadir panel
		add(panelBotonesSistema, constraintsSistemasPanel);

		// Se añaden los listeners externos
		botonInventario.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				owner.abrirLogInInventario();
			}
		});

		// Se añaden los listeners externos
		botonPOS.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				owner.abrirLogInPOS();

			}
		});

	}

}
