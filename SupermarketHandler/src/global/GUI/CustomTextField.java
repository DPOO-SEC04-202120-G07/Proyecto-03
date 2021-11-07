package global.GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class CustomTextField extends JTextField{
	private static final long serialVersionUID = -8038177555403001845L;

	public CustomTextField(int width, int height) {
		setFont(new SourceSansFont(400, 16).getSourceSansFontFont());
		setForeground(Color.WHITE);
		setCaretColor(Color.WHITE);
		setPreferredSize(new Dimension(width, height));
		setBackground(new Color(69,52,89));
		setBorder(BorderFactory.createLineBorder(new Color(0,0,0, 51)));
	}
}
