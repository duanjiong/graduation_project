/**
 * 
 */
package wavelet;

import image.Image;
import image.PrintImage;

/**
 * @author Duan Jiong <djduanjiong@gmail.com>
 *
 */
public class WaveletTrans {
	
	public static void truncImage(Image image) {
		float[][] temp = null;
		if (image.height < 16 || image.width < 16) {
			System.out.println("the image file is too small!");
			return;
		}
		
		temp = new float[image.numcomps][(image.height/16)*16*(image.width/16)*16];
		
		for (int i = 0; i < image.numcomps; i++) {
			for (int j = 0; j < (image.height/16)*16; j++) {
				for (int m = 0; m < (image.width/16)*16; m++) {
					temp[i][j*(image.width/16)*16+m] = image.comps[i][j*(image.width)+m];
				}
			}
		}
		
		image.height = (image.height/16)*16;
		image.width = (image.width/16)*16;
		image.comps = temp;
	}
	
	public static void herizontalWavelet(Image image) {
		float[][] temp = new float[image.numcomps][image.height*image.width];
		
		for (int i = 0; i < image.numcomps; i++) {
			for (int j = 0; j < image.height; j++) {
				int index;
				for (index = 1; index < image.width-1; index += 2) {
					temp[i][j*image.width+index] = (float)(image.comps[i][j*image.width+index]
							- ((image.comps[i][j*image.width+index-1]+image.comps[i][j*image.width+index+1])*0.5));
					
				}
				temp[i][j*image.width+index] = (float)(image.comps[i][j*image.width+index]
						- ((image.comps[i][j*image.width+index-1]+image.comps[i][j*image.width+index-1])*0.5));
			}
		}
		
		
		for (int i = 0; i < image.numcomps; i++) {
			for (int j = 0; j < image.height; j++) {
				int index;
				for (index = 2; index < image.width; index += 2) {
					temp[i][j*image.width+index] = (float) (image.comps[i][j*image.width+index]
							+ (temp[i][j*image.width+index-1]+temp[i][j*image.width+index+1] +2)*0.25);
					
				}
				//j*image.width+2%image.width
				temp[i][j*image.width] = (float)(image.comps[i][j*image.width]
						+ ((image.comps[i][j*image.width+1]-(image.comps[i][j*image.width+2]+image.comps[i][j*image.width])*0.5)
								+temp[i][j*image.width+1] +2)*0.25);
			}
		}
		
	
		for (int num = 0; num < image.numcomps; num++) {
			for (int h = 0; h < image.height; h++) {
				for (int w = 0; w < image.width/2; w++) {
					image.comps[num][h*image.width + w] = temp[num][h*image.width+2*w];
				}
				for (int w = 0; w < image.width/2; w++) {
					image.comps[num][h*image.width + image.width/2 + w] = temp[num][h*image.width+2*w+1];
					
				}
			}
		}
		
	}
	
	
	public static void verticalWavelet(Image image) {
		float[][] temp = new float[image.numcomps][image.height*image.width];
		
		for (int i = 0; i < image.numcomps; i++) {
			for (int j = 0; j < image.width; j++) {
				int index;
				for (index = 1; index < image.height-1; index += 2) {
					temp[i][index*image.width+j] = (float)(image.comps[i][index*image.width+j]
							- (image.comps[i][(index-1)*image.width+j]+image.comps[i][(index+1)*image.width+j])*0.5);
					
				}
				temp[i][(index)*image.width+j] = (float)(image.comps[i][(index)*image.width+j]
						- (image.comps[i][(index-1)*image.width+j]+image.comps[i][(index-1)*image.width+j])*0.5);
			}
		}
		
		
		for (int i = 0; i < image.numcomps; i++) {
			for (int j = 0; j < image.width; j++) {
				int index;
				for (index = 2; index < image.height; index += 2) {
					temp[i][index*image.width+j] = (float)(image.comps[i][index*image.width+j]
							+ (temp[i][(index-1)*image.width+j]+temp[i][(index+1)*image.width+j] +2)*0.25);
					
				}
				
				//(2%image.heigth*image.width +j
				float flag = (float)(image.comps[i][image.width+j]-(image.comps[i][2*image.width+j]+image.comps[i][j])*0.5);
				temp[i][j] = (float)(image.comps[i][j]
						+ ((flag)
								+temp[i][image.width+j] +2)*0.25);
				
			}
		}
		
		for (int num = 0; num < image.numcomps; num++) {
			for (int h = 0; h < image.width; h++) {
				for (int w = 0; w < image.height/2; w++) {
					image.comps[num][w*image.width + h] = temp[num][(2*w)*image.width+h];
					
				}
				for (int w = 0; w < image.height/2; w++) {
					image.comps[num][(image.height/2 + w)*image.width + h] = temp[num][(2*w+1)*image.width+h];
				}
			}
		}
		
	}
	
	public static void waveletTrans(Image image, int num) {
		Image temp1, temp2;
		temp1 = new Image(image.height, image.width, image.numcomps);
		temp2 = new Image(image.height, image.width, image.numcomps);
		temp1.comps = temp2.comps = image.comps;
		
		for (int i = 0; i < num; i++) {
			//System.out.println("height="+image.height+";width="+image.width);
			herizontalWavelet(temp1);
			verticalWavelet(temp1);
			temp2 = new Image(temp1.height/2, temp1.width/2, temp1.numcomps);
			
			for (int k = 0; k < temp1.numcomps; k++) {
				for (int m = 0; m < temp1.height; m++) {
					for (int n = 0; n < temp1.width; n++) {
						image.comps[k][m*image.width + n] = temp1.comps[k][m*temp1.width + n];
					}
				}
			}
			
			for (int k = 0; k < temp2.numcomps; k++) {
				for (int m = 0; m < temp2.height; m++) {
					for (int n = 0; n < temp2.width; n++) {
						temp2.comps[k][m*temp2.width + n] = temp1.comps[k][m*temp1.width + n];
					}
				}
			}
			
			temp1 = temp2;
		}
	}
}
