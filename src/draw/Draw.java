/**
 * 
 */
package draw;

import java.awt.Color;
import java.awt.Graphics;

import image.Image;

/**
 * @author Duan Jiong <djduanjiong@gmail.com>
 *
 */
public class Draw {
	public static void draw(Image image, Graphics g) {
		Color color = null;
		
		for (int m = 0; m < image.height; m++) {
			for (int n = 0; n < image.width; n++) {

				
				
				color = new Color(((int)Math.abs(image.comps[0][m*image.width + n]))%256, ((int)Math.abs(image.comps[1][m*image.width + n]))%256,((int) Math.abs(image.comps[2][m*image.width + n]))%256);
				g.setColor(color);
				g.drawLine(n, m, n, m);
				
			}
		}
	}
}
