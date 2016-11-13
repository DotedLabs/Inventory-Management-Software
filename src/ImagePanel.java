

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;

public class ImagePanel extends JComponent{

	private Image image;
	public ImagePanel(Image image) {
		this.image = image;
	}
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 185, -10, null);
	}
}

