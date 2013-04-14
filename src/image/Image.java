/**
 * 
 */
package image;

/**
 * @author Duan Jiong <djduanjiong@gmail.com>
 *
 */
public class Image {
	public int height;
	public int width;
	public int numcomps;
	public byte[][] comps;
	
	public Image(int heigth, int width, int numcomps) {
		this.height = heigth;
		this.width = width;
		this.numcomps = numcomps;
		comps = new byte[numcomps][heigth*width];
	}
}
