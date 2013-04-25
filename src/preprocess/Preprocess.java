/**
 * 
 */
package preprocess;

import image.Image;

/**
 * @author Duan Jiong <djduanjiong@gmail.com>
 *
 */
public class Preprocess {
	public static void herizontal_trans(Image image) {
		int index = 0;
		int numcomps = 0;
		for (numcomps=0; numcomps < image.numcomps; numcomps++) {
			for (index = 0; index < image.height*image.width; index++) {
				image.comps[numcomps][index] -= 128;
			}
		}
	}
	
	public static void  vertical_trans(Image image) {
		float[][] temp = new float[image.numcomps][image.height*image.width];
		int index = 0;
		
		for (index = 0; index < image.width*image.height; index++) {
			temp[0][index] = (float)(0.25*image.comps[0][index] + 0.5*image.comps[1][index] + 0.25*image.comps[2][index]);
		}
		
		for (index = 0; index < image.width*image.height; index++) {
			temp[1][index] = (float)(image.comps[2][index] - image.comps[1][index]);
		}
		
		for (index = 0; index < image.width*image.height; index++) {
			temp[2][index] = (float)(image.comps[0][index] - image.comps[1][index]);
		}
		
		image.comps = temp;
	}
}
