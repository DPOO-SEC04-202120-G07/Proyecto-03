package global.GUI;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class SourceSansFont{


	private Font actualFont = new Font("Arial", 400, 60);
	
	public SourceSansFont(int style, int size) {
		
		try {
		    //create the font to use. Specify the size!
			this.actualFont = Font.createFont(Font.TRUETYPE_FONT, new File("sources/SourceSansPro-SemiBold.ttf")).deriveFont(style, size);
			
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(actualFont);
		} catch (IOException | FontFormatException e) {
		    e.printStackTrace();
		}
		
	
		
	}
	
	public Font getSourceSansFontFont(){
		
		return this.actualFont;
		
	}
	


	
}
