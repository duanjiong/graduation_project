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
	public float[][] comps;
	
	public Image(int heigth, int width, int numcomps) {
		this.height = heigth;
		this.width = width;
		this.numcomps = numcomps;
		comps = new float[numcomps][heigth*width];
	}
}
