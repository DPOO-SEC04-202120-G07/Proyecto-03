package global.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;

public class RoundedButton extends JButton {

	private static final long serialVersionUID = -6602524988706833661L;
	private String text = "";
	private Font font;

	private boolean hovering = false;
	private boolean click = false;

	public RoundedButton(int width, int height, String text, Font font) {
		setPreferredSize(new Dimension(width, height));
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		this.text = text;
		this.font = font;

		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				hovering = true;
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				hovering = false;
				click = false;

				;
			}

			public void mouseClicked(java.awt.event.MouseEvent evt) {

				click = true;

			}
		});

		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				click = false;
			}

		});
		timer.start();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!hovering) {
			g.setColor(new Color(0, 0, 0, 120));
		} else {
			g.setColor(new Color(0, 0, 0, 200));
		}

		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 40, 40);

		if (!hovering) {
			g.setColor(new Color(69, 52, 89));
		} else {
			g.setColor(new Color(59, 45, 77));
		}
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight() - 3, 40, 40);

		if (click) {
			Graphics2D g2d = (Graphics2D) g;

			g2d.setColor(new Color(255, 255, 255, 255));
			g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 40, 40);

			g2d.setColor(new Color(69, 52, 89));
			g.fillRoundRect(0, 0, this.getWidth(), this.getHeight() - 3, 40, 40);

		}

		g.setColor(Color.WHITE);
		g.setFont(font);

		FontMetrics fm = g.getFontMetrics();
		int x = ((this.getWidth() - fm.stringWidth(text)) / 2);
		int y = (((this.getHeight() - fm.getHeight()) / 2) + fm.getAscent());

		g.drawString(this.text, x, y);

		repaint();

	}

}
