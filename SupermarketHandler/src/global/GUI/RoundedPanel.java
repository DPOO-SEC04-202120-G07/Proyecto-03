package global.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RoundedPanel extends JPanel {

	private static final long serialVersionUID = -4059665176080393254L;

	public RoundedPanel(int width, int height){
	
	setPreferredSize(new Dimension(528, 297));
	setOpaque(false);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(new Color(0,0,0,120));
        g.fillRoundRect(0, 0, this.getWidth(),this.getHeight(),50, 50);
        
        g.setColor(new Color(99,78,128));
        g.fillRoundRect(0, 0, this.getWidth(),this.getHeight()-3,50, 50);
       
 	}
}
