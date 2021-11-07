package global.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CustomImagePanel extends JPanel{
	
	private static final long serialVersionUID = -3554343180819335292L;
	private BufferedImage image;
	private String imagePath;
	private String productId;
	private String productName;
	
	Font sourceSansPro = new SourceSansFont(400, 16).getSourceSansFontFont();
	
	public CustomImagePanel(int width, int height, String productName,String productID, String imagePath) {
		this.imagePath = imagePath;
		this.productName = productName;
		this.productId = productID;
		
		setPreferredSize(new Dimension(width, height));
		setOpaque(false);
		
		try {
			if(!imagePath.contains("None")) {
			 this.image = ImageIO.read(new File(imagePath));}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(new Color(89, 68, 115));
		g.fillRoundRect(0, 0, this.getWidth()-10, this.getHeight()-30, 50, 50);
		
		if(!imagePath.contains("None")) {
		g.drawImage(image, 25, 10, this.getWidth()-60, this.getHeight()-50, this);
		}
		
		g.setFont(sourceSansPro);
		g.setColor(Color.WHITE);
		
		FontMetrics fm = g.getFontMetrics();
		int x = ((this.getWidth() - fm.stringWidth(productName)) / 2);
		int y = ((this.getHeight()));

		g.drawString(this.productName, x, y-15);
		g.drawString("ID:" +this.productId, x, y);
		
	}
	
}
